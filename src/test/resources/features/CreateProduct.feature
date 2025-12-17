@Authentication @Sanity
Feature: Create Product API

  Scenario: Create product with valid token
    Given user has valid product payload
    When user calls create product API
    Then verify status code should be 201
    And verify product response details
    And response should match create product schema

