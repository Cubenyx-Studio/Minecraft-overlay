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

### Current Version
- ✅ **Steam-like overlay** with Shift+Tab toggle
- ✅ **Real-time information display:**
  - ⏰ Current time
  - 🎮 FPS counter
  - 📍 Player coordinates (X, Y, Z)
  - 🌍 Current dimension
  - ❤️ Player health
- ✅ **Integrated tools:**
  - 🌐 **Browser** - Web browsing interface with quick links
  - ⏱ **Timer** - Configurable countdown timer
  - ⏲ **Stopwatch** - Precision chronometer with lap recording
  - ⚙ **Settings** - Overlay customization options
- ✅ **Interactive buttons** with hover effects
- ✅ **Transparent overlay** - Non-intrusive design
- ✅ **Multilingual support** (French & English)

### Planned Features
- 🔲 Full web browser with rendering
- 🔲 Online friends list
- 🔲 Integrated chat
- 🔲 Notifications system
- 🔲 Screenshot with annotation
- 🔲 Advanced visual settings
- 🔲 Persistent configuration save

## 🚀 Installation

### Prerequisites
- Java 21 or higher
- Gradle (included via wrapper)
- NeoForge 1.21.1+

### For Users

1. Download the JAR file from releases
2. Place it in your `.minecraft/mods/` folder
3. Launch Minecraft with NeoForge profile
4. Press **Shift+Tab** in-game to open the overlay

### For Developers

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

The mod can be configured via the in-game Settings menu or the Forge configuration file:

- **overlayEnabled**: Enable/disable the overlay (default: true)
- **Show FPS**: Display FPS counter
- **Show Coordinates**: Display player coordinates
- **Show Time**: Display current time
- **Show Health**: Display player health
- **Show Dimension**: Display current dimension

## 🎯 Usage

### Main Controls
- Press **Shift+Tab** to toggle the overlay on/off

### Overlay Features

#### 🌐 Browser
- Enter URLs in the address bar
- Click "Go" to navigate
- Use quick links for popular sites (Minecraft.net, CurseForge, Modrinth)
- Click "Close Browser" to return

#### ⏱ Timer
- Set hours, minutes, and seconds
- Click "Start" to begin countdown
- Click "Stop" to pause
- Click "Reset" to clear
- Get a notification when time is up

#### ⏲ Stopwatch
- Click "Start" to begin timing
- Click "Stop" to pause
- Click "Lap" to record a split time
- Click "Reset" to clear all
- View last 5 lap times
- Millisecond precision display

#### ⚙ Settings
- Toggle overlay visibility
- Enable/disable individual information displays
- Click "Save Settings" to apply changes

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
│   │       ├── KeyBindings.java         # Key management
│   │       └── screens/
│   │           ├── BrowserScreen.java   # Browser interface
│   │           ├── TimerScreen.java     # Timer tool
│   │           ├── StopwatchScreen.java # Stopwatch tool
│   │           └── SettingsScreen.java  # Settings menu
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

