# Framework architecture

```text
┌─────────────────────────────────────────────────────────┐
│                    demoqa-ui-tests                       │
│  BaseUiTest → Page Objects → TestNG (parallel)          │
└──────────────────────────┬──────────────────────────────┘
                           │ depends on
┌──────────────────────────▼──────────────────────────────┐
│                    framework-core                        │
│  Config │ DriverFactory │ Waits │ JsonDataReader        │
│  AllureListener │ RetryAnalyzer │ @TestCase              │
└──────────────────────────┬──────────────────────────────┘
                           │
         ┌─────────────────┴─────────────────┐
         ▼                                   ▼
   Local Chrome/Firefox              Docker Selenium Grid
```

## Design decisions

- **ThreadLocal WebDriver** — safe parallel method execution
- **JS click + React-friendly type helper** — stable on modern SPAs (DemoQA)
- **Separate API client class** — readable Rest Assured wrapper for interviews
- **Maven profiles** — smoke vs regression without duplicating code
- **config-{env}.properties** — overlay config for CI without secrets in repo

## CI pipeline

1. **api-tests** — fast feedback, no browser
2. **ui-smoke** — 2 parallel tests, headless Chrome
3. **ui-regression** — full suite after smoke passes
