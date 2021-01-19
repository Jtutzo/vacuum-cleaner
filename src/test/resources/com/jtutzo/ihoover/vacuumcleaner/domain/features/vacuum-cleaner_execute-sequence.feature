Feature: A vacuum cleaner - execute sequence
  A vacuum cleaner execute the sequence asked

  Scenario: should execute the sequence asked
    Given i create an vacuum cleaner with "10" and "10" as position grid and "5", "5" and "N" as initial position
    When execute the sequence "DADADADAA"
    Then the new position of vacuum cleaner is "5", "6" and "N"