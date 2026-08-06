# Developer Onboarding

1. Install Java 21, Maven 3.9+, Docker, Python 3.12, Node/Appium for mobile work.
2. Copy `.env.example` to `.env`; use a secret manager in CI.
3. Run `mvn test -Dcucumber.filter.tags="@api"` for a non-UI smoke check.
4. Start Selenium Grid with `docker compose up -d selenium-hub chrome` when remote browser execution is needed.
5. Start Appium separately for mobile tests and set capability values in environment variables.
6. Review `AGENTS.md` before using or modifying an agent skill.
