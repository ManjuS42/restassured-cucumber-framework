@CreateUserAPI @Sanity
Feature: Create User API

  Scenario: Create a new user
    Given user has valid user creation payload
    When user calls create user API
    Then status code from response should be 201
    And user id should be generated
    And response should match with user creation schema
