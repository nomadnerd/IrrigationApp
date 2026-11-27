import 'package:flutter_test/flutter_test.dart';
import 'package:aquadesign/core/units/unit_converter.dart';
import 'package:aquadesign/core/units/unit_system.dart';

void main() {
  group('UnitConverter', () {
    test('converts feet to metres and back', () {
      final si = UnitConverter.lengthToSi(10, UnitSystem.imperial);
      expect(si, closeTo(3.048, 1e-10));
      expect(UnitConverter.lengthFromSi(si, UnitSystem.imperial), closeTo(10, 1e-10));
    });

    test('converts acres to hectares display basis and back', () {
      final si = UnitConverter.areaToSi(1, UnitSystem.imperial);
      expect(si, closeTo(4046.8564224, 1e-7));
      expect(UnitConverter.areaFromSi(si, UnitSystem.imperial), closeTo(1, 1e-10));
    });

    test('converts US gallons per minute to cubic metres per second', () {
      final si = UnitConverter.flowToSi(1, UnitSystem.imperial);
      expect(si, closeTo(0.003785411784 / 60, 1e-12));
      expect(UnitConverter.flowFromSi(si, UnitSystem.imperial), closeTo(1, 1e-10));
    });

    test('converts psi to pascals and back', () {
      final si = UnitConverter.pressureToSi(1, UnitSystem.imperial);
      expect(si, closeTo(6894.757293168, 1e-8));
      expect(UnitConverter.pressureFromSi(si, UnitSystem.imperial), closeTo(1, 1e-10));
    });

    test('converts inches to metres and back', () {
      final si = UnitConverter.depthToSi(1, UnitSystem.imperial);
      expect(si, closeTo(0.0254, 1e-12));
      expect(UnitConverter.depthFromSi(si, UnitSystem.imperial), closeTo(1, 1e-10));
    });

    test('converts metric area from hectares to square metres', () {
      expect(UnitConverter.areaToSi(1, UnitSystem.metric), closeTo(10000, 1e-10));
      expect(UnitConverter.areaFromSi(10000, UnitSystem.metric), closeTo(1, 1e-10));
    });
  });
}
