@api @smoke
Feature: API health
  Scenario: Service responds
    Given the API service is configured
    When I request the health endpoint
    Then the API response status should be 200
