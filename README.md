# Aspirateur automatique iHoover (Yanport)

Réalisation d'un [aspirateur automatique](https://docs.google.com/document/d/1lmerjBXassYpTq-O-cfSBbw3yf0hv5GdlQSEqChTSXs/edit) pour la société Yanport.

<a href="https://github.com/jtutzo/vacuum-cleaner"><img alt="GitHub Actions status" src="https://github.com/jtutzo/vacuum-cleaner/workflows/maven/badge.svg"></a>

L'aspirateur est capable :
 - d'exécuter une séquence de format "DGA" dans une grille déterminée
 - De gérer les problèmes de collision à la grille (choix d'une exception)

Le programme n'a pas de fonction main. \
Pour vérifier son bon fonctionnement il faut exécuter les tests de scénario avec la commande `maven` suivante :
```bash
mvn clean test
```

Voici les différents scnéraii :
```gherkin
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
```

## Stack technique

- `kotlin` (jvm 1.8)
- `maven` pour la gestion des dépendances et du cycle de vie de l'application (test, compile, build)
- `cucumber` pour réaliser les tests BDD
- `githubCI` pour l'intégration continue
