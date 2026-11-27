class DripInputs {
  const DripInputs({
    required this.areaHa,
    required this.etcMmPerDay,
    required this.effectiveRainMmPerDay,
    required this.intervalDays,
    required this.applicationEfficiencyPercent,
    required this.plantsPerHa,
    required this.emittersPerPlant,
    required this.emitterDischargeLitresPerHour,
  });

  final double areaHa;
  final double etcMmPerDay;
  final double effectiveRainMmPerDay;
  final double intervalDays;
  final double applicationEfficiencyPercent;
  final double plantsPerHa;
  final double emittersPerPlant;
  final double emitterDischargeLitresPerHour;
}

class DripResults {
  const DripResults({
    required this.netDepthMm,
    required this.grossDepthMm,
    required this.volumeM3,
    required this.emitterCount,
    required this.totalEmitterFlowM3PerHour,
    required this.runtimeHours,
  });

  final double netDepthMm;
  final double grossDepthMm;
  final double volumeM3;
  final double emitterCount;
  final double totalEmitterFlowM3PerHour;
  final double runtimeHours;
}

class DripCalculator {
  /// Preliminary field-level irrigation estimate.
  ///
  /// Assumptions: ETc and effective rainfall are daily averages over the
  /// irrigation interval; application efficiency is entered as percent;
  /// every plant has the same emitter count and discharge; all emitters
  /// operate simultaneously. Does not size pipes or check hydraulics.
  static DripResults calculate(DripInputs input) {
    final values = [
      input.areaHa, input.etcMmPerDay, input.effectiveRainMmPerDay,
      input.intervalDays, input.applicationEfficiencyPercent,
      input.plantsPerHa, input.emittersPerPlant,
      input.emitterDischargeLitresPerHour,
    ];
    if (values.any((v) => !v.isFinite) ||
        input.areaHa <= 0 ||
        input.etcMmPerDay < 0 ||
        input.effectiveRainMmPerDay < 0 ||
        input.intervalDays <= 0 ||
        input.applicationEfficiencyPercent <= 0 ||
        input.applicationEfficiencyPercent > 100 ||
        input.plantsPerHa <= 0 ||
        input.emittersPerPlant <= 0 ||
        input.emitterDischargeLitresPerHour <= 0) {
      throw ArgumentError('Check the inputs: area, interval, plant density, emitter count and discharge must be positive; efficiency must be above 0 and no more than 100%.');
    }

    final dailyNet = (input.etcMmPerDay - input.effectiveRainMmPerDay).clamp(0, double.infinity).toDouble();
    final netDepth = dailyNet * input.intervalDays;
    final grossDepth = netDepth / (input.applicationEfficiencyPercent / 100);
    // 1 mm over 1 ha = 10 m³.
    final volume = grossDepth * input.areaHa * 10;
    final plants = input.areaHa * input.plantsPerHa;
    final emitterCount = plants * input.emittersPerPlant;
    final totalFlow = emitterCount * input.emitterDischargeLitresPerHour / 1000;
    final runtime = totalFlow > 0 ? volume / totalFlow : 0;

    return DripResults(
      netDepthMm: netDepth,
      grossDepthMm: grossDepth,
      volumeM3: volume,
      emitterCount: emitterCount,
      totalEmitterFlowM3PerHour: totalFlow,
      runtimeHours: runtime,
    );
  }
}
