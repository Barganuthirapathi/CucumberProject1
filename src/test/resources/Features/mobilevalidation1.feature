#Author: uthira@your.domain.com
Feature: mobile

  Scenario: mobile Validation1
    Given user launches flipkart website
    And user login by entering valid input
    When user search mobile by one dim list
      | realme | redmi | SAMSUNG  |
    And user click on the mobile nam
    Then user validate the mobile nam

    
    