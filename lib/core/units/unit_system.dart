/// Display preferences. Calculation engines will use documented canonical units.
enum UnitSystem { metric, imperial }

class UnitSystemLabels {
  static String length(UnitSystem system) => system == UnitSystem.metric ? 'm' : 'ft';
  static String area(UnitSystem system) => system == UnitSystem.metric ? 'ha' : 'ac';
  static String flow(UnitSystem system) => system == UnitSystem.metric ? 'm³/h' : 'US gpm';
  static String pressure(UnitSystem system) => system == UnitSystem.metric ? 'kPa' : 'psi';
  static String depth(UnitSystem system) => system == UnitSystem.metric ? 'mm' : 'in';
}
