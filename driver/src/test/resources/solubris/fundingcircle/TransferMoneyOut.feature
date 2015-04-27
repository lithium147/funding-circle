Feature: Transfer Money Out

  @Implemented
  Scenario Outline: login with profile
    Given i open funding circle
    Given i enter email and password for "<profile>"
    Given i answer the secret question for "<profile>"
    Given i accept the conditions

    Given i goto transfer money
    Given i goto transfer out
    Given i have available funds greater than £20
    When i enter the available funds in the amount
    And i enter account details for "<profile>"
    And i click to transfer funds
    Then i have available funds equal to £0

  Examples:
  | profile   |
  |  tim      |
  |  solubris |
