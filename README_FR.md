# Minecraft Overlay

![Minecraft Version](https://img.shields.io/badge/Minecraft-1.20.1--1.21.x-brightgreen?style=for-the-badge&logo=minecraft)
[![NeoForge](https://img.shields.io/badge/NeoForge-21.1.76+-orange?style=for-the-badge&logo=curseforge)](https://neoforged.net/)
[![Java Version](https://img.shields.io/badge/Java-21+-blue?style=for-the-badge&logo=openjdk)](https://www.oracle.com/fr/java/technologies/downloads/)
[![License](https://img.shields.io/badge/License-All%20Rights%20Reserved-red?style=for-the-badge)](LICENSE)
![Client Side](https://img.shields.io/badge/Side-Client%20Only-purple?style=for-the-badge)

[![Issues](https://img.shields.io/github/issues/Cubenyx-Studio/minecraft-overlay?style=for-the-badge&logo=github)](https://github.com/Cubenyx-Studio/minecraft-overlay/issues)
[![Stars](https://img.shields.io/github/stars/Cubenyx-Studio/minecraft-overlay?style=for-the-badge&logo=github)](https://github.com/Cubenyx-Studio/minecraft-overlay/stargazers)

> Un mod Minecraft qui recrée l'overlay de Steam directement dans le jeu, offrant des outils pratiques et des informations en temps réel.

[English Version](README.md) | **Version Française**

---

## 📖 Table des Matières

- [Aperçu](#-aperçu)
- [Fonctionnalités](#-fonctionnalités)
- [Compatibilité](#-compatibilité)
- [Installation](#-installation)
- [Utilisation](#-utilisation)
- [Configuration](#️-configuration)
- [Langues Supportées](#-langues-supportées)
- [Développement](#-développement)
- [Captures d'écran](#-captures-décran)
- [FAQ](#-faq)
- [Contribution](#-contribution)
- [Licence](#-licence)

---

## 🎮 Aperçu

**Minecraft Overlay** est un mod client qui apporte l'expérience de l'overlay Steam à Minecraft. Appuyez simplement sur **Shift+Tab** pour accéder à une interface élégante avec des outils pratiques, des informations en temps réel et bien plus encore !

### ✨ Points Forts

- 🎯 **Interface intuitive** inspirée de Steam
- ⚡ **Performances optimisées** - Aucun impact sur les FPS
- 🌐 **Multilingue** - Français, Anglais, Espagnol
- 🔧 **Hautement personnalisable** - Adaptez l'overlay à vos besoins
- 📌 **Système d'épinglage** - Gardez les informations importantes toujours visibles
- 💾 **Sauvegarde automatique** - Vos préférences sont conservées

---

## 🚀 Fonctionnalités

### Interface Principale

<table>
<tr>
<td width="50%">

#### 📊 Informations en Temps Réel
- ⏰ **Heure actuelle** - Horloge système
- 🎮 **Compteur FPS** - Suivi des performances
- 📍 **Coordonnées** - Position X, Y, Z
- 🌍 **Dimension** - Overworld, Nether, End
- ❤️ **Santé** - Points de vie actuels/max
- ⏱️ **Temps de jeu** - Suivi du temps passé
  - Session instance
  - Temps dans le monde
  - Temps dans la dimension

</td>
<td width="50%">

#### 🛠️ Outils Intégrés
- 🌐 **Navigateur Web**
  - Liens rapides (Minecraft, CurseForge, Modrinth)
  - Ouverture dans le navigateur système
  - Interface utilisateur intuitive
- ⏱️ **Minuteur**
  - Configuration heures/minutes/secondes
  - Contrôles Démarrer/Arrêter/Réinitialiser
  - Notification à la fin
- ⏲️ **Chronomètre**
  - Précision à la milliseconde
  - Enregistrement des tours
  - Affichage des 5 derniers tours

</td>
</tr>
</table>

### Paramètres Avancés

#### ⚙️ Options d'Affichage
- 🎛️ **Activation globale** - Active/désactive l'overlay
- 📊 **Affichage FPS** - Montre le compteur d'images par seconde
- 📍 **Affichage coordonnées** - Montre votre position
- ⏰ **Affichage heure réelle** - Montre l'horloge système
- ⏱️ **Affichage temps de jeu** - Suivi du temps passé
- ❤️ **Affichage santé** - Montre vos points de vie
- 🌍 **Affichage dimension** - Montre la dimension actuelle

#### 📌 Système d'Épinglage
Épinglez des éléments pour les garder **toujours visibles** même quand l'overlay est fermé !

- 📌 **Pin FPS** - Compteur FPS persistant
- 📌 **Pin Coordonnées** - Position toujours affichée
- 📌 **Pin Heure** - Horloge permanente
- 📌 **Pin Temps de jeu** - Suivi de session visible
- 📌 **Pin Santé** - Barre de vie constante
- 📌 **Pin Dimension** - Indication de dimension fixe

Les éléments épinglés apparaissent avec une **étoile dorée** ⭐ et restent visibles en jeu !

---

## 🎯 Compatibilité

### ✅ Versions Supportées

| Composant | Versions |
|-----------|----------|
| 🎮 **Minecraft** | `1.20.1` → `1.21.x` (toutes les versions 1.21) |
| 🔧 **NeoForge** | `21.1.76` ou supérieur |
| ☕ **Java** | `21` ou supérieur |
| 💻 **Système** | Windows, Linux, macOS |

### 📋 Important : Mod Client Uniquement

| Type | Statut | Description |
|------|--------|-------------|
| 💻 **Client** | ✅ **OUI** | À installer dans `.minecraft/mods/` |
| 🖥️ **Serveur** | ❌ **NON** | Ne PAS installer sur le serveur |
| 🌐 **Multijoueur** | ✅ **Compatible** | Fonctionne sur tous les serveurs |
| 👥 **Autres joueurs** | ❌ **Non requis** | Les autres n'ont pas besoin du mod |

**Serveurs compatibles :** Vanilla, Forge, NeoForge, Spigot, Paper, Fabric, etc.

---

## 📚 Informations de Version & Détails de Compatibilité

Pour des informations détaillées sur les versions, la compatibilité serveur et les spécifications techniques :

- **📋 [Informations de Version Détaillées (Français)](VERSION_INFO_FR.md)** - Matrice de compatibilité complète
- **📋 [Detailed Version Info (English)](VERSION_INFO.md)** - Complete version compatibility matrix

---

## 📥 Installation

### Pour les Joueurs

#### 📦 Étape 1 : Prérequis

1. **Java 21** - [Télécharger ici](https://www.oracle.com/fr/java/technologies/downloads/)
2. **NeoForge 1.21.1+** - [Télécharger ici](https://neoforged.net/)

#### 🎮 Étape 2 : Installation

1. Téléchargez le fichier `.jar` depuis la [page des releases](https://github.com/Cubenyx-Studio/minecraft-overlay/releases)
2. Placez-le dans votre dossier `.minecraft/mods/`
   - Windows : `%APPDATA%\.minecraft\mods\`
   - Linux : `~/.minecraft/mods/`
   - macOS : `~/Library/Application Support/minecraft/mods/`
3. Lancez Minecraft avec le profil NeoForge
4. Profitez ! Appuyez sur **Shift+Tab** en jeu

#### ⚠️ Dépannage

Si le mod ne se lance pas :
- Vérifiez que vous avez **Java 21** ou supérieur
- Vérifiez que **NeoForge 21.1.76+** est installé
- Consultez les logs dans `.minecraft/logs/latest.log`

---

## 🎮 Utilisation

### Contrôles de Base

| Touche | Action |
|--------|--------|
| `Shift + Tab` | Ouvrir/Fermer l'overlay |
| `ESC` | Fermer l'overlay |
| `Clic gauche` | Interagir avec les boutons |

### Navigation dans l'Overlay

#### 🏠 Écran Principal
L'overlay affiche 5 boutons principaux en bas de l'écran :

```
🌐 Navigateur  |  ⏱️ Minuteur  |  📌 Épingler  |  ⏲️ Chrono  |  ⚙️ Paramètres
```

#### 🌐 Navigateur Web
1. Cliquez sur **🌐 Navigateur**
2. Entrez une URL dans la barre
3. Ou utilisez les liens rapides :
   - **Minecraft.net** - Site officiel
   - **CurseForge** - Mods et modpacks
   - **Modrinth** - Plateforme moderne de mods
4. Le lien s'ouvre dans votre navigateur système

#### ⏱️ Minuteur
1. Cliquez sur **⏱️ Minuteur**
2. Configurez les heures, minutes, secondes
3. Cliquez sur **Démarrer**
4. Le minuteur compte à rebours
5. Notification à la fin : `§a[Minuteur] Minuteur terminé !`

**Contrôles :**
- 🟢 **Démarrer** - Lance le compte à rebours
- 🔴 **Arrêter** - Met en pause
- 🔄 **Réinitialiser** - Remet à zéro

#### ⏲️ Chronomètre
1. Cliquez sur **⏲️ Chrono**
2. Cliquez sur **Démarrer**
3. Enregistrez des tours avec **Tour**
4. Affichage précis : `00:00:00.000` (H:M:S.ms)

**Fonctionnalités :**
- 🟢 **Démarrer** - Lance le chrono
- 🔴 **Arrêter** - Met en pause
- 🔄 **Réinitialiser** - Remet à zéro
- 📍 **Tour** - Enregistre le temps du tour
- 📊 Affiche les **5 derniers tours**

#### 📌 Options d'Épinglage
1. Cliquez sur **📌 Épingler**
2. Activez les éléments que vous voulez garder visibles
3. Cliquez sur **Sauvegarder**

Éléments épinglés :
- Apparaissent avec une **⭐ étoile dorée**
- Restent visibles même avec l'overlay fermé
- Se sauvegardent automatiquement

#### ⚙️ Paramètres
1. Cliquez sur **⚙️ Paramètres**
2. Ajustez les options d'affichage
3. Cliquez sur **Sauvegarder** pour confirmer

**Catégories :**
- **Options d'Affichage** - FPS, Coordonnées, Heure, etc.
- **Suivi du Temps** - Temps de jeu, Santé, Dimension

---

## ⚙️ Configuration

### Configuration dans le Jeu

La manière la plus simple est d'utiliser le menu **⚙️ Paramètres** dans l'overlay.

### Configuration Avancée

Le fichier de configuration se trouve dans :
```
.minecraft/config/minecraftoverlay-client.toml
```

#### Options Disponibles

```toml
# Active/désactive l'overlay
overlayEnabled = true

# Affichage des informations
showFPS = true
showCoordinates = true
showRealTime = true
showPlayTime = true
showHealth = true
showDimension = true

# Éléments épinglés
pinFPS = false
pinCoordinates = false
pinRealTime = false
pinPlayTime = false
pinHealth = false
pinDimension = false
```

### Touches de Contrôle

Pour changer les touches :
1. Ouvrez les **Options** de Minecraft
2. Allez dans **Commandes**
3. Cherchez la catégorie **Minecraft Overlay**
4. Configurez :
   - **Touche Principale** (défaut : `Tab`)
   - **Modificateur** (défaut : `Shift`)

---

## 🌍 Langues Supportées

Le mod change automatiquement de langue selon votre configuration Minecraft !

| Langue | Code | Couverture |
|--------|------|------------|
| 🇬🇧 **Anglais** | `en_us` | ✅ 100% |
| 🇫🇷 **Français** | `fr_fr` | ✅ 100% |
| 🇪🇸 **Espagnol** | `es_es` | ✅ 100% |

### Changer la Langue

1. Dans Minecraft : **Options** → **Langue**
2. Sélectionnez votre langue
3. Le mod s'adapte automatiquement !

**Total :** Plus de **95 clés de traduction** par langue

---

## 👨‍💻 Développement

### Pour les Développeurs

#### Cloner le Projet

```bash
git clone https://github.com/Cubenyx-Studio/minecraft-overlay.git
cd minecraft-overlay
```

#### Compiler le Mod

```bash
gradlew build
```

Le JAR compilé se trouve dans `build/libs/` et est **automatiquement copié sur le Bureau**.

#### Lancer en Mode Dev

```bash
# Client de développement
gradlew runClient

# Serveur de développement (non recommandé pour ce mod)
gradlew runServer
```

#### Structure du Projet

```
minecraft-overlay/
├── src/main/java/com/cubenyxstudio/minecraftoverlay/
│   ├── MinecraftOverlay.java          # Classe principale
│   ├── Config.java                     # Configuration
│   └── client/
│       ├── KeyBindings.java            # Touches de contrôle
│       ├── OverlayState.java           # État global
│       ├── PlayTimeTracker.java        # Suivi du temps
│       ├── PinnedOverlayRenderer.java  # Rendu des éléments épinglés
│       └── screens/
│           ├── OverlayScreen.java      # Écran principal
│           ├── BrowserScreen.java      # Navigateur
│           ├── TimerScreen.java        # Minuteur
│           ├── StopwatchScreen.java    # Chronomètre
│           ├── PinOptionsScreen.java   # Options d'épinglage
│           └── SettingsScreen.java     # Paramètres
├── src/main/resources/
│   └── assets/minecraftoverlay/
│       └── lang/
│           ├── en_us.json              # Anglais
│           ├── fr_fr.json              # Français
│           └── es_es.json              # Espagnol
└── build.gradle                         # Configuration Gradle
```

#### Ajouter une Nouvelle Langue

1. Créez `src/main/resources/assets/minecraftoverlay/lang/[code].json`
2. Copiez la structure de `en_us.json`
3. Traduisez toutes les valeurs (gardez les clés identiques)
4. Le mod détectera automatiquement la nouvelle langue

**Codes courants :**
- `de_de` - Allemand
- `it_it` - Italien
- `pt_br` - Portugais (Brésil)
- `ru_ru` - Russe
- `ja_jp` - Japonais
- `zh_cn` - Chinois (Simplifié)
- `ko_kr` - Coréen

---

## 📸 Captures d'Écran

> *Captures d'écran à venir...*

---

## ❓ FAQ

### Questions Fréquentes

<details>
<summary><strong>Le mod fonctionne-t-il en multijoueur ?</strong></summary>

Oui ! C'est un mod **client uniquement**, il fonctionne sur n'importe quel serveur sans que celui-ci n'ait besoin de l'installer.
</details>

<details>
<summary><strong>Les autres joueurs doivent-ils avoir le mod ?</strong></summary>

Non ! Seul vous avez besoin du mod. Les autres joueurs ne le verront même pas.
</details>

<details>
<summary><strong>Le mod affecte-t-il les performances ?</strong></summary>

Non ! Le mod est optimisé et n'a aucun impact notable sur les FPS. L'overlay n'est rendu que quand il est ouvert.
</details>

<details>
<summary><strong>Puis-je changer les touches de contrôle ?</strong></summary>

Oui ! Allez dans **Options** → **Commandes** → **Minecraft Overlay** et configurez les touches comme vous le souhaitez.
</details>

<details>
<summary><strong>Comment épingler des éléments ?</strong></summary>

1. Ouvrez l'overlay (Shift+Tab)
2. Cliquez sur le bouton **📌 Épingler**
3. Activez les éléments souhaités
4. Cliquez sur **Sauvegarder**

Les éléments épinglés restent visibles en permanence avec une ⭐ étoile dorée.
</details>

<details>
<summary><strong>Le navigateur affiche-t-il les pages web dans le jeu ?</strong></summary>

Actuellement, le navigateur ouvre les liens dans votre navigateur système. Une intégration complète avec rendu web nécessiterait JCEF (Java Chromium Embedded Framework), une dépendance volumineuse.
</details>

<details>
<summary><strong>Mes paramètres sont-ils sauvegardés ?</strong></summary>

Oui ! Tous vos paramètres (affichage, épinglage, etc.) sont automatiquement sauvegardés dans `.minecraft/config/minecraftoverlay-client.toml`.
</details>

<details>
<summary><strong>Le mod est-il compatible avec d'autres mods ?</strong></summary>

Oui ! Le mod est conçu pour être compatible avec la majorité des mods. En cas de conflit, contactez-nous sur GitHub.
</details>

---

## 🤝 Contribution

Les contributions sont les bienvenues ! Voici comment vous pouvez aider :

### Signaler un Bug

1. Vérifiez que le bug n'a pas déjà été signalé dans les [Issues](https://github.com/Cubenyx-Studio/minecraft-overlay/issues)
2. Créez une nouvelle issue avec :
   - Description détaillée du problème
   - Étapes pour reproduire
   - Version du mod, Minecraft, NeoForge
   - Logs (`.minecraft/logs/latest.log`)

### Proposer une Fonctionnalité

1. Ouvrez une [Issue](https://github.com/Cubenyx-Studio/minecraft-overlay/issues) avec le tag `enhancement`
2. Décrivez clairement la fonctionnalité souhaitée
3. Expliquez pourquoi elle serait utile

### Contribuer au Code

1. **Fork** le projet
2. Créez une branche (`git checkout -b feature/MaSuperFonctionnalite`)
3. Committez vos changements (`git commit -m 'Ajout d'une super fonctionnalité'`)
4. Push vers la branche (`git push origin feature/MaSuperFonctionnalite`)
5. Ouvrez une **Pull Request**

### Aider avec les Traductions

1. Ajoutez une nouvelle langue dans `src/main/resources/assets/minecraftoverlay/lang/`
2. Suivez le format de `en_us.json`
3. Soumettez une Pull Request

---

## 📜 Licence

**Tous droits réservés © 2025 Cubenyx-Studio**

Ce mod est la propriété exclusive de **Cubenyx-Studio**. Toute redistribution, modification ou utilisation commerciale est strictement interdite sans autorisation écrite préalable.

### Permissions

- ✅ **Utilisation personnelle** - Libre
- ✅ **Partage du lien GitHub** - Encouragé
- ❌ **Redistribution du fichier .jar** - Interdit
- ❌ **Modification et republication** - Interdit
- ❌ **Utilisation commerciale** - Interdit

---

## 📞 Contact & Support

### 🔗 Liens Utiles

- 🐛 **Signaler des Bugs** : [GitHub Issues](https://github.com/Cubenyx-Studio/minecraft-overlay/issues)

### ❤️ Nous Soutenir

Si vous aimez ce mod, n'hésitez pas à :
- ⭐ **Star** le projet sur GitHub
- 🐛 Signaler les bugs
- 💡 Proposer des améliorations
- 🌍 Aider avec les traductions
- 📢 Partager avec vos amis !

---

<div align="center">

**Fait avec ❤️ par Cubenyx-Studio**

[![GitHub](https://img.shields.io/badge/GitHub-Cubenyx--Studio-181717?style=for-the-badge&logo=github)](https://github.com/Cubenyx-Studio)

*Amusez-vous bien avec Minecraft Overlay !* 🎮✨

</div>

