#Author: bargan@your.domain.com
Feature: Television

  Scenario: Television Validation
    Given user launches flipkart webs
    And user login by entering valid crendentials in webs
    When user search Television
    And user click on the Television namee
    Then user validate the Television namee
