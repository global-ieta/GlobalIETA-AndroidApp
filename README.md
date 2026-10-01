# Global IETA Android Application

> **Different worlds. One connected foundation.**

![Android 14+](https://img.shields.io/badge/Android-14%2B%20%28API%2034%2B%29-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin 2.0+](https://img.shields.io/badge/Kotlin-2.0%2B-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-M3-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)
![Material 3](https://img.shields.io/badge/Material%203-Expressive-757575?style=for-the-badge&logo=materialdesign&logoColor=white)
![Architecture](https://img.shields.io/badge/Architecture-Clean%20UDF-00D9FF?style=for-the-badge)

---

## 🌌 Overview & Core Product Vision

The **Global IETA** Android application serves as the mobile gateway to the Global IETA Connected Ecosystem. Designed around an expressive, futuristic dark aesthetic, Global IETA unifies autonomous mobility, neural artificial intelligence, enterprise workspaces, global campus networking, aerospace telematics, and cross-industry asset exchanges under a single, cohesive architecture.

Built natively using **Kotlin 2.0+** and **Jetpack Compose Material 3**, the application delivers fluid 60fps+ animations, interactive visual node graph representations, and a resilient Unidirectional Data Flow (UDF) pattern.

---

## 🎨 Design System & Color Tokens

Global IETA employs a strict **70-80-15-5 Design Rule** to preserve dark-mode visual hierarchy and prevent visual clutter:

- **70–80% Deep Backgrounds & Surfaces**: Ultra-dark navy and black tones (`#020812` Primary Background, `#06111D` Secondary Background, `#081522` Deep Surface).
- **15–20% High-Contrast Text**: Crisp off-white hierarchy (`#F4F7FA` Primary, `#AAB9C9` Secondary, `#718398` Muted).
- **5–10% Interactive Cyan Signal**: Vibrant neon cyan used exclusively for interactive elements, focus borders, and active status indicators (`#00D9FF` Signal Cyan, `#2578FF` Electric Blue, `#00CFFF` Aura Blue).

### Vertical Accent Palette

| Vertical Accent | Token Name | Color Hex | Primary Context |
| :--- | :--- | :--- | :--- |
| **Gaming / EQUORA** | `GamingPurple` | `#8B5CF6` | Immersive Metaverse & Gaming |
| **Campus / Education** | `EducationBlue` | `#2979FF` | Academics & Knowledge Hubs |
| **Equine & AgTech** | `EquineGreen` | `#21C88A` | Biological & Animal Telematics |
| **Business Operations** | `BusinessCyan` | `#00D4FF` | Commercial & Fleet Metrics |
| **Marketplace** | `MarketplaceOrange` | `#FF8A3D` | Decentralized Commerce & Tokens |
| **Legal & Compliance** | `LegalGold` | `#D9A93A` | Governance & Contracts |

---

## 🕸️ Interactive Canvas Node Graph Visualization

The centerpiece of the Global IETA Home Screen is the custom **Ecosystem Canvas Node Graph** (`EcosystemGraph.kt`). 

- **Polar Geometry Calculation**: Nodes are computed dynamically using polar coordinates ($\theta_i = \frac{2\pi \cdot i}{N}$) mapped to Canvas Cartesian coordinates $(x_i, y_i)$.
- **Dynamic Orbital Pulsing**: Orbital connection lines radiate glowing energy pulses rendered with trigonometric sine functions and dynamic particle physics.
- **Interactive Radial Hit-Testing**: Touch gestures on the Canvas utilize Euclidean distance calculation ($\sqrt{\Delta x^2 + \Delta y^2} \le R_{node}$) for high-precision node selection and spring-animated focus transitions.

---

## 🛸 Core Ecosystem Hubs Matrix

| Hub / Screen | Route | Core Functionality |
| :--- | :--- | :--- |
| **AURA AI** | `/aura` | Conversational neural AI assistant with multi-modal reasoning and live stream processing. |
| **ARIN** | `/arin` | Augmented Reality Intelligence Network for spatial mapping and real-time mesh telemetry. |
| **AERO** | `/aero` | Autonomous aerial vehicle management, drone navigation logs, and airspace monitoring. |
| **Campus** | `/campus` | Global educational portal, research repository access, and academic collaboration. |
| **Connector** | `/connector` | Universal hardware bridge for IoT devices, industrial sensors, and edge telemetry. |
| **RideOS** | `/rideos` | Next-generation urban mobility, autonomous fleet dispatching, and route telemetry. |
| **Workspace** | `/workspace` | Real-time collaborative enterprise portal with document synching and team boards. |
| **Marketplace** | `/marketplace` | Decentralized digital asset exchange, API services catalog, and smart transaction ledger. |
| **Gaming / EQUORA** | `/gaming` | Virtual universe environment, avatar customization, and real-time graphics streaming. |
| **Billing** | `/billing` | Enterprise subscription management, token balance ledger, and usage analytics. |
| **Industries** | `/industries` | Vertical-specific enterprise modules (Healthcare, Energy, Logistics, Agriculture). |
| **Company** | `/company` | Global entity hierarchy, executive vision, career portal, and investor updates. |
| **Resources** | `/resources` | Developer documentation, API reference guides, SDKs, and system status feeds. |
| **Early Access** | `/early_access` | Beta feature registration, preview track enrollment, and insider feedback channels. |
| **Auth** | `/auth` | Multi-factor authentication, biometric identity assertion, and token issuance. |
| **Profile** | `/profile` | User identity management, security keys, and cross-device session tracking. |
| **Settings** | `/settings` | System-wide preferences, theme customization, memory caching, and diagnostic controls. |
| **Support** | `/support` | Automated diagnostic ticketing, live agent escalation, and system health status. |

---

## 🏗️ Tech Stack & Clean Architecture

- **Language**: Kotlin 2.0+
- **UI Framework**: Jetpack Compose with Material Design 3 Expressive APIs
- **Navigation**: Navigation Compose with customized horizontal/vertical slide and fade transitions
- **Asynchronous Flow**: Kotlin Coroutines & `StateFlow` / `SharedFlow`
- **Architecture**: Clean Architecture + Unidirectional Data Flow (UDF)
  - **Data Layer**: Repositories, Data Sources, Mock Services
  - **Domain Layer**: Pure Kotlin Models, Use Cases / Business Rules
  - **UI / Feature Layer**: ViewModels, Immutable UI States, Declarative Composables

---

## ⚡ Getting Started & Build Commands

### Prerequisites
- Android Studio Jellyfish (2024.1.1) or newer
- JDK 17+
- Android SDK 34 (Android 14+)

### Build Commands

To assemble the debug APK:
```bash
./gradlew assembleDebug
```

To run all unit tests:
```bash
./gradlew testDebugUnitTest
```

To run instrumented UI tests:
```bash
./gradlew connectedDebugAndroidTest
```

---

## 📁 Package Structure Layout (`com.example.ieta`)

```
com.example.ieta
├── MainActivity.kt               # Single Activity host
├── core
│   ├── components               # Reusable atomic UI components (Buttons, Cards, Inputs, TopBars)
│   ├── design                   # Tokens (Color, Motion, Glow, Shape, Type, Theme)
│   └── navigation               # NavHost, Routes (Screen.kt), and AppNavigation transitions
├── data
│   └── repository               # Data implementations & mock data providers
├── domain
│   ├── model                    # Core business entity models
│   └── repository               # Repository interface contracts
├── feature                      # Modular feature screens
│   ├── aura                     # AURA AI conversational feature & ViewModel
│   ├── home                     # Home Dashboard, EcosystemGraph canvas, Hero & Metrics
│   ├── auth                     # Authentication flow & ViewModel
│   ├── earlyaccess              # Early Access enrollment & ViewModel
│   ├── support                  # Support ticketing & ViewModel
│   └── [14+ additional feature hubs...]
└── ui
    └── state                    # Sealed UI state classes (HomeUiState, AuraUiState, etc.)
```

---

## 📖 Further Documentation

- For full architecture breakdown and design patterns, consult [ARCHITECTURE.md](ARCHITECTURE.md).
- For developer setup and contribution guidelines, read [CONTRIBUTING.md](CONTRIBUTING.md).
- For complete system specs and business requirements, refer to [GLOBAL_IETA_DOCUMENTATION.md](GLOBAL_IETA_DOCUMENTATION.md).

---

*© Global IETA. All Rights Reserved.*
