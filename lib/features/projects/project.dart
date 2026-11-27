class IrrigationProject {
  const IrrigationProject({
    required this.id,
    required this.name,
    required this.location,
    required this.crop,
    required this.areaHa,
    required this.unitSystem,
    required this.createdAt,
  });

  final String id;
  final String name;
  final String location;
  final String crop;
  final double areaHa;
  final String unitSystem;
  final DateTime createdAt;

  Map<String, Object?> toMap() => {
    'id': id, 'name': name, 'location': location, 'crop': crop,
    'area_ha': areaHa, 'unit_system': unitSystem,
    'created_at': createdAt.toIso8601String(),
  };

  factory IrrigationProject.fromMap(Map<String, Object?> map) =>
      IrrigationProject(
        id: map['id'] as String,
        name: map['name'] as String,
        location: map['location'] as String? ?? '',
        crop: map['crop'] as String? ?? '',
        areaHa: (map['area_ha'] as num).toDouble(),
        unitSystem: map['unit_system'] as String? ?? 'metric',
        createdAt: DateTime.parse(map['created_at'] as String),
      );
}
