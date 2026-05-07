# Delivery Order App

Take-home assessment project. A simple delivery tracking app where customers can see their orders and drivers can update the status.

---

## Running the app

1. Clone the repo and open it in Android Studio
2. Let Gradle sync finish
3. Run on any emulator or device (API 24+)

No setup needed — it connects to a MockAPI.io endpoint I set up with sample order data.

---

## Running the tests

```bash
./gradlew test
```
- CI also runs tests automatically on every push via GitHub Actions.

---

## What it does

- Lists all orders, can filter by status (Pending / In Transit / Delivered)
- Tap an order to see the full details
- Update the status from the detail screen
- List auto-refreshes when you go back so changes show up immediately

---

## Stack

I went with:
- **Jetpack Compose + Material3** for UI
- **Ktor** for HTTP — felt more natural than Retrofit for a Kotlin-first project
- **Koin** for DI — less setup overhead than Hilt for a project this size
- **Kotlinx Serialization** for JSON parsing
- **MVVM + Clean Architecture** — domain, data, and presentation layers kept separate
- **StateFlow + UiState** — single state object per screen, easier to reason about

---

## Structure

Went with a standard clean arch setup:

```
data/        → DTOs, Ktor API service, repository impl
domain/      → models, repository interface, use cases
presentation → screens, viewmodels, components
di/          → Koin modules
```

---

## Decisions I made

**Ktor over Retrofit** — wanted to keep things idiomatic Kotlin. Ktor plays nicer with coroutines and doesn't need a separate serialization converter.

**Koin over Hilt** — faster to wire up for a scoped project like this. If this were a team codebase I'd probably lean Hilt for the compile-time checks.

**No Room/caching** — given the time constraint I skipped local persistence. The app always fetches fresh from the API which is fine for a mock setup.

---

## What I'd add given more time

- Local caching with Room so it works offline
- Proper pagination instead of loading everything at once
- A "Create Order" screen (the API supports it, just didn't build the UI)
- Environment-based config so the base URL isn't hardcoded
- Better error types — right now it just surfaces the raw exception message