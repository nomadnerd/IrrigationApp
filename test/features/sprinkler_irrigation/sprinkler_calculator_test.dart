import 'package:flutter_test/flutter_test.dart';
import 'package:aquadesign/features/sprinkler_irrigation/sprinkler_calculator.dart';

void main() {
  group('SprinklerCalculator', () {
    test('calculates adjusted discharge, application rate and grid count', () {
      const input = SprinklerInputs(
        dischargeLitresPerHour: 1000,
        pressureBar: 4,
        referencePressureBar: 4,
        pressureExponent: 0.5,
        spacingAlongLateralM: 12,
        lateralSpacingM: 18,
        fieldLengthM: 120,
        fieldWidthM: 90,
      );
      final result = SprinklerCalculator.calculate(input);
      expect(result.adjustedDischargeLitresPerHour, closeTo(1000, 1e-9));
      expect(result.applicationRateMmPerHour, closeTo(1000 / 216, 1e-9));
      expect(result.sprinklersPerLateral, 10);
      expect(result.lateralCount, 5);
      expect(result.totalSprinklers, 50);
      expect(result.totalFlowM3PerHour, closeTo(50, 1e-9));
    });

    test('adjusts discharge using pressure exponent', () {
      const input = SprinklerInputs(
        dischargeLitresPerHour: 1000,
        pressureBar: 2,
        referencePressureBar: 4,
        pressureExponent: 0.5,
        spacingAlongLateralM: 10,
        lateralSpacingM: 10,
        fieldLengthM: 100,
        fieldWidthM: 100,
      );
      expect(SprinklerCalculator.calculate(input).adjustedDischargeLitresPerHour,
        closeTo(1000 * 0.5, 1e-9));
    });

    test('rejects zero spacing', () {
      const input = SprinklerInputs(
        dischargeLitresPerHour: 1000, pressureBar: 4,
        referencePressureBar: 4, pressureExponent: 0.5,
        spacingAlongLateralM: 0, lateralSpacingM: 10,
        fieldLengthM: 100, fieldWidthM: 100,
      );
      expect(() => SprinklerCalculator.calculate(input), throwsArgumentError);
    });
  });
}
