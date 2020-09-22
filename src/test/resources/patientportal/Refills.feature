@refill
Feature: Power2Patient Login
  Background:
    Given the Power2Patient test Login page is loaded

    Scenario: User can submit a Refill
      When  the user enters username field in power2patient login page
      And the user enters "valid" password field in power2patient login page
      And the user selects the sign in button in power2patient login page
      Then the user should be successfully logged into the power2patient home page
      And the user selects the Refills link
      Then the user should be on the Refills page
      When the user selects the first prescription in the Refills page
      And the user adds "Automated Test - refill Notes" to the first prescription Note to Office field in the Refills page
      And the user selects the submit button in the Refills page
      Then the user should be redirected to the Home page







