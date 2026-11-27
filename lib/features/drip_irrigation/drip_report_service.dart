import 'package:pdf/pdf.dart';
import 'package:pdf/widgets.dart' as pw;
import 'package:printing/printing.dart';

import 'drip_calculator.dart';

class DripReportService {
  static Future<void> share({
    required DripInputs inputs,
    required DripResults results,
  }) async {
    final doc = pw.Document();
    doc.addPage(pw.MultiPage(
      pageFormat: PdfPageFormat.a4,
      margin: const pw.EdgeInsets.all(36),
      build: (context) => [
        pw.Text('AquaDesign – Drip Irrigation Estimate',
          style: pw.TextStyle(fontSize: 20, fontWeight: pw.FontWeight.bold)),
        pw.SizedBox(height: 5),
        pw.Text('Engineering Water. Designing Solutions.',
          style: const pw.TextStyle(fontSize: 9, color: PdfColors.blueGrey)),
        pw.SizedBox(height: 8),
        pw.Text('Generated: ${DateTime.now().toIso8601String()}'),
        pw.Divider(),
        pw.Header(level: 1, text: 'Design inputs'),
        _table([
          ['Parameter', 'Value', 'Unit'],
          ['Field area', inputs.areaHa.toStringAsFixed(3), 'ha'],
          ['Crop evapotranspiration', inputs.etcMmPerDay.toStringAsFixed(3), 'mm/day'],
          ['Effective rainfall', inputs.effectiveRainMmPerDay.toStringAsFixed(3), 'mm/day'],
          ['Irrigation interval', inputs.intervalDays.toStringAsFixed(3), 'days'],
          ['Application efficiency', inputs.applicationEfficiencyPercent.toStringAsFixed(2), '%'],
          ['Plant population', inputs.plantsPerHa.toStringAsFixed(1), 'plants/ha'],
          ['Emitters per plant', inputs.emittersPerPlant.toStringAsFixed(1), 'no.'],
          ['Emitter discharge', inputs.emitterDischargeLitresPerHour.toStringAsFixed(3), 'L/h'],
        ]),
        pw.Header(level: 1, text: 'Calculated results'),
        _table([
          ['Result', 'Value', 'Unit'],
          ['Net irrigation depth', results.netDepthMm.toStringAsFixed(3), 'mm'],
          ['Gross irrigation depth', results.grossDepthMm.toStringAsFixed(3), 'mm'],
          ['Gross field volume', results.volumeM3.toStringAsFixed(3), 'm³'],
          ['Total emitter count', results.emitterCount.toStringAsFixed(0), 'emitters'],
          ['Total emitter flow', results.totalEmitterFlowM3PerHour.toStringAsFixed(3), 'm³/h'],
          ['Estimated runtime', results.runtimeHours.toStringAsFixed(3), 'hours'],
        ]),
        pw.Header(level: 1, text: 'Calculation method'),
        pw.Paragraph(text: 'Net depth = max(ETc − effective rainfall, 0) × irrigation interval.'),
        pw.Paragraph(text: 'Gross depth = net depth ÷ (application efficiency / 100).'),
        pw.Paragraph(text: 'Gross volume (m³) = gross depth (mm) × area (ha) × 10.'),
        pw.Paragraph(text: 'Total emitters = area (ha) × plant population (plants/ha) × emitters per plant.'),
        pw.Paragraph(text: 'Total emitter flow (m³/h) = total emitters × emitter discharge (L/h) ÷ 1,000.'),
        pw.Paragraph(text: 'Runtime (hours) = gross field volume ÷ total emitter flow.'),
        pw.Header(level: 1, text: 'Assumptions and limitations'),
        pw.Bullet(text: 'ETc and effective rainfall are daily average depths over the irrigation interval.'),
        pw.Bullet(text: 'Application efficiency is entered as a percentage.'),
        pw.Bullet(text: 'All emitters are assumed to operate simultaneously at the stated discharge.'),
        pw.Bullet(text: 'This estimate does not calculate soil-water depletion, allowable depletion, leaching, irrigation scheduling constraints, distribution uniformity or pipe hydraulics.'),
        pw.SizedBox(height: 12),
        pw.Text('Engineering review required',
          style: pw.TextStyle(fontWeight: pw.FontWeight.bold, color: PdfColors.red800)),
        pw.Text('This preliminary calculation is an engineering aid, not a complete irrigation design. Verify project criteria, crop and soil data, water quality, equipment characteristics, applicable standards and hydraulic performance before implementation.'),
        pw.SizedBox(height: 18),
        pw.Divider(),
        pw.Text('AquaDesign • Preliminary calculation report',
          style: const pw.TextStyle(fontSize: 8, color: PdfColors.grey700)),
      ],
    ));
    await Printing.sharePdf(
      bytes: await doc.save(),
      filename: 'aquadesign_drip_estimate.pdf',
    );
  }

  static pw.Widget _table(List<List<String>> rows) => pw.TableHelper.fromTextArray(
    data: rows,
    headerStyle: pw.TextStyle(fontWeight: pw.FontWeight.bold, color: PdfColors.white),
    headerDecoration: const pw.BoxDecoration(color: PdfColors.blueGrey800),
    cellStyle: const pw.TextStyle(fontSize: 9),
    cellPadding: const pw.EdgeInsets.all(6),
    border: pw.TableBorder.all(color: PdfColors.grey300, width: 0.5),
  );
}
