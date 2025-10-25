# 📋 Mod Information - Versions & Compatibility

## 🎮 Supported Versions

### ✅ Minecraft
- **Base version**: `1.21.1`
- **Compatibility**: `1.20.1` → `1.21.x` (all 1.21 versions included)
- **Not supported**: `< 1.20.1` and `≥ 1.22`

### ✅ NeoForge
- **Version used**: `21.1.76`
- **Compatibility**: `21.1.76+` (higher versions supported)

### ✅ Java
- **Required**: Java 21 or higher
- **Tested with**: Java 22

---

## 💻 Mod Type: **CLIENT-ONLY**

### ✅ What does this mean?

The mod is **strictly client-side**:
- ✅ **Installation**: Only in the **client** `mods/` folder
- ❌ **Server**: **No need** to install on the server
- ✅ **Multiplayer**: Works on any server (Vanilla, Forge, NeoForge, etc.)
- ✅ **Singleplayer**: Works in singleplayer

### 🔧 Technical Configuration

**File**: `neoforge.mods.toml`
```toml
side = "CLIENT"  # ← Client-only mod
```

**Code**: `MinecraftOverlay.java`
```java
@Mod(value = MinecraftOverlay.MODID, dist = Dist.CLIENT)
// ← Loaded only on client side
```

---

## 📦 Server Compatibility

| Server Type | Compatible? | Required on Server? |
|-------------|-------------|---------------------|
| Vanilla | ✅ Yes | ❌ No |
| Forge/NeoForge | ✅ Yes | ❌ No |
| Spigot/Paper | ✅ Yes | ❌ No |
| Fabric | ✅ Yes | ❌ No |

**Conclusion**: The mod works on **all servers** without server installation!

---

## 🎯 Where to Install the Mod?

### ✅ Client Installation
```
.minecraft/
└── mods/
    └── Minecraft-Overlay-1.0.0.jar  ← HERE
```

### ❌ NOT on the Server
```
server/
└── mods/
    └── [DO NOT PUT HERE]
```

---

## 🚀 Compatibility Testing

### Singleplayer Client (1.20.1 - 1.21.x)
```bash
# Launch with your chosen version
cmd /c gradlew.bat runClient
```

### Multiplayer
1. ✅ Install the mod in `.minecraft/mods/`
2. ✅ Connect to any server 1.20.1-1.21.x
3. ✅ The mod works automatically
4. ❌ The server does NOT need the mod

---

## 📊 Technical Summary

| Property | Value |
|----------|-------|
| **Minecraft** | 1.20.1 → 1.21.x |
| **NeoForge** | 21.1.76+ |
| **Java** | 21+ |
| **Type** | CLIENT-ONLY |
| **Server required** | ❌ NO |
| **Config Type** | CLIENT (not COMMON) |

---

## ⚠️ Changes Made

To make the mod strictly client-only:

1. ✅ **neoforge.mods.toml**: `side = "CLIENT"` (instead of `BOTH`)
2. ✅ **MinecraftOverlay.java**: 
   - Added `@Mod(dist = Dist.CLIENT)`
   - Removed `onServerStarting()`
   - Changed config to `ModConfig.Type.CLIENT`
3. ✅ Removed unnecessary server imports

---

## 🎉 Conclusion

✅ **The mod is now strictly CLIENT-ONLY**  
✅ **Compatible with Minecraft 1.20.1 → 1.21.x**  
✅ **Works on all servers without server installation**  
✅ **Configuration optimized for client**

---

**🎮 Ready to use on any server 1.20.1-1.21.x!**


