import 'unit_system.dart';

/// Converts display values to and from canonical SI calculation units.
///
/// Canonical values: metres, square metres, cubic metres per second,
/// pascals and metres of water depth.
class UnitConverter {
  static const _ftToM = 0.3048;
  static const _acreToM2 = 4046.8564224;
  static const _usGpmToM3s = 0.003785411784 / 60;
  static const _psiToPa = 6894.757293168;
  static const _inToM = 0.0254;

  static double lengthToSi(double value, UnitSystem system) =>
      system == UnitSystem.metric ? value : value * _ftToM;

  static double lengthFromSi(double metres, UnitSystem system) =>
      system == UnitSystem.metric ? metres : metres / _ftToM;

  static double areaToSi(double value, UnitSystem system) =>
      system == UnitSystem.metric ? value * 10000 : value * _acreToM2;

  static double areaFromSi(double squareMetres, UnitSystem system) =>
      system == UnitSystem.metric
          ? squareMetres / 10000
          : squareMetres / _acreToM2;

  static double flowToSi(double value, UnitSystem system) =>
      system == UnitSystem.metric ? value / 3600 : value * _usGpmToM3s;

  static double flowFromSi(double cubicMetresPerSecond, UnitSystem system) =>
      system == UnitSystem.metric
          ? cubicMetresPerSecond * 3600
          : cubicMetresPerSecond / _usGpmToM3s;

  static double pressureToSi(double value, UnitSystem system) =>
      system == UnitSystem.metric ? value * 1000 : value * _psiToPa;

  static double pressureFromSi(double pascals, UnitSystem system) =>
      system == UnitSystem.metric ? pascals / 1000 : pascals / _psiToPa;

  static double depthToSi(double value, UnitSystem system) =>
      system == UnitSystem.metric ? value / 1000 : value * _inToM;

  static double depthFromSi(double metres, UnitSystem system) =>
      system == UnitSystem.metric ? metres * 1000 : metres / _inToM;
}
