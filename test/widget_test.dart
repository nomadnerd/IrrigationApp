import 'package:flutter_test/flutter_test.dart';
import 'package:aquadesign/app.dart';

void main() {
  testWidgets('AquaDesign dashboard launches', (tester) async {
    await tester.pumpWidget(const AquaDesignApp());
    expect(find.text('AquaDesign'), findsOneWidget);
    expect(find.text('Irrigation design'), findsOneWidget);
  });
}
