# Minecraft Overlay

A Minecraft mod that recreates the Steam overlay for Minecraft.

## 🎮 Compatibility

This mod is compatible with Minecraft **1.20.1 to 1.21.x** (all 1.21 versions included).

**Important:** This is a **CLIENT-ONLY** mod:
- ✅ Install only on your client (in `.minecraft/mods/`)
- ❌ Do NOT install on servers
- ✅ Works on any server (Vanilla, Forge, NeoForge, Spigot, etc.)
- ✅ Other players don't need the mod

### Version Details
- **Minecraft:** 1.20.1 → 1.21.x
- **NeoForge:** 21.1.76 or higher
- **Java:** 21 or higher
- **Side:** CLIENT only

## 📋 Features

### Current Version (Base)
- ✅ Basic overlay displayed in-game
- ✅ Customizable configuration (position, opacity)
- ✅ Keyboard shortcut to toggle on/off (F9 by default)
- ✅ Multilingual support (French & English)

### Planned Features
- 🔲 Steam-like interface
- 🔲 Online friends list
- 🔲 Integrated chat
- 🔲 Notifications
- 🔲 Screenshot with annotation
- 🔲 Advanced visual settings

## 🚀 Installation

### Prerequisites
- Java 21 or higher
- Gradle (included via wrapper)
- NeoForge 1.21.1+

### Development

1. Clone the repository:
```bash
git clone https://github.com/Cubenyx-Studio/minecraft-overlay.git
cd minecraft-overlay
```

2. Build the project:
```bash
gradlew build
```
**Note:** The compiled JAR will be automatically copied to your Desktop after a successful build.

3. Launch the development client:
```bash
gradlew runClient
```

## ⚙️ Configuration

The mod can be configured via the Forge configuration file:

- **overlayEnabled**: Enable/disable the overlay (default: true)
- **overlayOpacity**: Overlay opacity 0-100 (default: 90)
- **overlayPosition**: Overlay position (TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT)

## 🎯 Usage

- Press **F9** (by default) to toggle the overlay on/off
- Keys can be reconfigured in Minecraft settings

## 🚀 Building & Distribution

After running `gradlew build`, the compiled JAR is automatically copied to your Desktop:
- **Location**: `Desktop/Minecraft-Overlay-1.0.0.jar`
- **Size**: Ready to share or install
- **Automatic**: No manual copy needed!

To disable auto-copy, comment out this line in `build.gradle`:
```groovy
// build.finalizedBy copyJarToDesktop
```

## 📊 Project Structure

```
minecraft-overlay/
├── src/main/
│   ├── java/com/cubenyxstudio/minecraftoverlay/
│   │   ├── MinecraftOverlay.java       # Main mod class
│   │   ├── Config.java                  # Mod configuration
│   │   └── client/
│   │       ├── OverlayRenderer.java     # Overlay rendering
│   │       └── KeyBindings.java         # Key management
│   └── resources/
│       ├── META-INF/neoforge.mods.toml  # Mod metadata
│       ├── pack.mcmeta                  # Resource pack metadata
│       └── assets/minecraftoverlay/
│           └── lang/                    # Translations
├── build.gradle                         # Gradle configuration
├── gradle.properties                    # Mod properties
└── README.md
```

## 🤝 Contributing

Contributions are welcome! Feel free to open an issue or pull request.

## 📜 License

MIT License - See the LICENSE file for more details

## 👥 Authors

Created by **Cubenyx-Studio**

