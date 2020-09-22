@patientlogin
Feature: Power2Patient Login
  Background:
    Given the Power2Patient test Login page is loaded
  @plogin
  Scenario: User can successfully login to patient site
    When  the user enters username field in power2patient login page
    And the user enters "valid" password field in power2patient login page
    And the user selects the sign in button in power2patient login page
    Then the user should be successfully logged into the power2patient home page
  @smoketest
  Scenario: User can see all the elements on the patient site
    And user can see Power2Practice Logo
    And user can see Forgot Username? link
    And user can see Forgot Password? link
    And user can see Agreement text
    And user can see Register Here link
    And user can see Terms of Use link

  @incorrectLogin
  Scenario: User can not login to patient portal with Invalid Login
    When  the user enters username field in power2patient login page
    And the user enters "invalid" password field in power2patient login page
    And the user selects the sign in button in power2patient login page
    Then the user should see error message and remain on Login page
