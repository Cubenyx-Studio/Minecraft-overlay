# Minecraft Overlay - Fabric Edition

[![Minecraft](https://img.shields.io/badge/Minecraft-1.21.4-brightgreen.svg)](https://www.minecraft.net/)
[![Fabric](https://img.shields.io/badge/Fabric-0.16.5-blue.svg)](https://fabricmc.net/)
[![License](https://img.shields.io/badge/License-All%20Rights%20Reserved-red.svg)](https://github.com/Cubenyx-Studio/minecraft-overlay)

> **⚠️ This is the Fabric port of Minecraft Overlay**
> 
> For the original NeoForge version, check the [`main` branch](https://github.com/Cubenyx-Studio/minecraft-overlay/tree/main)

## 📖 About

Minecraft Overlay is a **Steam-like overlay** for Minecraft, bringing advanced HUD features and utilities directly into your game. This Fabric port maintains all features from the NeoForge version while being fully compatible with the Fabric ecosystem.

## ✨ Features

- 🎮 **Steam-like Overlay** (Shift+Tab by default)
- ⏰ **Real-time Clock** & Play Time Tracking
- 📍 **Coordinates Display** & Dimension Info
- ❤️ **Health & FPS Display**
- 📌 **Pin System** - Keep important info visible at all times
- ⏱️ **Timer & Stopwatch** - Built-in time management tools
- 🌐 **In-Game Browser** - Browse the web without leaving Minecraft
- ⚙️ **Fully Configurable** - Customize everything via settings

## 🚀 Installation

### Requirements
- Minecraft 1.21.x
- Fabric Loader 0.16.5+
- Fabric API 0.105.0+1.21.1 (or newer)
- Java 21+

### Steps
1. Install [Fabric Loader](https://fabricmc.net/use/)
2. Download [Fabric API](https://modrinth.com/mod/fabric-api)
3. Download the latest release of Minecraft Overlay (Fabric)
4. Place both mods in your `.minecraft/mods` folder
5. Launch Minecraft with the Fabric profile

## 🎮 Usage

### Opening the Overlay
- Default keybind: **Shift + Tab**
- Configurable in Minecraft Controls settings

### Pin System
1. Open overlay (Shift+Tab)
2. Click **Settings** → **Pin Options**
3. Toggle pins for FPS, Coordinates, Time, etc.
4. Pinned elements stay visible even when overlay is closed

### Timer & Stopwatch
- Access via overlay bottom buttons
- Continue running in background
- Persistent across world changes

## 🔧 Configuration

Configuration file location: `.minecraft/config/minecraftoverlay.json`

All settings are also accessible through the in-game **Settings** menu.

## 🏗️ Building from Source

```bash
# Clone the repository
git clone https://github.com/Cubenyx-Studio/minecraft-overlay.git -b fabric-1.21-migration
cd minecraft-overlay

# Build the mod
./gradlew build

# The JAR will be in build/libs/ and automatically copied to your Desktop
```

## 🌐 Compatibility

- ✅ **Client-side only** - No server installation required
- ✅ Works on Singleplayer & Multiplayer
- ✅ Compatible with most Fabric mods
- ✅ Supports 1.21 & 1.21.1

## 📝 Technical Details

### Fabric Port Highlights
- Uses **Mojang Mappings** for easier maintenance
- Leverages Fabric API:
  - `ClientModInitializer` for mod entry point
  - `HudRenderCallback` for overlay rendering
  - `KeyBindingHelper` for keybinds
  - `ClientTickEvents` for game loop integration
- JSON-based configuration (no external libs needed)

### Differences from NeoForge Version
- Configuration system (JSON instead of ModConfigSpec)
- Event system (Fabric Events instead of Forge EventBus)
- Keybind registration (Fabric Key Binding API)

## 📜 License

**All Rights Reserved** © 2025 Cubenyx-Studio

This project and its source code are proprietary. Unauthorized copying, modification, distribution, or use of this software is strictly prohibited without explicit permission from the copyright holder.

## 👥 Credits

**Developer:** Cubenyx-Studio  
**Original NeoForge Version:** [Minecraft Overlay](https://github.com/Cubenyx-Studio/minecraft-overlay)

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📞 Support

- 🐛 [Report Issues](https://github.com/Cubenyx-Studio/minecraft-overlay/issues)
- 💬 Discussions: GitHub Discussions (coming soon)

---

Made with ❤️ for the Minecraft Fabric community

