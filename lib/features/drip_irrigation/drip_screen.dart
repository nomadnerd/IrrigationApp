import 'package:flutter/material.dart';
import '../../core/theme/app_theme.dart';
import 'drip_calculator.dart';

class DripScreen extends StatefulWidget {
  const DripScreen({super.key});
  @override
  State<DripScreen> createState() => _DripScreenState();
}

class _DripScreenState extends State<DripScreen> {
  final _formKey = GlobalKey<FormState>();
  final _controllers = <String, TextEditingController>{
    'area': TextEditingController(text: '1'),
    'etc': TextEditingController(text: '5'),
    'rain': TextEditingController(text: '0'),
    'interval': TextEditingController(text: '2'),
    'efficiency': TextEditingController(text: '90'),
    'plants': TextEditingController(text: '2000'),
    'emitters': TextEditingController(text: '2'),
    'discharge': TextEditingController(text: '4'),
  };
  DripResults? _result;
  String? _error;

  @override
  void dispose() {
    for (final c in _controllers.values) { c.dispose(); }
    super.dispose();
  }

  double _number(String key) => double.parse(_controllers[key]!.text);

  String? _validate(String? value) {
    final n = double.tryParse(value ?? '');
    if (n == null || !n.isFinite || n < 0) return 'Enter a valid non-negative number';
    return null;
  }

  void _calculate() {
    if (!_formKey.currentState!.validate()) return;
    try {
      final result = DripCalculator.calculate(DripInputs(
        areaHa: _number('area'),
        etcMmPerDay: _number('etc'),
        effectiveRainMmPerDay: _number('rain'),
        intervalDays: _number('interval'),
        applicationEfficiencyPercent: _number('efficiency'),
        plantsPerHa: _number('plants'),
        emittersPerPlant: _number('emitters'),
        emitterDischargeLitresPerHour: _number('discharge'),
      ));
      setState(() { _result = result; _error = null; });
    } on ArgumentError catch (e) {
      setState(() { _error = e.message.toString(); _result = null; });
    }
  }

  Widget _field(String key, String label, String unit) => Padding(
    padding: const EdgeInsets.only(bottom: 12),
    child: TextFormField(
      controller: _controllers[key],
      keyboardType: const TextInputType.numberWithOptions(decimal: true),
      validator: _validate,
      decoration: InputDecoration(labelText: label, suffixText: unit),
    ),
  );

  @override
  Widget build(BuildContext context) => Scaffold(
    appBar: AppBar(title: const Text('Drip irrigation')),
    body: ListView(padding: const EdgeInsets.all(18), children: [
      const Text('Preliminary field irrigation estimate',
        style: TextStyle(fontSize: 20, fontWeight: FontWeight.w800, color: AppTheme.deepNavy)),
      const SizedBox(height: 6),
      const Text('Enter field and emitter data. Values are currently entered in metric units.',
        style: TextStyle(color: Colors.black54)),
      const SizedBox(height: 18),
      Form(key: _formKey, child: Column(children: [
        _field('area', 'Field area', 'ha'),
        _field('etc', 'Crop evapotranspiration (ETc)', 'mm/day'),
        _field('rain', 'Effective rainfall', 'mm/day'),
        _field('interval', 'Irrigation interval', 'days'),
        _field('efficiency', 'Application efficiency', '%'),
        _field('plants', 'Plant population', 'plants/ha'),
        _field('emitters', 'Emitters per plant', 'no.'),
        _field('discharge', 'Emitter discharge', 'L/h'),
        SizedBox(width: double.infinity, child: FilledButton.icon(
          onPressed: _calculate,
          icon: const Icon(Icons.calculate_outlined),
          label: const Text('Calculate irrigation requirement'),
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
        _resultCard('Net irrigation depth', _result!.netDepthMm, 'mm'),
        _resultCard('Gross irrigation depth', _result!.grossDepthMm, 'mm'),
        _resultCard('Gross field volume', _result!.volumeM3, 'm³'),
        _resultCard('Total emitters', _result!.emitterCount, 'emitters'),
        _resultCard('Total emitter flow', _result!.totalEmitterFlowM3PerHour, 'm³/h'),
        _resultCard('Estimated runtime', _result!.runtimeHours, 'hours'),
        const SizedBox(height: 12),
        const Text('Method and limitations',
          style: TextStyle(fontWeight: FontWeight.w700, color: AppTheme.deepNavy)),
        const SizedBox(height: 5),
        const Text('Net depth = max(ETc − effective rainfall, 0) × interval. Gross depth = net depth ÷ application efficiency. Volume = gross depth × area × 10. Runtime = field volume ÷ total emitter flow. Assumes all emitters operate simultaneously and uniformly. This estimate does not account for soil-water storage, allowable depletion, leaching, irrigation scheduling constraints, distribution uniformity or hydraulic losses. Verify design criteria before using for engineering decisions.',
          style: TextStyle(color: Colors.black54, height: 1.45)),
      ],
    ]),
  );

  Widget _resultCard(String label, double value, String unit) => Card(
    margin: const EdgeInsets.only(bottom: 8),
    child: ListTile(
      title: Text(label),
      trailing: Text('${value.toStringAsFixed(2)} $unit',
        style: const TextStyle(fontWeight: FontWeight.w800, color: AppTheme.aquaTeal)),
    ),
  );
}
