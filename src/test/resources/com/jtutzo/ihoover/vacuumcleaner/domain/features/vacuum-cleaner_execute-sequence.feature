Feature: A vacuum cleaner - execute sequence
  A vacuum cleaner execute the asked sequence

  Scenario Outline: should execute the asked sequence
    Given I create an vacuum cleaner with "<gridSize>" as size grid, "<initialPosition>" as initial position and "<initialOrientation>" as initial orientation
    When execute the sequence "<sequence>"
    Then the new position of vacuum cleaner is "<finalPosition>"
    And the new orientation of vacuum cleaner is "<finalOrientation>"
    Examples:
      | gridSize | initialPosition | initialOrientation | sequence         | finalPosition | finalOrientation |
      | 10,10    | 5,5             | N                  | DADADADAA        | 5,6           | N                |
      | 20,20    | 10,10           | N                  | DADADADAA        | 10,11         | N                |
      | 10,10    | 0,0             | N                  | AADAGAGADDAADA   | 2,2           | S                |
      | 8,5      | 4,3             | S                  | DADADADAGAGAGAGA | 4,3           | S                |
      | 10,10    | 4,3             | S                  | GAAGADAGAAGGAAG  | 7,4           | W                |

  Scenario Outline: throw an exception when the vacuum cleaner position is out of the grid
    Given I create an vacuum cleaner with "<gridSize>" as size grid, "<initialPosition>" as initial position and "<initialOrientation>" as initial orientation
    When execute the sequence "<sequence>"
    Then should display "The position is out of the grid." error
    Examples:
      | gridSize | initialPosition | initialOrientation| sequence         |
      | 10,10    | 5,10            | N                 | DADADADAA        |
      | 10,10    | 5,5             | N                 | DDAAAAAAAD       |
      | 10,10    | 5,5             | N                 | DAAAAAAAAD       |
      | 10,10    | 5,5             | N                 | GAAAAAAAAD       |