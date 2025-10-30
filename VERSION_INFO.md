# 📋 Version Information & Compatibility

> **Quick Navigation:** [📖 Version FR](VERSION_INFO_FR.md) | [📖 README](README.md) | [📖 README FR](README_FR.md)

---

## 🎮 Supported Versions

### Minecraft Compatibility

![Minecraft Version](https://img.shields.io/badge/Minecraft-1.20.1%20→%201.21.x-brightgreen?style=flat-square&logo=minecraft)
![Supported Versions](https://img.shields.io/badge/1.20.1%2B-blue?style=flat-square)
![Supported Versions](https://img.shields.io/badge/1.20.2%20--%201.20.4-blue?style=flat-square)
![Supported Versions](https://img.shields.io/badge/1.21-green?style=flat-square)
![Supported Versions](https://img.shields.io/badge/1.21.1-brightgreen?style=flat-square)

| Version | Status | Notes |
|---------|--------|-------|
| `1.20.1` | ✅ Supported | Base compatibility |
| `1.20.2` - `1.20.4` | ✅ Supported | Full compatibility |
| `1.21` | ✅ Supported | Full compatibility |
| `1.21.1` | ✅ Supported | Base version tested |
| `≥ 1.22` | ❌ Not Supported | Future versions |
| `< 1.20.1` | ❌ Not Supported | Old versions |

---

### NeoForge Requirements

![NeoForge](https://img.shields.io/badge/NeoForge-21.1.76%2B-orange?style=flat-square&logo=curseforge)
![NeoForge Status](https://img.shields.io/badge/Mod%20Loader-NeoForge-orange?style=flat-square)

- **Required Version:** `21.1.76` or higher
- **Tested With:** `21.1.76`
- **Compatibility:** All newer versions supported

---

### Java Requirements

![Java Version](https://img.shields.io/badge/Java-21%2B-blue?style=flat-square&logo=openjdk)
![Java Status](https://img.shields.io/badge/Runtime-Java%2021%2B-blue?style=flat-square)

- **Required:** Java 21 or higher
- **Tested With:** Java 22
- **Compatibility:** Java 21, 22, 23+

---

## 📦 Server Compatibility Matrix

![Compatibility](https://img.shields.io/badge/Compatible%20Servers-6%20Types-brightgreen?style=flat-square)

| Server Type | Compatible | Installation | Remarks |
|-------------|-----------|--------------|---------|
| **Vanilla** | ✅ Yes | ❌ Client Only | Works perfectly |
| **Forge** | ✅ Yes | ❌ Client Only | Client-side mods only |
| **NeoForge** | ✅ Yes | ❌ Client Only | Recommended setup |
| **Spigot** | ✅ Yes | ❌ Client Only | Paper-compatible |
| **Paper** | ✅ Yes | ❌ Client Only | High performance |
| **Fabric** | ✅ Yes | ❌ Client Only | Loader agnostic |

### Summary

> ✨ **The mod works on ALL server types without requiring server installation!**

---

## 🚀 Compatibility Testing

### Single-Player Testing (All Versions)

```bash
# Launch with any supported version (1.20.1 - 1.21.x)
cmd /c gradlew.bat runClient
```

### Multiplayer Testing

1. ✅ Install mod in `.minecraft/mods/`
2. ✅ Connect to server (1.20.1 - 1.21.x)
3. ✅ Mod activates automatically
4. ✅ Press Shift+Tab to open overlay
5. ❌ Server doesn't need the mod

### Cross-Version Compatibility

| Scenario | Works | Notes |
|----------|-------|-------|
| Client 1.20.1 + Server 1.20.1 | ✅ Yes | Perfect match |
| Client 1.21.1 + Server 1.20.1 | ✅ Yes | Compatible |
| Client 1.20.1 + Server 1.21.1 | ✅ Yes | Compatible |
| Client 1.21.1 + Server 1.21.1 | ✅ Yes | Perfect match |

---

## 📊 Technical Summary

![Type](https://img.shields.io/badge/Type-Client%20Side-purple?style=flat-square)
![Status](https://img.shields.io/badge/Status-Active-brightgreen?style=flat-square)
![License](https://img.shields.io/badge/License-All%20Rights%20Reserved-red?style=flat-square)

| Property | Value | Status |
|----------|-------|--------|
| **Minecraft Versions** | 1.20.1 → 1.21.x | ✅ Stable |
| **NeoForge Version** | 21.1.76+ | ✅ Tested |
| **Java Version** | 21+ | ✅ Required |
| **Mod Type** | CLIENT-ONLY | ✅ Optimized |
| **Server Required** | ❌ NO | ✅ Good |
| **Config Type** | CLIENT | ✅ Proper |
| **Performance Impact** | Minimal | ✅ Optimized |
| **Multi-Platform** | Yes | ✅ Supported |

---

## 📚 Additional Resources

- **[📖 Complete README (EN)](README.md)** - Full usage guide
- **[📖 Documentation (FR)](README_FR.md)** - Guide complet en français
- **[🐛 Issue Tracker](https://github.com/Cubenyx-Studio/minecraft-overlay/issues)** - Report problems
- **[⭐ Star Us](https://github.com/Cubenyx-Studio/minecraft-overlay)** - Show support

---

**Last Updated:** October 30, 2025  
**Version:** 1.0.0  
**Status:** ✅ Active Development

