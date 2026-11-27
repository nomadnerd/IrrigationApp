import 'package:flutter/material.dart';
import 'core/theme/app_theme.dart';
import 'features/dashboard/dashboard_screen.dart';

class AquaDesignApp extends StatelessWidget {
  const AquaDesignApp({super.key});

  @override
  Widget build(BuildContext context) => MaterialApp(
    title: 'AquaDesign',
    debugShowCheckedModeBanner: false,
    theme: AppTheme.light,
    home: const DashboardScreen(),
  );
}
