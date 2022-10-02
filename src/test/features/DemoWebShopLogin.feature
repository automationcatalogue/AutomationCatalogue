Feature: DemoWebShop Login Test

  @Login @DemoWebShop @All
  Scenario: Verifying login functionality of DemoWebShop
    Given User is loaded DemoWebShop website
    And User clicks on Login link on topmenu
    When User entered the userName
    And User entered the password
    Then User clicks on Login button
    And Validate whether Successful Login or not