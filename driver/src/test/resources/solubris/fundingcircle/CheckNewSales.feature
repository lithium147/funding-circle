Feature: Check For New Sales

#  @Implemented
  Scenario Outline: login with profile
    Given i open funding circle
    Given i enter email and password for "<profile>"
    Given i answer the secret question for "<profile>"
    Given i accept the conditions

    Given i goto sell view
    Given i goto loan parts sold view
    Given i sort by time sold desc
    Given i remember newly sold parts that have not been seen before
    Given if found, send an email notification for remembered parts
    Given save remembered parts for next time
    Given a break point

    Examples:
      | profile   |
      |  tim      |
      |  solubris |
