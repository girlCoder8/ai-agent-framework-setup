# Architecture

```text
Cucumber/JUnit test intent
        |
Hooks + dependency lifecycle
        |
+-------+---------+----------+----------+
| Web Driver      | API      | Database | Mobile Driver
| Selenium/pages  | clients  | JDBC     | Appium/screens
+-------+---------+----------+----------+
        | shared config, data, logging, assertions
        +---------------+----------------
                        Allure results
```

Each layer is independently executable, while end-to-end scenarios may compose all four through shared scenario context.
