@api @smoke @wip
Feature: API health

  # @wip: no live service or stub is wired up in CI yet.
  # Remove this tag once API_BASE_URL points at something real
  # (a stub server started in ci.yml, or a deployed environment URL).
  Scenario: Service responds
    Given the API service is configured
    When I request the health endpoint
    Then the API response status should be 200
