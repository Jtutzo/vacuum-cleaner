Feature: An user - create a vacuum cleaner
  An user create a new vacuum cleaner

  Scenario: should create a new vacuum cleaner
    When i create a vacuum cleaner:
      | name            | my vacuum cleaner |
      | positionEndGrid | 10,10             |
      | position        | 5,5               |
      | orientation     | W                 |
    Then there is no error
    And the vacuum cleaner is created

  Scenario: should throw an exception when the vacuum cleaner is already created
    Given a vacuum cleaner already created:
      | name            | my vacuum cleaner |
      | positionEndGrid | 10,10             |
      | position        | 5,5               |
      | orientation     | W                 |
    When i create a vacuum cleaner:
      | name            | my vacuum cleaner |
      | positionEndGrid | 14,12             |
      | position        | 1,0               |
      | orientation     | N                 |
    Then there is an error with message "The vacuum cleaner my vacuum cleaner is already created"
    And the vacuum cleaner is not created
