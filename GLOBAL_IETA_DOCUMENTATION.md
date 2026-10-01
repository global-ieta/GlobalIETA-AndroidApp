# GLOBAL IETA — System Architecture & Technical Specifications

> **Tagline**: *"Different worlds. One connected foundation."*
>
> **Project Scope**: Enterprise-Grade Android Spatial Intelligence & Automation Platform built with **Jetpack Compose**, **Material Design 3 Expressive**, and **Clean Architecture**.

---

## 1. Executive Summary & Core Philosophy

**GLOBAL IETA** is an enterprise-grade spatial intelligence and industrial automation mesh designed to connect disparate operational verticals into a single cohesive runtime environment. Whether orchestrating autonomous aviation fleets (**AERO**), rendering sub-millisecond 6DoF spatial environments (**ARIN**), executing quantum AI queries (**AURA**), or managing virtual university research campuses (**CAMPUS**), GLOBAL IETA provides a zero-latency, high-performance Android experience.

### Core Architecture Philosophy
* **Unified Design System**: Built around a dark cyberpunk-inspired aesthetic (`GlobalIETAColor`) featuring 70-80% dark navy/black surface ratios, 15-20% off-white text contrast, 5-10% signal cyan highlights, monospace technical labels, and vertical accent borders.
* **Reactive & Unidirectional Data Flow (UDF)**: ViewModels expose immutable `StateFlow<UiState>` collected by Composables with lifecycle-aware flow collection, enforcing strict separation of concerns.
* **Clean Architecture & Domain Isolation**: Layered structure (Presentation, Domain, Data) with repository interfaces isolating domain models from data sources, backed by robust mock implementations simulating realistic network latency.
* **Responsive Multi-Pane Strategy**: Adapts seamlessly between mobile smartphones (<600dp) and multi-pane tablet/foldable form factors (≥600dp dual-column layout).
* **GPU-Accelerated Visual Effects**: Lightweight radial gradient brushes (`cyanGlow`, `auraPulseGlow`) providing ambient glow effects without software blur stacks or rendering penalties.

---

## 2. Centralized Design System (`com.example.ieta.core.design`)

### 2.1 Color Token Matrix (`GlobalIETAColor`)

The visual color system enforces precise surface-to-accent balance across all screens:
* **70-80% Surface Ratio**: Deep void navy backgrounds (`PrimaryBackground` `#020812`, `SecondaryBackground` `#06111D`, `DeepSurface` `#081522`, `ElevatedSurface` `#0B1928`).
* **15-20% Text Ratio**: High-contrast off-white hierarchy (`PrimaryText` `#F4F7FA`, `SecondaryText` `#AAB9C9`, `MutedText` `#718398`).
* **5-10% Signal Cyan Accent**: Used strictly for interactive highlights, focus strokes, and operational signals (`PrimaryCyan` `#00D9FF`, `SecondaryCyan` `#00A8D6`, `ElectricBlue` `#2578FF`, `AuraBlue` `#00CFFF`).

| Token Category | Token Name | Hex Code | Purpose / Usage |
| :--- | :--- | :--- | :--- |
| **Backgrounds** | `PrimaryBackground` / `PrimaryBg` | `#020812` | Main app background, root container void black |
| | `SecondaryBackground` / `SecondaryBg` | `#06111D` | Elevated bar background (TopBar, BottomBar, Footer) |
| | `DeepSurface` | `#081522` | Standard card, node container, and input field surface |
| | `ElevatedSurface` | `#0B1928` | Modal popups, dropdown menus, and pill tags |
| **Borders** | `Border` | `#183247` | Primary 1dp structural card & container border stroke |
| | `MutedBorder` | `#102536` | Secondary divider, top bar separator, and inactive stroke |
| **Text Hierarchy**| `PrimaryText` | `#F4F7FA` | Off-white high-contrast titles, headers, and primary labels |
| | `SecondaryText` | `#AAB9C9` | Muted body text, descriptions, and secondary labels |
| | `MutedText` | `#718398` | Low-priority metadata, timestamps, and subtle hints |
| **Signal Cyans** | `PrimaryCyan` | `#00D9FF` | Primary interactive cyan glow, active status, CTAs |
| | `SecondaryCyan` | `#00A8D6` | Secondary cyan highlight |
| | `ElectricBlue` | `#2578FF` | Secondary gradient accent color for buttons |
| | `AuraBlue` | `#00CFFF` | Signature quantum AI core glow color |
| **Vertical Accents**| `GamingPurple` | `#8B5CF6` | Gaming & Interactive / EQUORA vertical accent |
| | `EducationBlue` | `#2979FF` | EduTech & CAMPUS virtual university accent |
| | `EquineGreen` | `#21C88A` | Equine sports analytics & motion telemetry accent |
| | `BusinessCyan` | `#00D4FF` | CONNECTOR & Enterprise Workspace accent |
| | `MarketplaceOrange`| `#FF8A3D` | Global Marketplace spatial asset exchange accent |
| | `LegalGold` | `#D9A93A` | Legal, compliance & billing accent |
| **Status Signals** | `Success` | `#22C98B` | Operational status ok / online status badge |
| | `Warning` | `#F5B942` | Early access / degraded service badge |
| | `Error` | `#FF5D6C` | Connection fault / system error indicator |

---

### 2.2 Motion Design System (`Motion.kt`)

`GlobalIetaMotion` standardizes motion durations, easing curves, and spring specifications across the application:

#### Duration Tokens
```kotlin
object GlobalIetaMotion {
    const val Fast: Int = 120              // Micro-interactions, button presses, touch ripples
    const val Quick: Int = 180             // Focus border transitions, dropdown popups
    const val Standard: Int = 250          // Node selection scaling, tab switching
    const val Smooth: Int = 350            // Screen slide/fade enter-exit transitions
    const val Feature: Int = 450           // Component expansion, accordion toggles
    const val Hero: Int = 600              // Hero section entrance animation
    const val Splash: Int = 1200           // Splash screen radar ring pulse cycle
    const val GraphLinePulse: Int = 4500   // Data-flow dash-phase animation shift in EcosystemGraph
    const val AuraBreathing: Int = 2600    // Floating AURA button idle breathing cycle (0.98x - 1.02x)
}
```

#### Easing Specs
* `FastOutSlowInEasing` (`GlobalIetaMotion.FastOutSlowIn`): Standard deceleration curve for UI elements moving into position.
* `LinearOutSlowInEasing` (`GlobalIetaMotion.LinearOutSlowIn`): Incoming elements entering from off-screen.
* `FastOutLinearInEasing` (`GlobalIetaMotion.FastOutLinearIn`): Exiting elements leaving the screen.

#### Spring Physics Specs
* `cardPressSpring`: `SpringSpec(dampingRatio = MediumBouncy, stiffness = StiffnessLow)` for realistic touch feedback (`1.0x` → `0.985x` scale).
* `nodeSelectionSpring`: `SpringSpec(dampingRatio = LowBouncy, stiffness = StiffnessMedium)` for canvas node selection scaling (`1.0x` → `1.10x` scale).

---

### 2.3 Typography Hierarchy (`Type.kt`)

Typography pairs `FontFamily.Monospace` for technical system indicators and system headers with `FontFamily.SansSerif` for readable editorial titles and body text.

```kotlin
@Immutable
data class GlobalIetaTypography(
    val display: TextStyle = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.ExtraBold, fontSize = 38.sp, lineHeight = 42.sp, letterSpacing = (-1.0).sp),
    val hero: TextStyle = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.ExtraBold, fontSize = 32.sp, lineHeight = 38.sp, letterSpacing = (-0.5).sp),
    val sectionTitle: TextStyle = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold, fontSize = 24.sp, lineHeight = 30.sp, letterSpacing = (-0.2).sp),
    val cardTitle: TextStyle = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.SemiBold, fontSize = 17.sp, lineHeight = 22.sp, letterSpacing = 0.2.sp),
    val body: TextStyle = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Normal, fontSize = 14.sp, lineHeight = 20.sp, letterSpacing = 0.25.sp),
    val small: TextStyle = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Medium, fontSize = 12.sp, lineHeight = 16.sp, letterSpacing = 0.4.sp),
    val technicalLabel: TextStyle = TextStyle(fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 11.sp, lineHeight = 14.sp, letterSpacing = 2.5.sp),
    val eyebrow: TextStyle = TextStyle(fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 11.sp, lineHeight = 14.sp, letterSpacing = 2.5.sp)
)
```

---

### 2.4 GPU-Accelerated Glow Modifiers (`Glow.kt`)

To maintain 120 FPS target performance without expensive software blur layers:
* `Modifier.cyanGlow(...)`: Draws a radial gradient brush (`Brush.radialGradient`) behind rectangular/rounded containers with configurable radius, spread, alpha, and corner radius.
* `Modifier.auraPulseGlow(...)`: Renders a circular radial gradient glow behind status dots, active radar nodes, and the floating AURA button.

---

## 3. Reusable Design Components (`com.example.ieta.core.components`)

### 3.1 `GlobalIetaTopBar`
* **Height**: 68dp fixed container height with `#020812` to `#06111D` vertical gradient background.
* **Separator Line**: 1dp `#102536` (`MutedBorder`) bottom stroke drawn via `drawBehind`.
* **Brand Badge**: `GLOBAL IETA` branding logo accompanied by a pulsing green `● OPERATIONAL` badge (1200ms `FastOutSlowInEasing` reverse animation).
* **Action Icons**: Search icon, notifications icon with red/cyan badged counter, and primary command menu trigger icon (`Icons.Rounded.Menu`).

### 3.2 `GlobalIetaBottomBar`
* **Floating AURA Button**: Center-aligned 58dp floating action button elevated -18dp, featuring a 2600ms idle breathing scale (`0.98x` → `1.02x`), cyan border, and `auraPulseGlow`.
* **Tab Cells**: 4 primary navigation items (`Home`, `Verticals`, `Market`, `Profile`). Tapping an item triggers a 200ms `1.1x` icon scale and a 220ms cyan top indicator bar width animation (`0dp` → `20dp`).

### 3.3 `GlobalIetaButton` & `GlobalIetaOutlinedButton`
* **Gradient Style**: Cyan-to-Electric Blue gradient (`#00D9FF` → `#2578FF`) with dark text (`#020812`) for high legibility.
* **Press Scale**: 120ms spring press scale animation (`1.0x` → `0.985x`).
* **Interactive Targets**: Minimum 52dp height touch target with inline `CircularProgressIndicator` during loading states.

### 3.4 `GlobalIetaCard`
* **Surface**: Deep surface container (`#081522`) with a 1dp stroke (`#183247` `Border`), 16dp rounded corners, and a 120ms spring press scale.
* **Vertical Accent**: Left 3dp vertical accent border indicator line matching the card's vertical category color.

### 3.5 `GlobalIetaInput` (`GlobalIetaTextField`, `GlobalIetaDropdown`, `GlobalIetaCheckbox`)
* **Focus Transitions**: 180ms smooth focus border color transition (`#102536` → `#00D9FF`) with `cyanGlow` ambient elevation.
* **Error States**: Displays monospace error labels (`SYSTEM STATUS // ERROR: <message>`) with `#FF5D6C` border strokes.

### 3.6 `GlobalIetaStateComponents`
* **`GlobalIetaLoading`**: Canvas orbital ring loader with a 1800ms rotating radar sweep arc and inner pulsing core dot (`#00CFFF`).
* **`GlobalIetaEmptyState`**: Technical card container with `[ SYSTEM STATUS // NO ACTIVE DATA ]` header badge.
* **`GlobalIetaErrorState`**: Error card container with `#FF5D6C` accent border, error icon, and retry CTA button.

### 3.7 `GlobalIetaOverlays` (`GlobalIetaBottomSheet` & `GlobalIetaDialog`)
* **Sheet Geometry**: 20-24dp top rounded corners, 44dp x 4dp cyan drag handle bar (`#00D9FF`), and 82% dark backdrop scrim (`#020812`).
* **Entrance Motion**: Combined slide-up vertically (`slideInVertically`) and fade-in (`fadeIn`) entry transitions.

---

## 4. Home Hub & Ecosystem Graph Mechanics (`feature/home`)

### 4.1 Editorial Hero Section
* **Technical Eyebrow**: `[ GLOBAL OPERATIONAL MESH // ONLINE ]` rendered in monospace typography (`2.5sp` letter spacing, `#00D9FF`).
* **Headline**: Editorial SansSerif headline `AI AND SOFTWARE / FOR EVERYDAY / OPERATIONS.` (`32sp`, ExtraBold).
* **CTAs**: Primary `EXPLORE MATRIX` button scrolling smoothly to the graph, and `MEET AURA AI` outlined button routing directly to AURA Quantum AI.

### 4.2 System Metrics Section
Displays live telemetry metric counters across 3 enterprise stats cards:
1. `ACTIVE NODES`: `1,845,000+` connected spatial and AI nodes.
2. `ACTIVE COUNTRIES`: `142` operational global regions.
3. `SYSTEM UPTIME`: `99.999%` fault-tolerant mesh SLA.

### 4.3 Interactive Canvas Node Graph (`EcosystemGraph.kt`)
* **Topology**: Central node (`GLOBAL IETA` `CORE-00`) surrounded by 7 peripheral nodes (`AURA`, `EDUCATION`, `BUSINESS`, `LEGAL`, `GAMING`, `EQUINE`, `MARKETPLACE`).
* **Canvas Math**: Positions computed dynamically via polar coordinates:
  $$x = \text{centerX} + r \cdot \cos(\theta), \quad y = \text{centerY} + r \cdot \sin(\theta)$$
* **Animated Dash Phase**: Connections drawn with `PathEffect.dashPathEffect(floatArrayOf(12f, 8f), dashPhase)` animating over 4500ms (`LinearEasing`).
* **Selection State**: Selected node scales `1.10x` using `nodeSelectionSpring`, dims non-selected nodes (`alpha = 0.5f`), and opens a modal `GlobalIetaBottomSheet` containing node technical code (`NODE-AI-01`), vertical description, and navigation CTA button.

### 4.4 Product Matrix & Vertical Highlights
* **Product Matrix**: Lists 10 core products with status badges (`● AVAILABLE`, `● EARLY ACCESS`, `● ENTERPRISE`), category chips, vertical accent borders, and navigation triggers.
* **Mobile Footer Accordion**: Expandable footer accordion groups (`ECOSYSTEM`, `INDUSTRIES`, `COMPANY`, `RESOURCES & LEGAL`) with smooth `expandVertically` / `shrinkVertically` transitions.

---

## 5. Complete Directory of All 26 Screens & Feature Modules

1. **Splash Screen** (`SplashScreen.kt`): Futuristic boot sequence with pulsing radar sweep rings, logo scale entrance, and status logs routing to Home.
2. **Home Hub** (`HomeScreen.kt`): Ecosystem command center featuring system telemetry metrics, interactive `EcosystemGraph`, product matrix, and mobile footer.
3. **AURA Quantum AI Core & Chat** (`AuraScreen.kt` & `AuraChatScreen.kt`): Natural language AI operator interface with streaming chat messages, confidence scores, suggested action chips, and prompt templates.
4. **ARIN Spatial Engine** (`ArinScreen.kt`): Spatial computing dashboard highlighting sub-2.4ms pose alignment, 6DoF tracking, 120 FPS target monitor, and spatial mesh toggles.
5. **AERO Mobility** (`AeroScreen.kt`): Aviation and drone fleet telemetry control room with live 3D radar grid visualizer, ADS-B signal fusion metrics, and collision avoidance alerts.
6. **IETA CAMPUS** (`CampusScreen.kt`): Virtual university portal offering tele-presence surgical labs, engineering physics simulations, AI tutor assistance, and course catalog listings.
7. **IETA CONNECTOR** (`ConnectorScreen.kt`): Enterprise API gateway managing REST, gRPC, and WebSockets connections, latency telemetry (<0.8ms), and zero-trust security rules.
8. **RIDEOS Core** (`RideOSScreen.kt`): Autonomous urban fleet operating system screen tracking shuttle dispatch, V2X mesh communication, and LiDAR point-cloud telemetry.
9. **IETA WORKSPACE** (`WorkspaceScreen.kt`): Spatial productivity suite managing multi-monitor virtual displays, spatial whiteboards, digital twin avatars, and encrypted channels.
10. **Global Marketplace** (`MarketplaceScreen.kt`): Decentralized spatial asset exchange for 3D models, digital twins, licenses, and automated royalty smart contracts.
11. **Gaming & Interactive / EQUORA** (`GamingScreen.kt`): Spatial gaming hub demonstrating low-latency multiplayer mesh performance, physics benchmarks, and metaverse showcase.
12. **IETA Billing** (`BillingScreen.kt`): Metered subscriptions portal offering real-time tier comparisons (Starter, Professional, Enterprise), entitlement vault, and invoice history.
13. **Industries Directory** (`IndustriesScreen.kt`): Index of supported ecosystem verticals (EduTech, Equine Analytics, Aviation, Enterprise, Legal, Gaming) with deployment specs.
14. **Company Information** (`CompanyScreen.kt`): About Global IETA overview, mission statement, executive leadership, global operational hubs, and company timeline.
15. **Developer Resources** (`ResourcesScreen.kt`): Developer documentation hub with downloadable SDK links, API reference guides, code snippets, and community forum links.
16. **Early Access Protocol** (`EarlyAccessScreen.kt`): Interactive signup portal with organization, role, and tier selection, submitting request to `EarlyAccessRepository` and returning a reference ID dialog.
17. **System Authentication / Sign In** (`AuthScreen.kt`): Credentials portal supporting Sign In / Register tabs, biometric passkey option, and guest mode bypass.
18. **Architect Profile** (`ProfileScreen.kt`): User profile dashboard displaying security clearance level, active node subscriptions, connected devices, and sign-out actions.
19. **System Settings** (`SettingsScreen.kt`): Configuration screen allowing theme adjustments, notification channel toggles, rendering quality selection, and cache clearing.
20. **Support Matrix** (`SupportScreen.kt`): Support ticket portal with new ticket creation modal, priority selector (Low, Medium, High, Critical), and real-time ticket tracking.
21. **System Specs / About** (`AboutScreen.kt`): Technical specification sheet displaying app version, build ID, kernel architecture, open-source licenses, and memory usage.
22. **Privacy Policy** (`LegalScreen.kt` / `PrivacyScreen`): Quantum-encrypted data handling documentation and zero-knowledge storage privacy guardrails.
23. **Terms of Service** (`LegalScreen.kt` / `TermsScreen`): Terms of service, spatial licensing agreement, and enterprise SLA commitments.
24. **Global Search Engine** (`SearchScreen.kt`): Cross-platform search interface with real-time query filtering across products, docs, and industry verticals.
25. **System Notifications & Logs** (`NotificationsScreen.kt`): Real-time system event feed with severity filters (INFO, WARNING, ERROR) and audit trail logs.
26. **Primary Command Menu** (`MenuScreen.kt`): Full-screen navigation index divided into 4 core sections (`CAT-ECO-01`, `CAT-IND-02`, `CAT-SYS-03`, `CAT-LGL-04`).

---

## 6. Navigation Compose Motion Architecture (`AppNavigation.kt`)

`AppNavigation.kt` manages screen routing using Jetpack Navigation Compose with custom motion transitions:

```kotlin
// Standard Horizontal Slide & Fade Transitions
enterTransition = {
    fadeIn(animationSpec = GlobalIetaMotion.smoothTweenSpec()) +
    slideInHorizontally(
        animationSpec = GlobalIetaMotion.smoothTweenSpec(),
        initialOffsetX = { fullWidth -> fullWidth / 4 }
    )
},
exitTransition = {
    fadeOut(animationSpec = GlobalIetaMotion.smoothTweenSpec()) +
    slideOutHorizontally(
        animationSpec = GlobalIetaMotion.smoothTweenSpec(),
        targetOffsetX = { fullWidth -> -fullWidth / 4 }
    )
}

// AURA Quantum AI Core Vertical Emergence Transition
composable(
    route = Screen.Aura.route,
    enterTransition = {
        fadeIn(animationSpec = GlobalIetaMotion.smoothTweenSpec()) +
        slideInVertically(
            animationSpec = GlobalIetaMotion.smoothTweenSpec(),
            initialOffsetY = { fullHeight -> fullHeight / 3 }
        )
    },
    exitTransition = {
        fadeOut(animationSpec = GlobalIetaMotion.smoothTweenSpec()) +
        slideOutVertically(
            animationSpec = GlobalIetaMotion.smoothTweenSpec(),
            targetOffsetY = { fullHeight -> fullHeight / 3 }
        )
    }
)
```

---

## 7. Responsive Multi-Pane Strategy

GLOBAL IETA uses screen width breakpoints to adjust layout density dynamically:

| Form Factor | Breakpoint | Structural Layout Strategy |
| :--- | :--- | :--- |
| **Mobile Smartphone** | `< 600dp` | Single-column vertical scroll, bottom navigation bar, modal bottom sheets, expandable footer accordions |
| **Tablet / Foldable** | `≥ 600dp` | Dual-column side-by-side pane layout, side navigation rail, persistent Ecosystem Graph side panel, multi-column grid |

---

## 8. Clean Architecture & Mock Repository Architecture

The architecture enforces unidirectional data flow (UDF) across 3 distinct layers:

```
┌────────────────────────────────────────────────────────────────────────┐
│                          PRESENTATION LAYER                            │
│     Composables (UI)  <───>  ViewModels  <───>  UiState (Immutable)   │
└──────────────────────────────────┬─────────────────────────────────────┘
                                   │
                                   ▼
┌────────────────────────────────────────────────────────────────────────┐
│                             DOMAIN LAYER                               │
│     Domain Models (User, AuraMessage, Product) + Repository Interfaces │
└──────────────────────────────────▲─────────────────────────────────────┘
                                   │
                                   ▼
┌────────────────────────────────────────────────────────────────────────┐
│                              DATA LAYER                                │
│     Mock Repositories (MockAuraRepo, MockProductRepo) / Flow Data      │
└────────────────────────────────────────────────────────────────────────┘
```

### Domain Interfaces & Mock Implementations
* **`AuraRepository`** / **`MockAuraRepository`**: Exposes chat message flows (`getMessages(): Flow<List<AuraMessage>>`), simulates quantum AI query processing using `delay(800)`, and returns context-aware responses.
* **`ProductRepository`** / **`MockProductRepository`**: Provides product catalog flows, real-time search filtering (`searchProducts(query)`), and vertical categorization.
* **`SupportRepository`** / **`MockSupportRepository`**: Handles support ticket creation (`createTicket(...)`) and ticket history tracking.
* **`AuthRepository`** / **`MockAuthRepository`**: Simulates biometric/credential sign-in, profile management, and sign-out states.
* **`EarlyAccessRepository`** / **`MockEarlyAccessRepository`**: Validates early access applications and generates unique reference protocol IDs (e.g., `REF-IETA-8921`).

---

## 9. Complete Package Layout (`com.example.ieta`)

```
com.example.ieta/
├── MainActivity.kt
├── core/
│   ├── components/
│   │   ├── GlobalIetaBottomBar.kt
│   │   ├── GlobalIetaButton.kt
│   │   ├── GlobalIetaCard.kt
│   │   ├── GlobalIetaInput.kt
│   │   ├── GlobalIetaLogo.kt
│   │   ├── GlobalIetaOverlays.kt
│   │   ├── GlobalIetaStateComponents.kt
│   │   └── GlobalIetaTopBar.kt
│   ├── design/
│   │   ├── Color.kt
│   │   ├── Glow.kt
│   │   ├── Motion.kt
│   │   ├── Shape.kt
│   │   ├── Theme.kt
│   │   └── Type.kt
│   └── navigation/
│       ├── AppNavigation.kt
│       └── Screen.kt
├── data/
│   └── repository/
│       ├── MockAuraRepository.kt
│       ├── MockAuthRepository.kt
│       ├── MockEarlyAccessRepository.kt
│       ├── MockProductRepository.kt
│       └── MockSupportRepository.kt
├── domain/
│   ├── model/
│   │   ├── AuraMessage.kt
│   │   ├── EarlyAccessRequest.kt
│   │   ├── Industry.kt
│   │   ├── Product.kt
│   │   ├── SupportTicket.kt
│   │   └── User.kt
│   └── repository/
│       ├── AuraRepository.kt
│       ├── AuthRepository.kt
│       ├── EarlyAccessRepository.kt
│       ├── ProductRepository.kt
│       └── SupportRepository.kt
├── feature/
│   ├── about/
│   ├── aero/
│   ├── arin/
│   ├── aura/
│   ├── auth/
│   ├── billing/
│   ├── campus/
│   ├── company/
│   ├── connector/
│   ├── earlyaccess/
│   ├── gaming/
│   ├── home/
│   │   └── components/
│   │       ├── EcosystemGraph.kt
│   │       ├── HeroSection.kt
│   │       └── MetricsSection.kt
│   ├── industries/
│   ├── legal/
│   ├── marketplace/
│   ├── menu/
│   ├── notifications/
│   ├── profile/
│   ├── resources/
│   ├── rideos/
│   ├── search/
│   ├── settings/
│   ├── splash/
│   ├── support/
│   └── workspace/
└── ui/
    └── state/
        ├── AuraUiState.kt
        ├── AuthUiState.kt
        ├── EarlyAccessUiState.kt
        ├── HomeUiState.kt
        ├── ProductUiState.kt
        └── SupportUiState.kt
```

---

## 10. Build Verification & Unit Test Suite Results

The build configuration and automated unit tests have been executed and verified:

### Gradle Commands
```bash
# Execute Unit Test Suite
./gradlew :app:testDebugUnitTest

# Assemble Debug Application Package
./gradlew :app:assembleDebug
```

### Test Suite Execution Summary
* **`MockRepositoriesTest`**:
  1. `productRepository_returnsProductsAndIndustries()` — PASSED
  2. `auraRepository_sendsMessageAndReceivesResponse()` — PASSED
  3. `authRepository_signInAndProfileManagement()` — PASSED
  4. `earlyaccessRepository_submitsAndRetrievesRequest()` — PASSED
  5. `supportRepository_createsAndRetrievesTicket()` — PASSED
* **`EcosystemHomeTest`**:
  6. `ecosystemNodes_containsAllSevenCoreNodes()` — PASSED
  7. `homeProducts_containsExpectedProductsWithStatusAndRoutes()` — PASSED
  8. `menuGroups_containsStructuredCategoriesAndItems()` — PASSED

> **Build Status**: **SUCCESS (100% Pass Rate)**

---

## 11. Future Backend Integration Roadmap

To migrate GLOBAL IETA from mock repositories to live enterprise cloud services:

1. **HTTP & WebSocket Integration**: Replace `MockAuraRepository` and `MockProductRepository` with Ktor/Retrofit client implementations calling REST and WebSocket endpoints for real-time AI and radar telemetry streaming.
2. **Local Persistence (Room DB)**: Add Room tables for caching spatial nodes, user profiles, product catalogs, and offline support tickets.
3. **Dependency Injection (Hilt / Koin)**: Inject production repository implementations using `@Provides` / `@Binds` modules based on build flavors (`mock` vs `prod`).
4. **Security & Authentication**: Implement EncryptedSharedPreferences for secure JWT token storage and OAuth2 refresh token flows.
