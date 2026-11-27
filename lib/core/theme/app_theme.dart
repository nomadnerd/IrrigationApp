import 'package:flutter/material.dart';

class AppTheme {
  static const aquaTeal = Color(0xFF087E8B);
  static const deepNavy = Color(0xFF12304A);
  static const flowGreen = Color(0xFF65B891);
  static const lightGrey = Color(0xFFF1F5F7);

  static ThemeData get light {
    final scheme = ColorScheme.fromSeed(
      seedColor: aquaTeal,
      brightness: Brightness.light,
      primary: aquaTeal,
      secondary: flowGreen,
      surface: Colors.white,
    );
    return ThemeData(
      useMaterial3: true,
      colorScheme: scheme,
      scaffoldBackgroundColor: lightGrey,
      appBarTheme: const AppBarTheme(
        backgroundColor: lightGrey,
        foregroundColor: deepNavy,
        elevation: 0,
      ),
      cardTheme: CardThemeData(
        color: Colors.white,
        elevation: 0,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(18),
          side: BorderSide(color: deepNavy.withValues(alpha: 0.07)),
        ),
      ),
    );
  }
}
