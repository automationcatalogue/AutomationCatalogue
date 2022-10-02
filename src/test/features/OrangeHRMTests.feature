Feature: OrangeHRM Testcases validation

  @AddEmployee @OrangeHRM @All
  Scenario: AddEmployee Scenario
    Given User is loaded the OrangeHRM Website
    When User is Logged in with Credentials
    Then Verify OrangeHRM Login
    And User Clicks on AddEmployee in EmployeeList
    When User performs the AddEmployee
    Then User Verifies AddEmployeeData
    And User log out from the OrangeHRM
    And User Closes Browser



