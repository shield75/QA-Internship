Feature: Update Email

  Background:
    Given the user is logged into the koel app and is in the profile and preference page

  Scenario: Enter an invalid email without "@" symbol
    When the user updates the email to "invalidtestpro.io"
    Then the message "Please include an '@' in the email address. invalidtestpro.io is missing an '@'." should be displayed
    And the email should not be updated

  Scenario: Enter an invalid email without a domain
    When the user updates the email to "user@domain"
    Then the message "Profile updated." should not be displayed
    And the email should not be updated

  Scenario: Enter an invalid email without a dot in the domain
    When the user updates the email to "user@domaincom"
    Then the message "Profile updated." should not be displayed
    And the email should not be updated

  Scenario: Enter an email with "+" sign before "@"
    When the user updates the email to "user+@example.com"
    Then the message "Profile updated." should not be displayed
    And the email should not be updated

  Scenario: Enter an email already in the database
    When the user updates the email to an existing email address from the database
    Then the message "The email has already been taken." should be displayed
    And the email should not be updated

  Scenario: Update email with a valid email address
    When the user updates the email to "valid.email@testpro.io"
    Then the message "Profile updated." should be displayed

  Scenario: User should be able to log in with the updated email
    Given the user has updated their email to "valid.email@testpro.io"
    When the user logs out
    And the user logs in with "valid.email@testpro.io"
    Then the user should be able to log in

  Scenario: User should not be able to log in with the old email
    Given the user has updated their email from "old.email@testpro.io" to "new.email@testpro.io"
    When the user logs out
    And the user logs in with "old.email@testpro.io"
    Then the user should not be able to log in

  Scenario: Updated email should be correctly saved to the database
    Given the user has updated their email to "new.email@testpro.io"
    When the user checks their account details in the database
    Then the email should be "new.email@testpro.io"