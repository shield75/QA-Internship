Feature: Login feature

  Scenario: Login Scenario
    Given I open Login Page
    When I enter email "rumenul.rimon@testpro.io"
    And I enter Password "27041575"
    And I submit
    Then I am logged in