# AquaDesign Development Status

Updated: 27 November 2026

## Implemented in the current preview branch

- Flutter application entry point and AquaDesign Material 3 dashboard.
- Local SQLite project create/list/delete workflow.
- Preliminary drip irrigation estimate: net/gross depth, gross volume, emitter count, total emitter flow and estimated runtime.
- PDF export/share for the drip estimate, including inputs, equations, assumptions and limitations.
- Preliminary sprinkler estimate: pressure-adjusted discharge, application rate, rectangular-grid sprinkler count and total flow.
- SI conversion utilities for metric/imperial input/display integration.
- Unit tests for conversion and the two preliminary calculation engines.
- GitHub Actions workflow for Flutter analysis, unit tests and Android debug build.

## Not yet implemented

- Full crop/soil water-balance and irrigation scheduling workflow.
- Drip and sprinkler pipe network design, friction losses, pressure variation, uniformity and hydraulic checks.
- Pump selection, total dynamic head and motor power design.
- Full imperial-mode UI and user-selectable unit preference.
- Project-to-calculation linking, project editing and calculation history.
- Sprinkler PDF report and combined project report.
- Cloud synchronisation/backup.
- Final logo assets, app icon, release signing and store deployment.
- iOS build and physical-device verification.

## Validation

The first GitHub Actions analyzer run found three Dart errors. Those were corrected on the development branch. The newer validation runs are queued; a passing analyzer/test/build result has not yet been confirmed. No APK or iOS package has been released from this branch.

## Engineering review gate

The current irrigation equations are preliminary estimates only. They must be independently reviewed against authoritative irrigation design references, crop and soil criteria, equipment manufacturer data, applicable standards and representative hand calculations before the outputs are used as professional design results.
