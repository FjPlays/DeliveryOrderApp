# Delivery Order App

Simple delivery tracking app built for a take-home assessment. Customers can view their orders and filter by status. Drivers can update the order status from the detail screen.

---

## Running the app

1. Clone the repo and open in Android Studio
2. Wait for Gradle sync
3. Run on emulator or device (API 24+)

Uses MockAPI.io as the backend — no extra setup needed.

---

## Running tests

```bash
./gradlew test
```

GitHub Actions runs this automatically on every push.

---

## Backend

Set up MockAPI.io with an `orders` resource. Endpoints used:

- `GET /orders` — list orders
- `GET /orders?customerId=x` — filter by customer
- `GET /orders/:id` — order detail
- `POST /orders` — create order
- `PUT /orders/:id` — update status

---

## Stack

- Jetpack Compose + Material3
- Ktor — coroutine-native, pairs well with Kotlinx Serialization
- Koin — simpler setup than Hilt for a project this size
- MVVM + Clean Architecture
- StateFlow for UI state

---

## Architecture

Went with MVVM and split into three layers — domain, data, and presentation. Domain has no Android dependencies so the logic is easy to unit test. Each screen has a single UiState data class exposed through a ViewModel to keep state consistent.

Chose Ktor over Retrofit because it's Kotlin-first and doesn't need extra converters. Koin over Hilt was a time trade-off — Hilt would be better for a team project.

---

## Trade-offs

Kept the focus on the core flow — list, filter, detail, status update. What I left out due to time:

- No local caching, always fetches from API
- No pagination
- No Create Order UI (API supports it, just no screen for it)
- Base URL is hardcoded — would move to `local.properties` in production

---

## Testing

Focused tests on the use case and ViewModel layers since that's where the logic lives. Covered the happy path, filtering, and error states.

---

## Real-time driver tracking

If I had more time I'd add a live map using Google Maps SDK. The detail screen would have a "Track Driver" button opening a map with the driver's location updating in real-time via WebSockets — Ktor has a WebSocket client that would wire into the existing setup cleanly. A simpler fallback would be polling the API every few seconds using a coroutine loop.

---

## What I'd add for production

- Room for offline caching
- Paging 3 for large order lists
- Crashlytics for monitoring
- Typed error handling instead of raw exception messages
- CI extended to handle signing and Play Store deployment
- SSL pinning and API key management for security
- ProGuard rules to obfuscate the release build

---

Given the 3-4 hour timeframe I kept the scope tight to avoid running into issues
I couldn't fix in time. The main features from the requirements are all covered —
order list with filtering, order detail, and status updates.