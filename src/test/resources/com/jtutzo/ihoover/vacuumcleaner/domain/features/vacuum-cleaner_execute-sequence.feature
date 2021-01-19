Feature: A vacuum cleaner - execute sequence
  A vacuum cleaner execute the sequence asked

  Scenario Outline: should execute the sequence asked
    Given i create an vacuum cleaner with "<gridSize>" as size grid and "<initialPosition>" as initial position
    When execute the sequence "<sequence>"
    Then the new position of vacuum cleaner is "<finalPosition>"
    Examples:
      | gridSize | initialPosition | sequence  | finalPosition |
      | 10,10    | 5,5,N           | DADADADAA | 5,6,N         |
      | 20,20    | 10,10,N         | DADADADAA | 10,11,N       |
