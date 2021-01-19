Feature: A vacuum cleaner - execute sequence
  A vacuum cleaner execute the sequence asked

  Scenario Outline: should execute the sequence asked
    Given i create an vacuum cleaner with "<gridSize>" as size grid and "<initialPosition>" as initial position
    When execute the sequence "<sequence>"
    Then the new position of vacuum cleaner is "<finalPosition>"
    Examples:
      | gridSize | initialPosition | sequence         | finalPosition |
      | 10,10    | 5,5,N           | DADADADAA        | 5,6,N         |
      | 20,20    | 10,10,N         | DADADADAA        | 10,11,N       |
      | 10,10    | 0,0,N           | AADAGAGADDAADA   | 2,2,S         |
      | 8,5      | 4,3,S           | DADADADAGAGAGAGA | 4,3,S         |
      | 10,10    | 4,3,S           | GAAGADAGAAGGAAG  | 7,4,O         |

  Scenario Outline: throw an exception when the vacuum cleaner position is out of grid
    Given i create an vacuum cleaner with "<gridSize>" as size grid and "<initialPosition>" as initial position
    When execute the sequence "<sequence>"
    Then should display "The vacuum cleaner position is out of grid." error
    And the new position of vacuum cleaner is "<finalPosition>"
    Examples:
      | gridSize | initialPosition | sequence          | finalPosition |
      | 10,10    | 5,10,N           | DADADADAA        | 5,10,N        |
      | 10,10    | 5,5,N            | DDAAAAAAAD       | 5,0,S         |
      | 10,10    | 5,5,N            | DAAAAAAAAD       | 10,5,O        |
      | 10,10    | 5,5,N            | GAAAAAAAAD       | 0,5,E         |