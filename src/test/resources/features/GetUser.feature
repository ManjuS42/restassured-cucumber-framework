@GetUserAPI @Sanity
Feature: Get User API

  Scenario: Fetch users list
    When user fetches users for page 1
    Then status code should be 200
    And response should match get users schema
