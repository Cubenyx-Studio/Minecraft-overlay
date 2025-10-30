# 📋 Informations de Version & Compatibilité

> **Navigation rapide :** [📖 Version EN](VERSION_INFO.md) | [📖 README FR](README_FR.md) | [📖 README](README.md)

---

## 🎮 Versions Supportées

### Compatibilité Minecraft

![Minecraft Version](https://img.shields.io/badge/Minecraft-1.20.1%20→%201.21.x-brightgreen?style=flat-square&logo=minecraft)
![Supported Versions](https://img.shields.io/badge/1.20.1%2B-blue?style=flat-square)
![Supported Versions](https://img.shields.io/badge/1.20.2%20--%201.20.4-blue?style=flat-square)
![Supported Versions](https://img.shields.io/badge/1.21-green?style=flat-square)
![Supported Versions](https://img.shields.io/badge/1.21.1-brightgreen?style=flat-square)

| Version | Statut | Notes |
|---------|--------|-------|
| `1.20.1` | ✅ Supportée | Compatibilité de base |
| `1.20.2` - `1.20.4` | ✅ Supportées | Compatibilité complète |
| `1.21` | ✅ Supportée | Compatibilité complète |
| `1.21.1` | ✅ Supportée | Version testée |
| `≥ 1.22` | ❌ Non supportée | Versions futures |
| `< 1.20.1` | ❌ Non supportée | Anciennes versions |

---

### Exigences NeoForge

![NeoForge](https://img.shields.io/badge/NeoForge-21.1.76%2B-orange?style=flat-square&logo=curseforge)
![NeoForge Status](https://img.shields.io/badge/Chargeur%20de%20Mod-NeoForge-orange?style=flat-square)

- **Version requise :** `21.1.76` ou supérieure
- **Testée avec :** `21.1.76`
- **Compatibilité :** Toutes les versions plus récentes supportées

---

### Exigences Java

![Java Version](https://img.shields.io/badge/Java-21%2B-blue?style=flat-square&logo=openjdk)
![Java Status](https://img.shields.io/badge/Runtime-Java%2021%2B-blue?style=flat-square)

- **Requise :** Java 21 ou supérieure
- **Testée avec :** Java 22
- **Compatibilité :** Java 21, 22, 23+

---

## 📦 Matrice de Compatibilité Serveur

![Compatibility](https://img.shields.io/badge/Serveurs%20Compatibles-6%20Types-brightgreen?style=flat-square)

| Type de Serveur | Compatible | Installation | Remarques |
|-----------------|-----------|--------------|----------|
| **Vanilla** | ✅ Oui | ❌ Client uniquement | Fonctionne parfaitement |
| **Forge** | ✅ Oui | ❌ Client uniquement | Mods côté client uniquement |
| **NeoForge** | ✅ Oui | ❌ Client uniquement | Configuration recommandée |
| **Spigot** | ✅ Oui | ❌ Client uniquement | Compatible Paper |
| **Paper** | ✅ Oui | ❌ Client uniquement | Haute performance |
| **Fabric** | ✅ Oui | ❌ Client uniquement | Indépendant du chargeur |

### Résumé

> ✨ **Le mod fonctionne sur TOUS les types de serveurs sans nécessiter une installation serveur !**

---

## 🚀 Tests de Compatibilité

### Tests Solo (Toutes les Versions)

```bash
# Lancer avec n'importe quelle version supportée (1.20.1 - 1.21.x)
cmd /c gradlew.bat runClient
```

### Tests Multijoueur

1. ✅ Installer mod dans `.minecraft/mods/`
2. ✅ Se connecter au serveur (1.20.1 - 1.21.x)
3. ✅ Mod s'active automatiquement
4. ✅ Appuyer sur Shift+Tab pour ouvrir overlay
5. ❌ Le serveur n'a pas besoin du mod

### Compatibilité Inter-Versions

| Scénario | Fonctionne | Notes |
|----------|-----------|-------|
| Client 1.20.1 + Serveur 1.20.1 | ✅ Oui | Correspondance parfaite |
| Client 1.21.1 + Serveur 1.20.1 | ✅ Oui | Compatible |
| Client 1.20.1 + Serveur 1.21.1 | ✅ Oui | Compatible |
| Client 1.21.1 + Serveur 1.21.1 | ✅ Oui | Correspondance parfaite |

---

## 📊 Résumé Technique

![Type](https://img.shields.io/badge/Type-Côté%20Client-purple?style=flat-square)
![Status](https://img.shields.io/badge/Statut-Actif-brightgreen?style=flat-square)
![License](https://img.shields.io/badge/Licence-Tous%20Droits%20Réservés-red?style=flat-square)

| Propriété | Valeur | Statut |
|-----------|--------|--------|
| **Versions Minecraft** | 1.20.1 → 1.21.x | ✅ Stable |
| **Version NeoForge** | 21.1.76+ | ✅ Testé |
| **Version Java** | 21+ | ✅ Requis |
| **Type de Mod** | CLIENT-UNIQUEMENT | ✅ Optimisé |
| **Serveur requis** | ❌ NON | ✅ Bon |
| **Type de Config** | CLIENT | ✅ Correct |
| **Impact Performance** | Minimal | ✅ Optimisé |
| **Multi-Plateforme** | Oui | ✅ Supporté |

---

## 📚 Ressources Supplémentaires

- **[📖 Documentation complète (FR)](README_FR.md)** - Guide complet d'utilisation
- **[📖 Documentation (EN)](README.md)** - Full usage guide
- **[🐛 Suivi des Problèmes](https://github.com/Cubenyx-Studio/minecraft-overlay/issues)** - Signaler des problèmes
- **[⭐ Nous Soutenir](https://github.com/Cubenyx-Studio/minecraft-overlay)** - Montrez votre soutien

---

**Dernière mise à jour :** 30 octobre 2025  
**Version :** 1.0.0  
**Statut :** ✅ Développement Actif

