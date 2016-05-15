Feature: Sell Loan Parts

#  Background:
  @Implemented
  Scenario Outline: Delist loan parts
    Given i open funding circle
    Given i enter email and password for "<profile>"
    Given i answer the secret question for "<profile>"
    Given i accept the conditions

    Given i goto sell view
    Given i goto loan parts for sale view
    Given i select 250 items per page
    Given there are items on sale
    Given i select delist for all records in the view
    Given i delist the selected parts

  Examples:
  | profile   |
#  |  tim      |
  |  solubris |
