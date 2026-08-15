# DemoQA Advanced Automation Framework

Advanced SDET portfolio — **DemoQA** UI automation and **Restful Booker** API automation with a reusable Java framework, parallel TestNG, Allure reporting, Docker Selenium Grid, and GitHub Actions CI/CD.

> Basic Sauce Demo project: [test-saucedemo](https://github.com/shivam9473/test-saucedemo)

## Why this is "advanced"

| Feature | Implementation |
|---------|----------------|
| Multi-module Maven | `framework-core`, `demoqa-ui-tests`, `booker-api-tests` |
| Reusable core | Driver factory (local + Grid), waits, JSON data reader, Allure listener |
| DemoQA UI | Forms, alerts, web tables — real-world widgets |
| API lifecycle | Auth token → create → read → update → delete + JSON schema |
| Parallel runs | TestNG `parallel="methods"` |
| Retry | Flaky-test retry analyzer |
| Data-driven | JSON test data for Text Box |
| Docker Grid | `docker-compose.yml` for Selenium Hub + Chrome nodes |
| CI/CD | Smoke → regression pipeline, Allure artifacts |

## Prerequisites

- JDK 17+
- Maven 3.9+
- Chrome (local) or Docker (Grid)

## Run locally

```bash
# Full build
mvn clean test

# UI smoke only (2 parallel threads)
mvn test -pl demoqa-ui-tests

# UI regression (all DemoQA tests, 3 threads)
mvn test -pl demoqa-ui-tests -Pregression

# Headless CI mode
mvn test -pl demoqa-ui-tests -Dheadless=true -Denv=ci

# API only
mvn test -pl booker-api-tests
```

## Allure report

```bash
cd demoqa-ui-tests
mvn test -Dheadless=true -Denv=ci -Pregression
mvn allure:serve
```

## Docker Selenium Grid (optional)

```bash
docker compose up -d
# set grid.url=http://localhost:4444/wd/hub in config.properties
mvn test -pl demoqa-ui-tests -Dgrid.url=http://localhost:4444/wd/hub
```

## Project structure

```text
demoqa-advanced-automation/
├── framework-core/       Shared driver, config, waits, Allure, retry, JSON data
├── demoqa-ui-tests/      Page objects + parallel UI tests (demoqa.com)
├── booker-api-tests/     Rest Assured + schema validation
├── docker-compose.yml    Selenium Grid
├── docs/                 Test plan + architecture notes
└── .github/workflows/    CI pipeline
```

## Test coverage

**UI (DemoQA)**
- Text Box — data-driven from JSON
- Practice Form — full form + modal validation
- Alerts — alert, confirm, prompt handling
- Web Tables — add row + search

**API (Restful Booker)**
- Ping health check
- Full booking CRUD with cookie auth
- JSON schema validation on create response

## Author

Shivam — SDET portfolio project.
