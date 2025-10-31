# Minecraft Overlay

![Minecraft Version](https://img.shields.io/badge/Minecraft-1.20.1--1.21.x-brightgreen?style=for-the-badge&logo=minecraft)
[![NeoForge](https://img.shields.io/badge/NeoForge-21.1.76+-orange?style=for-the-badge&logo=curseforge)](https://neoforged.net/)
[![Java Version](https://img.shields.io/badge/Java-21+-blue?style=for-the-badge&logo=openjdk)](https://www.oracle.com/fr/java/technologies/downloads/)

[![Issues](https://img.shields.io/github/issues/Cubenyx-Studio/minecraft-overlay?style=for-the-badge&logo=github)](https://github.com/Cubenyx-Studio/minecraft-overlay/issues)
[![Stars](https://img.shields.io/github/stars/Cubenyx-Studio/minecraft-overlay?style=for-the-badge&logo=github)](https://github.com/Cubenyx-Studio/minecraft-overlay/stargazers)

> A Minecraft mod that recreates the Steam overlay directly in-game, providing useful tools and real-time information.

**English Version** | [Version Française](README_FR.md)

---

## 📖 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Compatibility](#-compatibility)
- [Installation](#-installation)
- [Usage](#-usage)
- [Configuration](#️-configuration)
- [Supported Languages](#-supported-languages)
- [Development](#-development)
- [Screenshots](#-screenshots)
- [FAQ](#-faq)
- [Contributing](#-contributing)
- [License](#-license)

---

## 🎮 Overview

**Minecraft Overlay** is a client-side mod that brings the Steam overlay experience to Minecraft. Simply press **Shift+Tab** to access an elegant interface with practical tools, real-time information, and much more!

### ✨ Highlights

- 🎯 **Intuitive interface** inspired by Steam
- ⚡ **Optimized performance** - No FPS impact
- 🌐 **Multilingual** - French, English, Spanish
- 🔧 **Highly customizable** - Adapt the overlay to your needs
- 📌 **Pinning system** - Keep important information always visible
- 💾 **Auto-save** - Your preferences are preserved

---

## 🚀 Features

### Main Interface

<table>
<tr>
<td width="50%">

#### 📊 Real-Time Information
- ⏰ **Current time** - System clock
- 🎮 **FPS counter** - Performance tracking
- 📍 **Coordinates** - X, Y, Z position
- 🌍 **Dimension** - Overworld, Nether, End
- ❤️ **Health** - Current/max health points
- ⏱️ **Play time** - Time tracking
  - Instance session
  - World time
  - Dimension time

</td>
<td width="50%">

#### 🛠️ Integrated Tools
- 🌐 **Web Browser**
  - Quick links (Minecraft, CurseForge, Modrinth)
  - Opens in system browser
  - Intuitive user interface
- ⏱️ **Timer**
  - Hours/minutes/seconds configuration
  - Start/Stop/Reset controls
  - End notification
- ⏲️ **Stopwatch**
  - Millisecond precision
  - Lap recording
  - Display last 5 laps

</td>
</tr>
</table>

### Advanced Settings

#### ⚙️ Display Options
- 🎛️ **Global activation** - Enable/disable overlay
- 📊 **FPS display** - Show frames per second counter
- 📍 **Coordinates display** - Show your position
- ⏰ **Real time display** - Show system clock
- ⏱️ **Play time display** - Time tracking
- ❤️ **Health display** - Show your health points
- 🌍 **Dimension display** - Show current dimension

#### 📌 Pinning System
Pin elements to keep them **always visible** even when the overlay is closed!

- 📌 **Pin FPS** - Persistent FPS counter
- 📌 **Pin Coordinates** - Always displayed position
- 📌 **Pin Time** - Permanent clock
- 📌 **Pin Play Time** - Visible session tracking
- 📌 **Pin Health** - Constant health bar
- 📌 **Pin Dimension** - Fixed dimension indicator

Pinned elements appear with a **golden star** ⭐ and remain visible in-game!

---

## 🎯 Compatibility

### ✅ Supported Versions

| Component | Versions |
|-----------|----------|
| 🎮 **Minecraft** | `1.20.1` → `1.21.x` (all 1.21 versions) |
| 🔧 **NeoForge** | `21.1.76` or higher |
| ☕ **Java** | `21` or higher |
| 💻 **System** | Windows, Linux, macOS |

### 📋 Important: Client-Only Mod

| Type | Status | Description |
|------|--------|-------------|
| 💻 **Client** | ✅ **YES** | Install in `.minecraft/mods/` |
| 🖥️ **Server** | ❌ **NO** | Do NOT install on server |
| 🌐 **Multiplayer** | ✅ **Compatible** | Works on all servers |
| 👥 **Other players** | ❌ **Not required** | Others don't need the mod |

**Compatible servers:** Vanilla, Forge, NeoForge, Spigot, Paper, Fabric, etc.

---

## 📚 Version Information & Compatibility Details

For detailed version information, server compatibility, and technical specifications:

- **📋 [Detailed Version Info (English)](VERSION_INFO.md)** - Complete version compatibility matrix
- **📋 [Informations de Version (Français)](VERSION_INFO_FR.md)** - Matrice de compatibilité complète

---

### For Players

#### 📦 Step 1: Prerequisites

1. **Java 21** - [Download here](https://www.oracle.com/fr/java/technologies/downloads/)
2. **NeoForge 1.21.1+** - [Download here](https://neoforged.net/)

#### 🎮 Step 2: Installation

1. Download the `.jar` file from the [releases page](https://github.com/Cubenyx-Studio/minecraft-overlay/releases)
2. Place it in your `.minecraft/mods/` folder
   - Windows: `%APPDATA%\.minecraft\mods\`
   - Linux: `~/.minecraft/mods/`
   - macOS: `~/Library/Application Support/minecraft/mods/`
3. Launch Minecraft with the NeoForge profile
4. Enjoy! Press **Shift+Tab** in-game

#### ⚠️ Troubleshooting

If the mod doesn't launch:
- Check that you have **Java 21** or higher
- Check that **NeoForge 21.1.76+** is installed
- Check logs in `.minecraft/logs/latest.log`

---

## 🎮 Usage

### Basic Controls

| Key | Action |
|-----|--------|
| `Shift + Tab` | Open/Close overlay |
| `ESC` | Close overlay |
| `Left Click` | Interact with buttons |

### Navigating the Overlay

#### 🏠 Main Screen
The overlay displays 5 main buttons at the bottom of the screen:

```
🌐 Browser  |  ⏱️ Timer  |  📌 Pin  |  ⏲️ Stopwatch  |  ⚙️ Settings
```

#### 🌐 Web Browser
1. Click on **🌐 Browser**
2. Enter a URL in the bar
3. Or use quick links:
   - **Minecraft.net** - Official website
   - **CurseForge** - Mods and modpacks
   - **Modrinth** - Modern mod platform
4. Link opens in your system browser

#### ⏱️ Timer
1. Click on **⏱️ Timer**
2. Configure hours, minutes, seconds
3. Click **Start**
4. Timer counts down
5. End notification: `§a[Timer] Timer finished!`

**Controls:**
- 🟢 **Start** - Starts countdown
- 🔴 **Stop** - Pauses
- 🔄 **Reset** - Resets to zero

#### ⏲️ Stopwatch
1. Click on **⏲️ Stopwatch**
2. Click **Start**
3. Record laps with **Lap**
4. Precise display: `00:00:00.000` (H:M:S.ms)

**Features:**
- 🟢 **Start** - Starts stopwatch
- 🔴 **Stop** - Pauses
- 🔄 **Reset** - Resets to zero
- 📍 **Lap** - Records lap time
- 📊 Displays **last 5 laps**

#### 📌 Pin Options
1. Click on **📌 Pin**
2. Enable elements you want to keep visible
3. Click **Save**

Pinned elements:
- Appear with a **⭐ golden star**
- Remain visible even with overlay closed
- Auto-save

#### ⚙️ Settings
1. Click on **⚙️ Settings**
2. Adjust display options
3. Click **Save** to confirm

**Categories:**
- **Display Options** - FPS, Coordinates, Time, etc.
- **Time Tracking** - Play time, Health, Dimension

---

## ⚙️ Configuration

### In-Game Configuration

The easiest way is to use the **⚙️ Settings** menu in the overlay.

### Advanced Configuration

The configuration file is located at:
```
.minecraft/config/minecraftoverlay-client.toml
```

#### Available Options

```toml
# Enable/disable overlay
overlayEnabled = true

# Information display
showFPS = true
showCoordinates = true
showRealTime = true
showPlayTime = true
showHealth = true
showDimension = true

# Pinned elements
pinFPS = false
pinCoordinates = false
pinRealTime = false
pinPlayTime = false
pinHealth = false
pinDimension = false
```

### Control Keys

To change keys:
1. Open Minecraft **Options**
2. Go to **Controls**
3. Look for **Minecraft Overlay** category
4. Configure:
   - **Main Key** (default: `Tab`)
   - **Modifier** (default: `Shift`)

---

## 🌍 Supported Languages

The mod automatically changes language based on your Minecraft settings!

| Language | Code | Coverage |
|----------|------|----------|
| 🇬🇧 **English** | `en_us` | ✅ 100% |
| 🇫🇷 **French** | `fr_fr` | ✅ 100% |
| 🇪🇸 **Spanish** | `es_es` | ✅ 100% |

### Changing Language

1. In Minecraft: **Options** → **Language**
2. Select your language
3. The mod adapts automatically!

**Total:** Over **95 translation keys** per language

---

## 👨‍💻 Development

### For Developers

#### Clone the Project

```bash
git clone https://github.com/Cubenyx-Studio/minecraft-overlay.git
cd minecraft-overlay
```

#### Build the Mod

```bash
gradlew build
```

The compiled JAR is in `build/libs/` and is **automatically copied to Desktop**.

#### Run in Dev Mode

```bash
# Development client
gradlew runClient

# Development server (not recommended for this mod)
gradlew runServer
```

#### Project Structure

```
minecraft-overlay/
├── src/main/java/com/cubenyxstudio/minecraftoverlay/
│   ├── MinecraftOverlay.java          # Main class
│   ├── Config.java                     # Configuration
│   └── client/
│       ├── KeyBindings.java            # Key bindings
│       ├── OverlayState.java           # Global state
│       ├── PlayTimeTracker.java        # Time tracking
│       ├── PinnedOverlayRenderer.java  # Pinned elements rendering
│       └── screens/
│           ├── OverlayScreen.java      # Main screen
│           ├── BrowserScreen.java      # Browser
│           ├── TimerScreen.java        # Timer
│           ├── StopwatchScreen.java    # Stopwatch
│           ├── PinOptionsScreen.java   # Pin options
│           └── SettingsScreen.java     # Settings
├── src/main/resources/
│   └── assets/minecraftoverlay/
│       └── lang/
│           ├── en_us.json              # English
│           ├── fr_fr.json              # French
│           └── es_es.json              # Spanish
└── build.gradle                         # Gradle config
```

#### Adding a New Language

1. Create `src/main/resources/assets/minecraftoverlay/lang/[code].json`
2. Copy structure from `en_us.json`
3. Translate all values (keep keys identical)
4. The mod will automatically detect the new language

**Common codes:**
- `de_de` - German
- `it_it` - Italian
- `pt_br` - Portuguese (Brazil)
- `ru_ru` - Russian
- `ja_jp` - Japanese
- `zh_cn` - Chinese (Simplified)
- `ko_kr` - Korean

---

## 📸 Screenshots

> *Screenshots coming soon...*

---

## ❓ FAQ

### Frequently Asked Questions

<details>
<summary><strong>Does the mod work in multiplayer?</strong></summary>

Yes! It's a **client-only** mod, it works on any server without needing to be installed on it.
</details>

<details>
<summary><strong>Do other players need the mod?</strong></summary>

No! Only you need the mod. Other players won't even see it.
</details>

<details>
<summary><strong>Does the mod affect performance?</strong></summary>

No! The mod is optimized and has no noticeable impact on FPS. The overlay is only rendered when open.
</details>

<details>
<summary><strong>Can I change the control keys?</strong></summary>

Yes! Go to **Options** → **Controls** → **Minecraft Overlay** and configure the keys as you wish.
</details>

<details>
<summary><strong>How do I pin elements?</strong></summary>

1. Open the overlay (Shift+Tab)
2. Click the **📌 Pin** button
3. Enable desired elements
4. Click **Save**

Pinned elements remain permanently visible with a ⭐ golden star.
</details>

<details>
<summary><strong>Does the browser display web pages in-game?</strong></summary>

Currently, the browser opens links in your system browser. Full integration with web rendering would require JCEF (Java Chromium Embedded Framework), a large dependency.
</details>

<details>
<summary><strong>Are my settings saved?</strong></summary>

Yes! All your settings (display, pinning, etc.) are automatically saved in `.minecraft/config/minecraftoverlay-client.toml`.
</details>

<details>
<summary><strong>Is the mod compatible with other mods?</strong></summary>

Yes! The mod is designed to be compatible with most mods. In case of conflict, contact us on GitHub.
</details>

---

## 🤝 Contributing

Contributions are welcome! Here's how you can help:

### Report a Bug

1. Check that the bug hasn't already been reported in [Issues](https://github.com/Cubenyx-Studio/minecraft-overlay/issues)
2. Create a new issue with:
   - Detailed problem description
   - Steps to reproduce
   - Mod, Minecraft, NeoForge versions
   - Logs (`.minecraft/logs/latest.log`)

### Suggest a Feature

1. Open an [Issue](https://github.com/Cubenyx-Studio/minecraft-overlay/issues) with the `enhancement` tag
2. Clearly describe the desired feature
3. Explain why it would be useful

### Contribute Code

1. **Fork** the project
2. Create a branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a **Pull Request**

### Help with Translations

1. Add a new language in `src/main/resources/assets/minecraftoverlay/lang/`
2. Follow the format of `en_us.json`
3. Submit a Pull Request

---

## 📜 License

**All Rights Reserved © 2025 Cubenyx-Studio**

This mod is the exclusive property of **Cubenyx-Studio**. Any redistribution, modification, or commercial use is strictly prohibited without prior written permission.

### Permissions

- ✅ **Personal use** - Free
- ✅ **Sharing GitHub link** - Encouraged
- ❌ **Redistributing .jar file** - Forbidden
- ❌ **Modification and republication** - Forbidden
- ❌ **Commercial use** - Forbidden

---

## 📞 Contact & Support

### 🔗 Useful Links

- 🐛 **Bug Reports**: [GitHub Issues](https://github.com/Cubenyx-Studio/minecraft-overlay/issues)

### ❤️ Support Us

If you like this mod, feel free to:
- ⭐ **Star** the project on GitHub
- 🐛 Report bugs
- 💡 Suggest improvements
- 🌍 Help with translations
- 📢 Share with your friends!

---

<div align="center">

**Made with ❤️ by Cubenyx-Studio**

[![GitHub](https://img.shields.io/badge/GitHub-Cubenyx--Studio-181717?style=for-the-badge&logo=github)](https://github.com/Cubenyx-Studio)

*Have fun with Minecraft Overlay!* 🎮✨

</div>

