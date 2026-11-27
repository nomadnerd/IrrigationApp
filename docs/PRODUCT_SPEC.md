# AquaDesign – Product Specification

**Tagline:** Engineering Water. Designing Solutions.  
**First release:** AquaDesign Irrigation  
**Primary users:** Water engineers, irrigation designers and engineering consultants.

## Objective
Replace the 2017 Android Java application with a maintainable Android and iOS product. Start with drip and sprinkler irrigation, then expand into wider water-engineering workflows.

## Release 1
### Drip irrigation
- Project and field definition
- Crop and soil reference inputs
- Crop water requirement and irrigation depth
- Emitters per plant and emitter spacing
- Wetted area and percentage wetted
- Irrigation frequency and set time
- Lateral and manifold hydraulic checks after formula verification

### Sprinkler irrigation
- Sprinkler catalogue and selection
- Discharge, pressure and wetted diameter
- Sprinkler spacing and field layout
- Lateral and mainline design
- Pressure variation checks after formula verification

### Shared capabilities
- Project-based calculations
- Metric and imperial display
- Offline-first local storage
- Input validation and calculation assumptions
- PDF reports showing inputs, equations, substitutions and results

## Navigation
Dashboard | Projects | Irrigation | Hydraulics | Reports | References and settings

## Technology
Flutter/Dart, Riverpod, Drift/SQLite and PDF generation. Explicit project state replaces global mutable singleton values. Synchronisation/backup is optional and must not be required for offline use.

## Engineering quality
Every equation must have variable definitions, canonical units, assumptions, source, independently calculated reference cases and boundary tests. Unverified legacy equations must not be used as approved design methods. Reports must preserve the calculation inputs and equation versions.

## Brand
Aqua Teal #087E8B; Deep Navy #12304A; Flow Green #65B891; Light Grey #F1F5F7.

Proposed application identifier: com.aquadesign.engineering. Confirm ownership and store availability before release.
