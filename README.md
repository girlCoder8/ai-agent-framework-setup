# Agentic Test Automation Framework SETUP

A starter repository for building and evolving a unified automation framework for Web, API, Database, and Mobile testing.

## Stack
- Java 21, Maven, JUnit 5, Cucumber
- Selenium 4 for Web
- REST Assured for APIs
- JDBC for Oracle/PostgreSQL/SQL Server-compatible validation
- Appium Java Client for iOS and Android
- Allure reporting
- Python utilities for test-data and result analysis
- Docker and GitHub Actions

## Agent workflow
Start with `agents/orchestrator/SKILL.md`. The orchestrator delegates to specialist skills, validates their outputs, and enforces the contracts in `AGENTS.md`.

## Quick start
```bash
cp .env.example .env
mvn -q -DskipTests package
mvn test -Dgroups=smoke
```

See `docs/ONBOARDING.md`, `docs/ARCHITECTURE.md`, and `docs/REPOSITORY_MAP.md`.
