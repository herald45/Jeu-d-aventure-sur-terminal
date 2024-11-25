# Projet : Jeu d'Aventure sur Terminal

## Description
Ce projet consiste à développer un jeu d'aventure sur terminal inspiré du jeu *Cataclysm: Dark Days Ahead*. Il s'agit d'un jeu de survie post-apocalyptique en tour par tour où le joueur incarne un personnage évoluant dans un environnement hostile. Le personnage pourra explorer, se battre, interagir avec des animaux et des objets, et s’adapter pour survivre.

Le jeu sera développé en Java selon une architecture **MVC** et tirera parti de divers design patterns pour assurer une conception modulaire et robuste. 

---

## Fonctionnalités de la première itération
1. **Déplacements et exploration :** Le personnage peut se déplacer dans la zone de jeu.
2. **Combat :** Le personnage peut se battre contre des ennemis.
3. **Interaction avec des objets :** 
   - Ramasser et déposer des objets.
   - Utiliser des objets pour influencer l’environnement ou les personnages.
4. **Interaction avec des animaux :**
   - Nourrir des animaux affamés pour tenter de les apprivoiser.
   - Protéger un animal apprivoisé des dangers.
   - Recevoir des avertissements de l’animal apprivoisé sur les dangers imminents.
5. **Zone de jeu thématique :** La zone de jeu pourra adopter le thème *Forêt* ou *Jungle*.

---

## Organisation du Projet

### Architecture MVC
- **Modèle (package `modele`)** :
  - Contient les classes décrivant les entités du jeu (personnage, objets, animaux, ennemis, terrain, etc.).
- **Vue (package `vue`)** :
  - Contient la classe `Ihm` pour interagir avec l’utilisateur via le terminal.
- **Contrôleur (package `controleur`)** :
  - Contient la classe `Controleur` qui gère la logique du jeu et coordonne les interactions entre le modèle et la vue.

### Diagrammes produit
- **Diagramme de cas d'utilisation :** Décrit les différentes interactions possibles entre le joueur et le système.
- **Scénarios :** Explique les séquences d’actions prévues pour différentes situations du jeu.
- **Diagramme de classe :** Représente la structure du domaine du jeu.
- **Diagramme de conception avec design patterns :** Décrit l’utilisation des patterns pour assurer une architecture modulaire.

---

## Design Patterns Utilisés
1. **Factory Method / Fabrique :** Pour la création d'objets (ex. différents types d'animaux, objets, ennemis).
2. **Observer / Observateur :** Pour permettre à l’animal apprivoisé de notifier le joueur des dangers.
3. **Command /Commande :** Pour gérer les actions du personnage (déplacement, combat, interaction avec objets/animaux).
4. **Singleton :** Pour gérer des ressources globales comme la carte ou l’état du jeu.
5. **State / Etat :** Pour gérer les différents états du personnage (normal, blessé, affamé, etc.).

---

## Structure des Packages
```
src/
├── controleur/
│   └── Controleur.java
├── modele/
│   ├── Personnage.java
│   ├── Animal.java
│   ├── Objet.java
│   ├── Terrain.java
│   └── Ennemi.java
├── vue/
│   └── Ihm.java
└── Main.java
```

---

## Instructions de Compilation et d'Exécution
1. Clonez le dépôt :
   ```bash
   git clone <url-du-dépôt>
   cd <nom-du-dépôt>
   ```
2. Compilez le projet :
   ```bash
   javac src/**/*.java -d bin
   ```
3. Exécutez le jeu :
   ```bash
   java -cp bin Main
   ```

---

## Calendrier du Projet

| **Date**       | **Objectif**                                     |
|-----------------|-------------------------------------------------|
| 20 Novembre 2024 | Constitution des groupes sur CELENE.            |
| 11 Décembre 2024 | Rendu et présentation de la première itération. |
| 28 Novembre 2024 | Sujet de la deuxième itération disponible.      |
| 09 Janvier 2025 | Rendu et présentation de la deuxième itération.  |

---

## Membres de l'Équipe
1. **Nkounkou Hérald**  
2. **Villaume Evan**  
3. **So Thibault**  

---

## Références
- *Cataclysm: Dark Days Ahead* : [Site officiel](https://cataclysmdda.org/)
- Documentation officielle Java : [Oracle](https://docs.oracle.com/javase/)

---
