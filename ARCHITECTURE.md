# Global IETA Technical Architecture Specification

> **Technical Architecture, Design Patterns, Canvas Math, and State Management Blueprint**

---

## 🏛️ Architecture Overview & Layering

The Global IETA Android application is built following **Clean Architecture** principles and **Unidirectional Data Flow (UDF)** patterns. The codebase is organized into three distinct layers to ensure separation of concerns, testability, and long-term maintainability.

```
                  ┌──────────────────────────────────────────────┐
                  │              UI / Feature Layer              │
                  │   (Composables, ViewModels, StateFlow)       │
                  └──────────────────────┬───────────────────────┘
                                         │
                                         │ Emits UI State & Collects User Intent
                                         ▼
                  ┌──────────────────────────────────────────────┐
                  │                 Domain Layer                 │
                  │   (Entity Models, Repository Contracts)      │
                  └──────────────────────▲───────────────────────┘
                                         │
                                         │ Implements Contracts
                                         │
                  ┌──────────────────────┴───────────────────────┐
                  │                  Data Layer                  │
                  │   (Mock Repositories, API Clients, DB)       │
                  └──────────────────────────────────────────────┘
```

---

## 🔄 Unidirectional Data Flow (UDF) & State Management

All screens in Global IETA enforce strict Unidirectional Data Flow (UDF):

1. **User Action / Event**: User interacts with a Composable UI element (e.g., clicks a button, submits text, taps a node on the Canvas graph).
2. **ViewModel Intent Processing**: The Composable delegates the event to the feature's `ViewModel` method (e.g., `auraViewModel.sendMessage(...)`).
3. **Domain & Data Operation**: The `ViewModel` invokes the appropriate `Repository` within `viewModelScope`.
4. **Immutable State Emission**: Upon completion or progress update, the `ViewModel` updates its internal `MutableStateFlow` and emits a new immutable `UiState` instance to the public `StateFlow`.
5. **Composable Recomposition**: The UI observes `StateFlow` via `collectAsStateWithLifecycle()` and recomposes smoothly to render the new state.

### State Flow Pattern Example (`AuraViewModel` & `AuraUiState`)

```kotlin
// UI State (Immutable Sealed Representation)
@Immutable
data class AuraUiState(
    val isLoading: Boolean = false,
    val messages: List<AuraMessage> = emptyList(),
    val error: String? = null
)

// ViewModel Implementation
class AuraViewModel(
    private val auraRepository: AuraRepository = MockAuraRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuraUiState())
    val uiState: StateFlow<AuraUiState> = _uiState.asStateFlow()

    fun sendMessage(text: String) {
        if (text.isBlank()) return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val updatedMessages = auraRepository.sendMessage(text)
            _uiState.update { 
                it.copy(isLoading = false, messages = updatedMessages) 
            }
        }
    }
}
```

---

## 🗄️ Repository Pattern & Data Layer

The application separates business contracts from data providers:

- **Domain Repository Contracts** (`com.example.ieta.domain.repository`): Interfaces defining data contracts without coupling to specific frameworks (e.g., `AuraRepository`, `AuthRepository`, `ProductRepository`, `SupportRepository`, `EarlyAccessRepository`).
- **Data Repository Implementations** (`com.example.ieta.data.repository`): Concrete implementations fetching from local mocks, persistent databases, or remote APIs (e.g., `MockAuraRepository`, `MockAuthRepository`).

This abstraction allows seamless substitution of mock data providers with live REST / GraphQL / WebSocket API integrations without modifying UI composables or domain models.

---

## 🎨 Ecosystem Canvas Node Graph Mechanics (`EcosystemGraph.kt`)

The **Ecosystem Canvas Node Graph** renders a futuristic 2D radial mesh depicting the interconnectivity of Global IETA's 18+ ecosystem hubs.

### 1. Polar Coordinate Geometry & Transformation

Nodes are mapped radially around a center origin $(x_0, y_0)$:

$$\text{Center Position: } x_0 = \frac{\text{Canvas Width}}{2}, \quad y_0 = \frac{\text{Canvas Height}}{2}$$

For $N$ orbital nodes on an orbit with radius $R$, node index $i$ has angular displacement $\theta_i$:

$$\theta_i = \frac{2\pi \cdot i}{N} + \theta_{\text{offset}}$$

The Cartesian coordinates $(x_i, y_i)$ on the Canvas are calculated as:

$$x_i = x_0 + R \cdot \cos(\theta_i)$$
$$y_i = y_0 + R \cdot \sin(\theta_i)$$

### 2. Radial Touch Hit-Testing

When a user taps the Canvas surface at $(x_{\text{touch}}, y_{\text{touch}})$, the graph calculates the Euclidean distance $d_i$ to each node center $(x_i, y_i)$:

$$d_i = \sqrt{(x_{\text{touch}} - x_i)^2 + (y_{\text{touch}} - y_i)^2}$$

If $d_i \le R_{\text{hitbox}}$ (where $R_{\text{hitbox}} = R_{\text{node}} + \text{Padding}$), the node is selected, triggering focus callbacks and spring animations.

### 3. Dynamic Sine Wave Orbital Pulse Rendering

Connecting orbital lines render continuous particle energy pulses. The pulse progression along line segment $(P_0 \to P_i)$ is modulated using a normalized sine phase:

$$\text{Pulse Position } t = \left( \frac{\text{SystemTime} \pmod{T_{\text{pulse}}}}{T_{\text{pulse}}} \right)$$

$$P_{\text{pulse}}(t) = P_0 + t \cdot (P_i - P_0)$$

A radial gradient brush centered at $P_{\text{pulse}}(t)$ produces a neon cyan laser pulse travelling along the connection vector.

---

## ⚡ Motion & Animation Architecture (`Motion.kt`)

Centralized timing constants, easing curves, and spring physics guarantee high fluid motion consistency across all 18+ screens.

### Timing Tokens (`GlobalIetaMotion`)
- `Fast` ($120\text{ ms}$): Immediate micro-feedback (e.g., button press down state).
- `Quick` ($180\text{ ms}$): Card hovers and small icon state transitions.
- `Standard` ($250\text{ ms}$): Tab switches, dialog entries, and expansion panels.
- `Smooth` ($350\text{ ms}$): Full screen route transitions and drawer opens.
- `Feature` ($450\text{ ms}$): Hero element morphing and graph node focus.
- `Hero` ($600\text{ ms}$): Complex multi-element stagger sequences.
- `GraphLinePulse` ($4500\text{ ms}$): Continuous canvas orbital energy loop.
- `AuraBreathing` ($2600\text{ ms}$): Ambient neural breathing indicator cycle.

### Spring Specifications
- **Card Press**: `Spring.DampingRatioMediumBouncy` with `Spring.StiffnessLow`.
- **Node Selection**: `Spring.DampingRatioLowBouncy` with `Spring.StiffnessMedium`.

---

## 🧭 Navigation Architecture (`AppNavigation.kt`)

`AppNavigation.kt` wraps Jetpack Navigation Compose with tailored transition animations based on destination hierarchy:

- **Primary Destination Entry**: Horizontal slide-in with crossfade (`slideInHorizontally` + `fadeIn`).
- **Secondary / Modal Screens**: Vertical slide-up from bottom (`slideInVertically` + `fadeIn`).
- **Back Navigation**: Reverse exit transitions with `slideOutHorizontally` / `slideOutVertically`.

```kotlin
composable(
    route = Screen.Aura.route,
    enterTransition = { slideInHorizontally(initialOffsetX = { it }) + fadeIn() },
    exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) + fadeOut() }
) {
    AuraScreen(navController = navController)
}
```

---

## 🚀 Future Backend API Migration Guide

Transitioning Global IETA from the current mock data layer to production backend services follows a straightforward 3-phase roadmap:

### Phase 1: HTTP & WebSocket Client Layer Integration
1. Add **Ktor Client** or **Retrofit2** with **OkHttp3** dependencies to `build.gradle.kts`.
2. Configure a centralized `NetworkModule` providing base URL configuration (`https://api.global-ieta.io/v1/`), serialization engines (KotlinX Serialization), and SSL pinning.

### Phase 2: Repository Swap
Replace mock repository instances with network-backed repositories implementing the existing domain interfaces:

```kotlin
// Before (Mock)
class MockAuraRepository : AuraRepository { ... }

// After (Live API Integration)
class RemoteAuraRepository(
    private val auraApiService: AuraApiService
) : AuraRepository {
    override async fun sendMessage(text: String): List<AuraMessage> {
        val response = auraApiService.postMessage(AuraRequest(text))
        return response.toDomainModel()
    }
}
```

### Phase 3: Auth & Token Refresh Interceptors
1. Enhance `AuthRepository` to persist access and refresh JWT tokens securely using **EncryptedSharedPreferences** or **Jetpack DataStore**.
2. Inject an OAuth2 Authenticator into the HTTP client to automatically handle $401 \text{ Unauthorized}$ token refresh flows without disrupting user sessions.
3. Establish a resilient WebSocket channel for real-time AURA AI streaming text and audio responses.

---

*© Global IETA. Architecture & Engineering Manual.*
