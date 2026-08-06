@db
Feature: Database connectivity
  Scenario: Execute a validation query
    Given database credentials are configured
    When I execute a read-only validation query
    Then the query should return a result
