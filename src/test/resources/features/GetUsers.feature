@GetUsersAPI @Regression
Feature: Get Users API

  Scenario: Fetch users list
    When user fetches users for page 2
    Then status code should be 200
