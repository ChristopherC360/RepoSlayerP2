Feature: Insurance Application features work

Scenario: Login work
    Given The User is on the Login Page
    When The User inputs information and clicks Login
    Then The User should be on the Dashboard Page

Scenario: Total Claims Button Works
    Given The User is on the Dashboard
    When The User clicks the total claims button
    Then The User should see the claims table

Scenario: Age/Country Button Works
    Given The User is on the Dashboard
    When The User clicks the age/country button
    Then The User should see the age/country table

Scenario: Insurance Reasons Button Works
    Given The User is on the Dashboard
    When The User clicks the insurance reasons button
    Then The User should see the insurance reasons table