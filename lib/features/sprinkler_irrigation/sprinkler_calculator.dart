import 'dart:math' as _math;

class SprinklerInputs {
  const SprinklerInputs({
    required this.dischargeLitresPerHour,
    required this.pressureBar,
    required this.referencePressureBar,
    required this.pressureExponent,
    required this.spacingAlongLateralM,
    required this.lateralSpacingM,
    required this.fieldLengthM,
    required this.fieldWidthM,
  });

  final double dischargeLitresPerHour;
  final double pressureBar;
  final double referencePressureBar;
  final double pressureExponent;
  final double spacingAlongLateralM;
  final double lateralSpacingM;
  final double fieldLengthM;
  final double fieldWidthM;
}

class SprinklerResults {
  const SprinklerResults({
    required this.adjustedDischargeLitresPerHour,
    required this.applicationRateMmPerHour,
    required this.sprinklersPerLateral,
    required this.lateralCount,
    required this.totalSprinklers,
    required this.totalFlowM3PerHour,
  });

  final double adjustedDischargeLitresPerHour;
  final double applicationRateMmPerHour;
  final int sprinklersPerLateral;
  final int lateralCount;
  final int totalSprinklers;
  final double totalFlowM3PerHour;
}

class SprinklerCalculator {
  /// Preliminary rectangular-grid estimate, not a complete sprinkler design.
  ///
  /// Discharge-pressure relationship: q2 = q1 * (p2 / p1)^x.
  /// Application rate assumes one sprinkler serves the rectangular area
  /// defined by the two spacings, with no edge/overlap correction.
  static SprinklerResults calculate(SprinklerInputs input) {
    final values = [
      input.dischargeLitresPerHour, input.pressureBar,
      input.referencePressureBar, input.pressureExponent,
      input.spacingAlongLateralM, input.lateralSpacingM,
      input.fieldLengthM, input.fieldWidthM,
    ];
    if (values.any((v) => !v.isFinite) ||
        input.dischargeLitresPerHour <= 0 ||
        input.pressureBar <= 0 ||
        input.referencePressureBar <= 0 ||
        input.pressureExponent <= 0 ||
        input.spacingAlongLateralM <= 0 ||
        input.lateralSpacingM <= 0 ||
        input.fieldLengthM <= 0 ||
        input.fieldWidthM <= 0) {
      throw ArgumentError('All sprinkler inputs must be finite and greater than zero.');
    }

    final adjusted = input.dischargeLitresPerHour *
        _pow(input.pressureBar / input.referencePressureBar, input.pressureExponent);
    final applicationRate = adjusted /
        (input.spacingAlongLateralM * input.lateralSpacingM);
    final perLateral = (input.fieldLengthM / input.spacingAlongLateralM).ceil();
    final laterals = (input.fieldWidthM / input.lateralSpacingM).ceil();
    final total = perLateral * laterals;

    return SprinklerResults(
      adjustedDischargeLitresPerHour: adjusted,
      applicationRateMmPerHour: applicationRate,
      sprinklersPerLateral: perLateral,
      lateralCount: laterals,
      totalSprinklers: total,
      totalFlowM3PerHour: total * adjusted / 1000,
    );
  }

  static double _pow(double base, double exponent) {
    // Dart's num.pow is supplied by dart:math.
    return _math.pow(base, exponent).toDouble();
  }
}

