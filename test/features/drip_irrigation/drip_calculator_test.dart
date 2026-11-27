import 'package:flutter_test/flutter_test.dart';
import 'package:aquadesign/features/drip_irrigation/drip_calculator.dart';

void main() {
  group('DripCalculator', () {
    test('calculates field depth, volume and emitter runtime', () {
      const input = DripInputs(
        areaHa: 1,
        etcMmPerDay: 5,
        effectiveRainMmPerDay: 1,
        intervalDays: 2,
        applicationEfficiencyPercent: 90,
        plantsPerHa: 2000,
        emittersPerPlant: 2,
        emitterDischargeLitresPerHour: 4,
      );
      final result = DripCalculator.calculate(input);
      expect(result.netDepthMm, closeTo(8, 1e-10));
      expect(result.grossDepthMm, closeTo(8.8888888889, 1e-8));
      expect(result.volumeM3, closeTo(88.8888888889, 1e-7));
      expect(result.emitterCount, 4000);
      expect(result.totalEmitterFlowM3PerHour, 16);
      expect(result.runtimeHours, closeTo(5.5555555556, 1e-8));
    });

    test('rejects efficiency above 100 percent', () {
      const input = DripInputs(
        areaHa: 1, etcMmPerDay: 5, effectiveRainMmPerDay: 0,
        intervalDays: 1, applicationEfficiencyPercent: 110,
        plantsPerHa: 1000, emittersPerPlant: 1,
        emitterDischargeLitresPerHour: 2,
      );
      expect(() => DripCalculator.calculate(input), throwsArgumentError);
    });

    test('rainfall greater than crop demand does not create negative irrigation', () {
      const input = DripInputs(
        areaHa: 1, etcMmPerDay: 3, effectiveRainMmPerDay: 5,
        intervalDays: 2, applicationEfficiencyPercent: 90,
        plantsPerHa: 1000, emittersPerPlant: 1,
        emitterDischargeLitresPerHour: 2,
      );
      expect(DripCalculator.calculate(input).netDepthMm, 0);
    });
  });
}
