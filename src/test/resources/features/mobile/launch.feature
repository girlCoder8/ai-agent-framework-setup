@mobile
Feature: Mobile launch
  Scenario: Launch configured application
    Given a mobile device is configured
    When I start the application session
    Then the mobile session should be active
