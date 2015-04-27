Feature: Check For New Sales

  Background:
    Given i enter email and password
    Given i answer the secret question
    Given i accept the conditions

  @Implemented
  Scenario: send email for new sales
    Given i goto sell view
    Given i goto loan parts sold view
    Given i sort by time sold desc
    Given i remember newly sold parts that have not been seen before
    Given if found, send an email notification for remembered parts
    Given save remembered parts for next time
    Given a break point
