# Contributing to Global IETA Android App

Thank you for your interest in contributing to the **Global IETA Android Application**. This document provides guidelines, standards, and procedures to ensure clean code quality, consistency, and smooth collaboration.

---

## 🛠️ Developer Setup

### Prerequisites
1. **JDK**: Version 17 or higher.
2. **Android Studio**: Jellyfish (2024.1.1) or higher.
3. **Android SDK**: API Level 34 (Android 14) installed.
4. **Git**: Installed and configured on your environment.

### Getting the Code
```bash
git clone https://github.com/global-ieta/GlobalIETA-AndroidApp.git
cd GlobalIETA-AndroidApp
```

### Initial Build & Verification
Validate your local setup by building the project and executing unit tests:
```bash
# Build debug APK
./gradlew assembleDebug

# Run unit tests
./gradlew testDebugUnitTest
```

---

## 📐 Code Styling & Kotlin / Compose Guidelines

We adhere strictly to official Kotlin and Jetpack Compose coding conventions.

### Naming Conventions
- **Composables**: Use **PascalCase** for any `@Composable` function emitting UI (e.g., `GlobalIetaCard`, `EcosystemGraph`, `AuraChatScreen`).
- **Screen Routes & UI States**: Use **PascalCase** (e.g., `Screen.AuraChat`, `AuraUiState.Success`).
- **Functions & Variables**: Use **camelCase** for regular functions, viewmodel methods, and variables (e.g., `sendMessage()`, `currentRoute`).
- **Constants**: Use **UPPER_SNAKE_CASE** or PascalCase in companion/object scopes (e.g., `GlobalIetaMotion.Hero`).

### Immutable State Management
1. **Immutable UI States**: All UI state classes must be immutable data classes or sealed interfaces annotated with `@Immutable` or `@Stable`.
   ```kotlin
   @Immutable
   data class AuraUiState(
       val isLoading: Boolean = false,
       val messages: List<AuraMessage> = emptyList(),
       val errorMessage: String? = null
   )
   ```
2. **Read-Only Flow Exposure**: Never expose `MutableStateFlow` from ViewModels. Always expose read-only `StateFlow<T>` using `asStateFlow()`.
   ```kotlin
   private val _uiState = MutableStateFlow(AuraUiState())
   val uiState: StateFlow<AuraUiState> = _uiState.asStateFlow()
   ```

### Jetpack Compose Performance & Recomposition Rules
- **Avoid Heavy Calculations in Composables**: Wrap expensive calculations or object creations in `remember` or `derivedStateOf`.
- **Stable Lambda References**: Pass method references or lambda parameters properly to prevent unnecessary child recomposition.
- **Lazy List Keys**: Always supply explicit `key` parameters in `LazyColumn` or `LazyRow` items (e.g., `items(list, key = { it.id })`).
- **Design Tokens**: Never hardcode hex colors or pixel dimensions. Use `GlobalIETAColor`, `GlobalIetaMotion`, and Material 3 dimensions.

---

## 🔀 Git Commit Message Conventions

We enforce [Conventional Commits](https://www.conventionalcommits.org/) format for clear, automated change logs.

### Format
```text
<type>(<scope>): <short summary>

[optional body description]
```

### Allowed Types
- `feat`: A new feature or screen addition.
- `fix`: A bug fix or patch.
- `docs`: Documentation changes (`README`, `ARCHITECTURE`, etc.).
- `style`: Formatting, spacing, or design token alignment (no functional code change).
- `refactor`: Code restructurings without behavior or API changes.
- `perf`: A code change that improves performance or reduces recompositions.
- `test`: Adding or correcting tests.
- `chore`: Build script, Gradle, or dependency updates.

### Examples
```bash
git commit -m "feat(aura): add real-time streaming state to AuraChatScreen"
git commit -m "fix(canvas): correct hit-test offset in EcosystemGraph polar calculation"
git commit -m "docs: create CONTRIBUTING.md and ARCHITECTURE.md"
```

---

## ✅ Pull Request (PR) Checklist

Before submitting a Pull Request, verify the following checklist:

- [ ] **Clean Build**: Running `./gradlew assembleDebug` succeeds without compilation errors or warnings.
- [ ] **Unit Tests**: Running `./gradlew testDebugUnitTest` passes 100%.
- [ ] **Architecture Compliance**:
  - UI logic resides in Composables.
  - State management is driven by ViewModel and `StateFlow`.
  - Data sources implement domain repository interfaces.
- [ ] **Design Tokens**: Colors, motions, and borders use `GlobalIETAColor` and `GlobalIetaMotion` tokens.
- [ ] **Git History**: Commits are clean and follow Conventional Commit standards.

---

Thank you for helping build the future of the **Global IETA Connected Ecosystem**!
