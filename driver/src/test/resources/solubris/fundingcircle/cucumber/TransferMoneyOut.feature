Feature: Transfer Money Out

  @CI
  Scenario Outline: Transfer Money Out
    Given i open funding circle
    Given i enter email and password for "<profile>"
    Given i answer the secret question for "<profile>"
    Given i accept the conditions

    Given i have available funds between £20 and £1000
    Given i goto transfer money
    Given i goto transfer out
    Given i select the first nominated bank account
    When i enter the available funds in the amount
    And i enter account details for "<profile>"
    And i click to transfer funds
    Then i have available funds equal to £0

  Examples:
  | profile   |
  |  tim      |
  |  solubris |
