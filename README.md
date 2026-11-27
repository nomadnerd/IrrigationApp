# AquaDesign – Water Engineering

**Engineering Water. Designing Solutions.**

AquaDesign is a cross-platform engineering application for irrigation design and, over time, broader water infrastructure calculations.

The original Android Java application is retained for audit and formula comparison. The modern Flutter application is being introduced alongside it.

## Current development preview

- AquaDesign dashboard and branding
- Local SQLite project creation, listing and deletion
- Preliminary drip-irrigation field estimate
- Metric-first inputs for the current drip workflow
- Shareable PDF calculation report
- Metric/imperial conversion utilities
- Unit and calculation tests

The drip calculation is a preliminary field-level estimate. It is not a complete irrigation design and does not size pipework or check hydraulic performance. See docs/FORMULA_REGISTER.md.

## Technology

Flutter, Dart, Riverpod foundation, SQLite (sqflite), PDF generation and printing/sharing.

## Set up locally

Install the stable Flutter SDK and Android Studio (Android SDK + emulator), then:

1. Clone this repository.
2. Open a terminal in the repository root.
3. Generate the Flutter Android and iOS platform folders:

       flutter create --platforms=android,ios --project-name aquadesign .

4. Fetch dependencies and run checks:

       flutter pub get
       flutter analyze
       flutter test

5. Run on a connected Android device or emulator:

       flutter run

6. Build an Android APK for manual testing:

       flutter build apk --release

The APK is created at:

       build/app/outputs/flutter-apk/app-release.apk

For iOS, use macOS with Xcode installed:

       flutter build ios --release

iOS signing and App Store distribution require an Apple developer setup. The current development environment has not yet produced a signed Android APK or iOS package.

## First-release roadmap

- Drip irrigation
- Sprinkler irrigation
- Hydraulic calculations and pump sizing
- Project-based reports and reference data
- Optional sync/backup

## Engineering notice

AquaDesign is a work in progress. Verify design inputs and outputs against applicable standards, project-specific criteria, manufacturer data and professional engineering judgement. No unverified legacy formula should be used as an approved design method.


Continuous integration checks run Flutter formatting analysis and unit tests on pushes to the development branch and pull requests to master.
