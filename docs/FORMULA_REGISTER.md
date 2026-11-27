# Legacy Formula Verification Register

No legacy equation should be promoted to production until its units, assumptions and reference result have been independently checked.

| ID | Workflow | Required verification | Status |
|---|---|---|---|
| DR-01 | Net/gross irrigation depth | Effective rainfall, efficiency, crop basis and depth units | Open |
| DR-02 | Irrigation frequency | Available moisture, allowable depletion, crop demand | Open |
| DR-03 | Emitters per plant | Plant demand, emitter discharge and duration | Open |
| DR-04 | Wetted percentage | Geometry, overlap, plant and row spacing | Open |
| DR-05 | Drip set time | Depth/volume conversion, area, emitter count and flow | Open |
| DR-06 | Drip lateral diameter | Flow distribution, friction equation and head variation | Open |
| SP-01 | Sprinkler selection | Nozzle discharge, pressure and manufacturer data | Open |
| SP-02 | Sprinkler layout | Spacing, overlap, wind and application rate | Open |
| SP-03 | Pressure variation | Discharge-pressure exponent and percentage convention | Open |
| HY-01 | Mainline/manifold | Friction method, coefficient and unit convention | Open |
| HY-02 | Total dynamic head | Static lift, suction, delivery, losses and control head | Open |
| PU-01 | Pump power | Flow units, head, efficiency and service factors | Open |
| SYS-01 | System capacity | Area, depth, cycle, shifts and hours; dimensional analysis | Open |

## Known legacy items to re-check
- Total Dynamic Head appears to read suction lift for the control-head term a second time.
- System capacity appears to use different multiplication/division sequences in input and calculation screens.
- Sprinkler database upgrade SQL appears to use IF EXIST rather than IF EXISTS.
- Pump power needs flow-unit, efficiency and derating checks.
- Mainline headloss constants need confirmation against their unit convention.
- Pressure variation and allowable variation need assumptions and percentage conventions checked.

For each equation record the source file/method, governing reference, variables and units, assumptions, independent worked case, expected result/tolerance, edge tests, reviewer and approval status.

Status: Open = not approved; Verified = independently checked and tests approved; Rejected = unsuitable for migration.
