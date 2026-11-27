import 'package:flutter/material.dart';

import '../../core/theme/app_theme.dart';
import 'sprinkler_calculator.dart';

class SprinklerScreen extends StatefulWidget {
  const SprinklerScreen({super.key});
  @override
  State<SprinklerScreen> createState() => _SprinklerScreenState();
}

class _SprinklerScreenState extends State<SprinklerScreen> {
  final _formKey = GlobalKey<FormState>();
  final _c = <String, TextEditingController>{
    'q': TextEditingController(text: '1000'),
    'p': TextEditingController(text: '4'),
    'pref': TextEditingController(text: '4'),
    'x': TextEditingController(text: '0.5'),
    'sx': TextEditingController(text: '12'),
    'sy': TextEditingController(text: '18'),
    'length': TextEditingController(text: '120'),
    'width': TextEditingController(text: '90'),
  };
  SprinklerResults? _result;
  String? _error;

  @override
  void dispose() {
    for (final item in _c.values) { item.dispose(); }
    super.dispose();
  }

  double _v(String key) => double.parse(_c[key]!.text);
  String? _validate(String? value) {
    final n = double.tryParse(value ?? '');
    return n == null || !n.isFinite || n <= 0
        ? 'Enter a number greater than zero' : null;
  }

  void _calculate() {
    if (!_formKey.currentState!.validate()) return;
    try {
      final result = SprinklerCalculator.calculate(SprinklerInputs(
        dischargeLitresPerHour: _v('q'),
        pressureBar: _v('p'),
        referencePressureBar: _v('pref'),
        pressureExponent: _v('x'),
        spacingAlongLateralM: _v('sx'),
        lateralSpacingM: _v('sy'),
        fieldLengthM: _v('length'),
        fieldWidthM: _v('width'),
      ));
      setState(() { _result = result; _error = null; });
    } on ArgumentError catch (e) {
      setState(() { _error = e.message.toString(); _result = null; });
    }
  }

  Widget _field(String key, String label, String unit) => Padding(
    padding: const EdgeInsets.only(bottom: 12),
    child: TextFormField(
      controller: _c[key],
      keyboardType: const TextInputType.numberWithOptions(decimal: true),
      validator: _validate,
      decoration: InputDecoration(labelText: label, suffixText: unit),
    ),
  );

  @override
  Widget build(BuildContext context) => Scaffold(
    appBar: AppBar(title: const Text('Sprinkler irrigation')),
    body: ListView(padding: const EdgeInsets.all(18), children: [
      const Text('Preliminary sprinkler layout estimate',
        style: TextStyle(fontSize: 20, fontWeight: FontWeight.w800,
          color: AppTheme.deepNavy)),
      const SizedBox(height: 6),
      const Text('Metric inputs. Enter the sprinkler catalogue discharge and its reference pressure.',
        style: TextStyle(color: Colors.black54)),
      const SizedBox(height: 18),
      Form(key: _formKey, child: Column(children: [
        _field('q', 'Sprinkler discharge at reference pressure', 'L/h'),
        _field('pref', 'Reference pressure', 'bar'),
        _field('p', 'Design operating pressure', 'bar'),
        _field('x', 'Discharge-pressure exponent', 'x'),
        _field('sx', 'Spacing along lateral', 'm'),
        _field('sy', 'Spacing between laterals', 'm'),
        _field('length', 'Field length', 'm'),
        _field('width', 'Field width', 'm'),
        SizedBox(width: double.infinity, child: FilledButton.icon(
          onPressed: _calculate,
          icon: const Icon(Icons.calculate_outlined),
          label: const Text('Calculate sprinkler layout'),
        )),
      ])),
      if (_error != null) ...[
        const SizedBox(height: 12),
        Text(_error!, style: const TextStyle(color: Colors.red)),
      ],
      if (_result != null) ...[
        const SizedBox(height: 22),
        const Text('Calculation results', style: TextStyle(
          fontSize: 18, fontWeight: FontWeight.w800, color: AppTheme.deepNavy)),
        const SizedBox(height: 10),
        _resultCard('Adjusted sprinkler discharge', _result!.adjustedDischargeLitresPerHour, 'L/h'),
        _resultCard('Estimated application rate', _result!.applicationRateMmPerHour, 'mm/h'),
        _resultCard('Sprinklers per lateral', _result!.sprinklersPerLateral.toDouble(), 'no.'),
        _resultCard('Number of laterals', _result!.lateralCount.toDouble(), 'no.'),
        _resultCard('Total sprinklers', _result!.totalSprinklers.toDouble(), 'no.'),
        _resultCard('Total system flow', _result!.totalFlowM3PerHour, 'm³/h'),
        const SizedBox(height: 12),
        const Text('Method and limitations', style: TextStyle(
          fontWeight: FontWeight.w700, color: AppTheme.deepNavy)),
        const SizedBox(height: 5),
        const Text('Adjusted discharge = reference discharge × (operating pressure / reference pressure)^x. Application rate = adjusted discharge (L/h) ÷ sprinkler spacing area (m²). The rectangular field count is estimated by rounding up field dimensions divided by spacing. This simple grid estimate does not account for boundary offsets, overlap, wind, topography, pressure variation along pipes, nozzle selection, soil infiltration or manufacturer layout limits. Confirm the discharge-pressure exponent and spacing criteria using the selected sprinkler manufacturer data.',
          style: TextStyle(color: Colors.black54, height: 1.45)),
      ],
    ]),
  );

  Widget _resultCard(String label, double value, String unit) => Card(
    margin: const EdgeInsets.only(bottom: 8),
    child: ListTile(
      title: Text(label),
      trailing: Text('${value.toStringAsFixed(2)} $unit',
        style: const TextStyle(fontWeight: FontWeight.w800,
          color: AppTheme.aquaTeal)),
    ),
  );
}
