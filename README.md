# Aspirateur automatique iHoover (Yanport)

![Java CI with Maven](https://github.com/Jtutzo/vacuum-cleaner/workflows/Java%20CI%20with%20Maven/badge.svg)
![Maven Package](https://github.com/Jtutzo/vacuum-cleaner/workflows/Maven%20Package/badge.svg)

Réalisation d'un [aspirateur automatique](https://docs.google.com/document/d/1lmerjBXassYpTq-O-cfSBbw3yf0hv5GdlQSEqChTSXs/edit) pour la société Yanport.

L'aspirateur est capable :
 - d'exécuter une séquence au format "DGA" dans une grille déterminée
 - De gérer les problèmes de collision avec la grille (choix d'une exception)

Le programme n'a pas de fonction main. J'ai développé les fonctionnalités en BDD. \
Pour vérifier son bon fonctionnement on peut exécuter les tests de scénario avec la commande `maven`.
```bash
mvn clean test
```

Voici les différents scénarios :
```gherkin
Feature: A vacuum cleaner - execute sequence
  A vacuum cleaner execute the asked sequence

  Scenario Outline: should execute the asked sequence
    Given I create an vacuum cleaner with "<gridSize>" as size grid, "<initialPosition>" as initial position and "<initialOrientation>" as initial orientation
    When execute the sequence "<sequence>"
    Then the new position and orientation of vacuum cleaner are "<finalPosition>" and "<finalOrientation>"
    And there is no error
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
    Then there is an error with message "The position is out of the grid."
    Examples:
      | gridSize | initialPosition | initialOrientation| sequence         |
      | 10,10    | 5,10            | N                 | DADADADAA        |
      | 10,10    | 5,5             | N                 | DDAAAAAAAD       |
      | 10,10    | 5,5             | N                 | DAAAAAAAAD       |
      | 10,10    | 5,5             | N                 | GAAAAAAAAD       |
```

## Stack technique

- `kotlin` (jvm 1.8)
- `maven` pour la gestion des dépendances et du cycle de vie de l'application (test, compile, build)
- `cucumber` pour les tests de scénario
- `github Actions` pour l'intégration continue
