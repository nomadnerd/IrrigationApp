import 'package:flutter/material.dart';
import 'package:uuid/uuid.dart';

import '../../core/theme/app_theme.dart';
import 'project.dart';
import 'project_database.dart';

class ProjectsScreen extends StatefulWidget {
  const ProjectsScreen({super.key});
  @override
  State<ProjectsScreen> createState() => _ProjectsScreenState();
}

class _ProjectsScreenState extends State<ProjectsScreen> {
  late Future<List<IrrigationProject>> _projects;

  @override
  void initState() {
    super.initState();
    _reload();
  }

  void _reload() => _projects = ProjectDatabase.instance.getProjects();

  Future<void> _createProject() async {
    final formKey = GlobalKey<FormState>();
    final name = TextEditingController();
    final location = TextEditingController();
    final crop = TextEditingController();
    final area = TextEditingController();
    final result = await showDialog<IrrigationProject>(
      context: context,
      builder: (context) => AlertDialog(
        title: const Text('New design project'),
        content: Form(
          key: formKey,
          child: SingleChildScrollView(child: Column(mainAxisSize: MainAxisSize.min, children: [
            TextFormField(controller: name, decoration: const InputDecoration(labelText: 'Project name'),
              validator: (v) => v == null || v.trim().isEmpty ? 'Enter a project name' : null),
            TextFormField(controller: location, decoration: const InputDecoration(labelText: 'Location')),
            TextFormField(controller: crop, decoration: const InputDecoration(labelText: 'Crop')),
            TextFormField(controller: area, decoration: const InputDecoration(labelText: 'Area (hectares)'),
              keyboardType: const TextInputType.numberWithOptions(decimal: true),
              validator: (v) {
                final n = double.tryParse(v ?? '');
                return n == null || !n.isFinite || n <= 0 ? 'Enter an area greater than zero' : null;
              }),
          ])),
        ),
        actions: [
          TextButton(onPressed: () => Navigator.pop(context), child: const Text('Cancel')),
          FilledButton(onPressed: () {
            if (!formKey.currentState!.validate()) return;
            Navigator.pop(context, IrrigationProject(
              id: const Uuid().v4(), name: name.text.trim(),
              location: location.text.trim(), crop: crop.text.trim(),
              areaHa: double.parse(area.text), unitSystem: 'metric',
              createdAt: DateTime.now(),
            ));
          }, child: const Text('Create project')),
        ],
      ),
    );
    if (result == null) return;
    await ProjectDatabase.instance.saveProject(result);
    if (!mounted) return;
    setState(_reload);
  }

  @override
  Widget build(BuildContext context) => Scaffold(
    appBar: AppBar(title: const Text('Projects')),
    floatingActionButton: FloatingActionButton.extended(
      onPressed: _createProject,
      icon: const Icon(Icons.add),
      label: const Text('New project'),
    ),
    body: FutureBuilder<List<IrrigationProject>>(
      future: _projects,
      builder: (context, snapshot) {
        if (snapshot.connectionState == ConnectionState.waiting) {
          return const Center(child: CircularProgressIndicator());
        }
        if (snapshot.hasError) return Center(child: Text('Could not load projects: ${snapshot.error}'));
        final projects = snapshot.data ?? [];
        if (projects.isEmpty) return const Center(child: Text('No saved projects. Create your first design.'));
        return ListView.builder(
          padding: const EdgeInsets.all(16),
          itemCount: projects.length,
          itemBuilder: (context, index) {
            final project = projects[index];
            return Card(child: ListTile(
              leading: const Icon(Icons.folder_outlined, color: AppTheme.aquaTeal),
              title: Text(project.name),
              subtitle: Text('${project.crop.isEmpty ? 'Crop not specified' : project.crop} • ${project.areaHa} ha • ${project.location}'),
              trailing: IconButton(
                tooltip: 'Delete project',
                icon: const Icon(Icons.delete_outline),
                onPressed: () async {
                  await ProjectDatabase.instance.deleteProject(project.id);
                  if (mounted) setState(_reload);
                },
              ),
            ));
          },
        );
      },
    ),
  );
}
