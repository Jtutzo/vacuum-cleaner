# Vacuum cleaner (iHoover)

Réalisation d'un [aspirateur automatique](https://docs.google.com/document/d/1lmerjBXassYpTq-O-cfSBbw3yf0hv5GdlQSEqChTSXs/edit).

L'aspirateur est capable de :
 - d'exécuter une séquence de format "DADA" dans une grille déterminer
 - De gérer les problèmes de collision (choix d'une exception)

## Stack technique

- `kotlin` (jvm 1.8)
- `maven` pour la gestion des dépendances et du cycle de vie de l'application (test, compile, build)
- `cucumber` pour réaliser des tests BDD
- `githubCI` pour l'intégration continue

## Test

```bash
mvn clean test
```
