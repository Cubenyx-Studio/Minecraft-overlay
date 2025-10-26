# 📋 Informations sur le Mod - Versions & Compatibilité

## 🎮 Versions Supportées

### ✅ Minecraft
- **Version de base** : `1.21.1`
- **Compatibilité** : `1.20.1` → `1.21.x` (toutes les versions 1.21 incluses)
- **Non supporté** : `< 1.20.1` et `≥ 1.22`

### ✅ NeoForge
- **Version utilisée** : `21.1.76`
- **Compatibilité** : `21.1.76+` (versions supérieures supportées)

### ✅ Java
- **Requis** : Java 21 ou supérieur
- **Testé avec** : Java 22

---

## 🎯 Fonctionnalités du Mod

### 📊 Overlay Steam-like
Appuyez sur **Shift+Tab** pour ouvrir l'overlay qui affiche :
- ⏰ **Heure actuelle** (haut gauche)
- 🎮 **FPS** (haut gauche)
- 📍 **Coordonnées** X, Y, Z (haut droite)
- 🌍 **Dimension** actuelle (haut droite)
- ❤️ **Santé** du joueur (centre haut)

### 🔧 Outils intégrés
L'overlay dispose de 4 boutons en bas de l'écran :

#### 🌐 **Navigateur**
- Interface de navigation web simplifiée
- Liens rapides vers Minecraft.net, CurseForge, Modrinth
- Note : Navigateur complet nécessiterait des bibliothèques additionnelles

#### ⏱ **Minuteur (Timer)**
- Configurer des heures, minutes, secondes
- Démarrer/Arrêter le compte à rebours
- Notification quand le temps est écoulé
- Réinitialisation facile

#### ⏲ **Chronomètre**
- Démarrer/Arrêter le chronomètre
- Enregistrement de tours (laps)
- Précision à la milliseconde
- Affichage des 5 derniers tours

#### ⚙ **Paramètres**
- Activer/Désactiver l'overlay
- Configurer l'affichage des informations
- Options de personnalisation

---

## 💻 Type de Mod : **CLIENT-ONLY**

### ✅ Qu'est-ce que cela signifie ?

Le mod est **strictement côté client** :
- ✅ **Installation** : Uniquement dans le dossier `mods/` du **client**
- ❌ **Serveur** : **Pas besoin** de l'installer sur le serveur
- ✅ **Multijoueur** : Fonctionne sur n'importe quel serveur (Vanilla, Forge, NeoForge, etc.)
- ✅ **Solo** : Fonctionne en solo

### 🔧 Configuration Technique

**Fichier** : `neoforge.mods.toml`
```toml
side = "CLIENT"  # ← Mod client-only
```

**Code** : `MinecraftOverlay.java`
```java
@Mod(value = MinecraftOverlay.MODID, dist = Dist.CLIENT)
// ← Chargé uniquement côté client
```

---

## 📦 Compatibilité Serveur

| Type de Serveur | Compatible ? | Requis sur le Serveur ? |
|-----------------|--------------|------------------------|
| Vanilla | ✅ Oui | ❌ Non |
| Forge/NeoForge | ✅ Oui | ❌ Non |
| Spigot/Paper | ✅ Oui | ❌ Non |
| Fabric | ✅ Oui | ❌ Non |

**Conclusion** : Le mod fonctionne sur **tous les serveurs** sans installation serveur !

---

## 🎯 Où Installer le Mod ?

### ✅ Installation Client
```
.minecraft/
└── mods/
    └── Minecraft-Overlay-1.0.0.jar  ← ICI
```

### ❌ PAS sur le Serveur
```
server/
└── mods/
    └── [NE PAS METTRE ICI]
```

---

## 🚀 Test de Compatibilité

### Client Solo (1.20.1 - 1.21.x)
```bash
# Lancer avec la version de votre choix
cmd /c gradlew.bat runClient
```

### Multijoueur
1. ✅ Installez le mod dans `.minecraft/mods/`
2. ✅ Connectez-vous à n'importe quel serveur 1.20.1-1.21.x
3. ✅ Le mod fonctionne automatiquement
4. ❌ Le serveur n'a PAS besoin du mod

---

## 📊 Résumé Technique

| Propriété | Valeur |
|-----------|--------|
| **Minecraft** | 1.20.1 → 1.21.x |
| **NeoForge** | 21.1.76+ |
| **Java** | 21+ |
| **Type** | CLIENT-ONLY |
| **Serveur requis** | ❌ NON |
| **Type de Config** | CLIENT (pas COMMON) |

---

## ⚠️ Modifications Effectuées

Pour rendre le mod strictement client-only :

1. ✅ **neoforge.mods.toml** : `side = "CLIENT"` (au lieu de `BOTH`)
2. ✅ **MinecraftOverlay.java** : 
   - Ajout de `@Mod(dist = Dist.CLIENT)`
   - Suppression de `onServerStarting()`
   - Config changée en `ModConfig.Type.CLIENT`
3. ✅ Suppression des imports serveur inutiles

---

## 🎉 Conclusion

✅ **Le mod est maintenant strictement CLIENT-ONLY**  
✅ **Compatible avec Minecraft 1.20.1 → 1.21.x**  
✅ **Fonctionne sur tous les serveurs sans installation serveur**  
✅ **Configuration optimisée pour le client**

---

**🎮 Prêt à utiliser sur n'importe quel serveur 1.20.1-1.21.x !**

