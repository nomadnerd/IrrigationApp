import 'package:path/path.dart' as p;
import 'package:path_provider/path_provider.dart';
import 'package:sqflite/sqflite.dart';

import 'project.dart';

class ProjectDatabase {
  ProjectDatabase._();
  static final ProjectDatabase instance = ProjectDatabase._();
  Database? _database;

  Future<Database> get database async {
    if (_database != null) return _database!;
    final directory = await getApplicationDocumentsDirectory();
    _database = await openDatabase(
      p.join(directory.path, 'aquadesign.db'),
      version: 1,
      onCreate: (db, version) async {
        await db.execute('''
          CREATE TABLE projects (
            id TEXT PRIMARY KEY,
            name TEXT NOT NULL,
            location TEXT NOT NULL,
            crop TEXT NOT NULL,
            area_ha REAL NOT NULL CHECK(area_ha > 0),
            unit_system TEXT NOT NULL,
            created_at TEXT NOT NULL
          )
        ''');
      },
    );
    return _database!;
  }

  Future<List<IrrigationProject>> getProjects() async {
    final db = await database;
    final rows = await db.query('projects', orderBy: 'created_at DESC');
    return rows.map(IrrigationProject.fromMap).toList();
  }

  Future<void> saveProject(IrrigationProject project) async {
    final db = await database;
    await db.insert('projects', project.toMap(),
      conflictAlgorithm: ConflictAlgorithm.replace);
  }

  Future<void> deleteProject(String id) async {
    final db = await database;
    await db.delete('projects', where: 'id = ?', whereArgs: [id]);
  }
}
