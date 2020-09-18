@patientlogin
Feature: Power2Patient Login
  Background:
    Given the Power2Patient test Login page is loaded
  Scenario: User can successfully login to patient site
    When  the user enters username field in power2patient login page
    And the user enters password field in power2patient login page
    And the user selects the sign in button in power2patient login page
    Then the user should be successfully logged into the power2patient home page