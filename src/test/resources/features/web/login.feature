@web
Feature: Web login
  Scenario: Login page is reachable
    Given the web application is configured
    When I open the login page
    Then the page should load successfully
