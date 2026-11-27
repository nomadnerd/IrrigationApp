import 'package:flutter/material.dart';
import '../../core/theme/app_theme.dart';
import '../projects/projects_screen.dart';
import '../drip_irrigation/drip_screen.dart';
import '../sprinkler_irrigation/sprinkler_screen.dart';

class DashboardScreen extends StatelessWidget {
  const DashboardScreen({super.key});

  @override
  Widget build(BuildContext context) => Scaffold(
    appBar: AppBar(
      title: const Row(mainAxisSize: MainAxisSize.min, children: [
        Icon(Icons.water_drop_rounded, color: AppTheme.aquaTeal),
        SizedBox(width: 8),
        Text('AquaDesign', style: TextStyle(
          color: AppTheme.deepNavy, fontWeight: FontWeight.w800)),
      ]),
      actions: [
        IconButton(tooltip: 'Projects', onPressed: () => Navigator.push(context,
          MaterialPageRoute(builder: (_) => const ProjectsScreen())),
          icon: const Icon(Icons.folder_open_outlined)),
        const SizedBox(width: 8),
      ],
    ),
    body: SafeArea(child: ListView(
      padding: const EdgeInsets.fromLTRB(20, 12, 20, 28),
      children: [
        Container(
          padding: const EdgeInsets.all(22),
          decoration: BoxDecoration(
            borderRadius: BorderRadius.circular(22),
            gradient: const LinearGradient(
              colors: [AppTheme.deepNavy, AppTheme.aquaTeal],
              begin: Alignment.topLeft, end: Alignment.bottomRight),
          ),
          child: const Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
            Text('ENGINEERING WATER. DESIGNING SOLUTIONS.',
              style: TextStyle(color: Colors.white, fontSize: 11,
                fontWeight: FontWeight.w700, letterSpacing: 1.2)),
            SizedBox(height: 18),
            Text('Design with confidence.',
              style: TextStyle(color: Colors.white, fontSize: 25,
                fontWeight: FontWeight.w700)),
            SizedBox(height: 8),
            Text('Professional tools for irrigation planning, hydraulic calculations and water infrastructure design.',
              style: TextStyle(color: Colors.white70, height: 1.45)),
          ]),
        ),
        const SizedBox(height: 26),
        const _Heading('Design modules', 'Choose a calculation workspace'),
        const SizedBox(height: 12),
        _Module(Icons.water_drop_outlined, 'Drip irrigation',
          'Crop water demand, emitters, irrigation frequency and laterals',
          onTap: () => Navigator.push(context,
            MaterialPageRoute(builder: (_) => const DripScreen()))),
        _Module(Icons.grain, 'Sprinkler irrigation',
          'Sprinkler selection, spacing, layouts and pressure checks',
          onTap: () => Navigator.push(context,
            MaterialPageRoute(builder: (_) => const SprinklerScreen()))),
        const _Module(Icons.water_outlined, 'Hydraulics & pumps',
          'Pipe sizing, headloss, total dynamic head and pump duty'),
        const _Module(Icons.picture_as_pdf_outlined, 'Design reports',
          'Calculation sheets and professional PDF outputs'),
        const SizedBox(height: 24),
        const _Heading('Project workspace', 'Offline-first project storage'),
        const SizedBox(height: 12),
        Card(child: Padding(padding: const EdgeInsets.all(18), child: Row(children: [
          const Icon(Icons.folder_open_outlined, color: AppTheme.aquaTeal, size: 30),
          const SizedBox(width: 14),
          Expanded(child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
            const Text('No projects yet', style: TextStyle(
              color: AppTheme.deepNavy, fontWeight: FontWeight.w700)),
            const SizedBox(height: 4),
            Text('Use the folder icon above to create and manage offline projects.',
              style: TextStyle(color: Colors.black54)),
          ])),
        ]))),
        const SizedBox(height: 18),
        const Center(child: Text('AquaDesign • Version 1.0 foundation',
          style: TextStyle(color: Colors.black45, fontSize: 12))),
      ],
    )),
  );
}

class _Heading extends StatelessWidget {
  const _Heading(this.title, this.subtitle);
  final String title, subtitle;
  @override
  Widget build(BuildContext context) => Column(
    crossAxisAlignment: CrossAxisAlignment.start, children: [
      Text(title, style: const TextStyle(fontSize: 18,
        fontWeight: FontWeight.w800, color: AppTheme.deepNavy)),
      const SizedBox(height: 4),
      Text(subtitle, style: const TextStyle(color: Colors.black54)),
    ]);
}

class _Module extends StatelessWidget {
  const _Module(this.icon, this.title, this.subtitle, {this.onTap});
  final IconData icon;
  final String title, subtitle;
  final VoidCallback? onTap;
  @override
  Widget build(BuildContext context) => Card(
    margin: const EdgeInsets.only(bottom: 10),
    child: ListTile(
      onTap: onTap,
      contentPadding: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
      leading: Container(width: 44, height: 44,
        decoration: BoxDecoration(color: AppTheme.aquaTeal.withValues(alpha: 0.10),
          borderRadius: BorderRadius.circular(12)),
        child: Icon(icon, color: AppTheme.aquaTeal)),
      title: Text(title, style: const TextStyle(
        color: AppTheme.deepNavy, fontWeight: FontWeight.w700)),
      subtitle: Padding(padding: const EdgeInsets.only(top: 5),
        child: Text(subtitle)),
      trailing: const Icon(Icons.chevron_right, color: Colors.black38),
    ),
  );
}
