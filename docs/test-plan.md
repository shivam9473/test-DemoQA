# Test plan — DemoQA + Restful Booker

## Scope

### UI — DemoQA (https://demoqa.com)

| Area | Scenarios |
|------|-----------|
| Elements / Text Box | Data-driven submit + output validation |
| Forms / Practice Form | Required fields, subject, hobby, modal |
| Alerts | Alert, confirm, prompt dialogs |
| Widgets / Web Tables | Add record, search filter |

### API — Restful Booker

| Scenario | Endpoint flow |
|----------|---------------|
| Health | GET /ping |
| Auth | POST /auth |
| CRUD | POST → GET → PUT → DELETE /booking/{id} |
| Schema | Create response JSON schema check |

## Environments

| Profile | Config file | Notes |
|---------|-------------|-------|
| local | config.properties | Visible browser |
| ci | config-ci.properties | Headless, used in GitHub Actions |

## Exit criteria

- Smoke suite green on PR
- Regression suite green on main
- API lifecycle test passes end-to-end
