# Assignment Submission - Android App Refactoring

**Name:** Soumadeep Barik  
**Date:** December 27, 2025  
**Assignment:** Fix and Refactor Broken Android Compose Project

---

## Overview

This document covers the issues I found in the provided Android project, how I fixed them, and the improvements I made. The app was crashing and poorly structured, so I refactored it to follow proper Android development patterns.

## Key Issues I Identified

### 1. Memory Leak
The MainActivity had a static reference storing the activity instance:
```kotlin
companion object {
    var leakedActivity: MainActivity? = null
}
```
This prevents the activity from being garbage collected, causing memory leaks.

### 2. UI Freezing
The repository was using `Thread.sleep(2000)` on the main thread, which blocked the UI completely. Plus, network calls were happening on the main thread using `GlobalScope.launch(Dispatchers.Main)`.

### 3. Wrong Coroutine Usage
- Used deprecated `GlobalScope` instead of proper scopes
- Network operations running on `Dispatchers.Main` instead of IO
- No lifecycle awareness

### 4. Bad Repository Implementation
The repository was a singleton object with blocking functions being called directly from Composables. No interfaces, no abstraction.

### 5. No Navigation
Even though there was a DetailScreen, there was no way to navigate to it. Navigation Compose wasn't set up.

### 6. No State Management
State was managed directly in Composables using `remember`, which doesn't survive configuration changes. No ViewModel layer at all.

### 7. UI and Repository Tightly Coupled
Composables were directly calling repository methods, making testing impossible and violating separation of concerns.

### 8. No Architecture
There was no clear architectural pattern. Everything was mixed together - UI, business logic, and data access.

### 9. Build Issues
- `compileSdk` had wrong syntax: `compileSdk { version = release(36) }`
- Duplicate `buildFeatures` blocks where the second one overwrote the first
- BuildConfig wasn't generating because of this

### 10. Other Issues
- Missing Room KAPT processor
- No DAO in the database
- Missing `@Entity` annotations
- API key hardcoded as "demo"
- Wrong API response type
- No Hilt setup
- Missing internet permission

## How I Solved Them

### Fixed Memory Leak
Removed the static reference completely. Now using Hilt for dependency injection, so no need for any static references.

### Fixed UI Freezing
- Removed `Thread.sleep()` completely
- Created a proper ViewModel with `viewModelScope`
- All network calls now happen on IO dispatcher automatically through suspend functions
- UI updates happen on Main thread through StateFlow

### Fixed Coroutines
Changed from:
```kotlin
GlobalScope.launch(Dispatchers.Main) { }
```
To:
```kotlin
viewModelScope.launch {
    // This automatically handles lifecycle and uses proper dispatchers
}
```

### Implemented Clean Architecture
Restructured the entire project into three layers:

**Presentation Layer (UI)**
- NewsScreen.kt
- DetailScreen.kt
- NewsViewModel.kt
- NavigationHost.kt

**Domain Layer (Business Logic)**
- ArticlesRepository (interface)
- ArticlesImpl (use case implementation)
- Articles (domain models)

**Data Layer**
- GetArticlesRepository (interface)
- GetArticlesImpl (implementation)
- ApiService
- AppDatabase
- DTOs and Mappers

Each layer only knows about the one below it through interfaces.

### Added Dependency Injection
Set up Hilt with these modules:
- `NetworkModule` - provides Retrofit and OkHttp
- `ApiServiceModule` - provides ApiService
- `RepositoryModule` - binds repository implementations
- `UseCaseModule` - binds use case implementations

Also created `BrokenAndroidComposeProject` application class with `@HiltAndroidApp`.

### Implemented Navigation
Added Navigation Compose and created a proper navigation graph:
```kotlin
@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.NewsScreen.route) {
        composable(Screen.NewsScreen.route) { NewsScreen() }
        composable(Screen.DetailScreen.route) { DetailScreen() }
    }
}
```

### Added State Management
Created a sealed class for UI states:
```kotlin
sealed class ResultState {
    object Loading: ResultState()
    data class Success(val articlesList: List<ArticlesData>): ResultState()
    data class Failure(val errorMessage: String): ResultState()
}
```

ViewModel exposes this through StateFlow, and the UI reacts to state changes.

### Fixed Build Configuration
- Changed `compileSdk { version = release(36) }` to `compileSdk = 36`
- Merged duplicate buildFeatures blocks:
```kotlin
buildFeatures {
    compose = true
    buildConfig = true
}
```
- Added product flavors with BuildConfig fields for API_KEY and BASE_URL
- Added missing dependencies (Hilt, Navigation, Room KAPT)

### Added Proper Error Handling
Repository now returns `Result<T>`:
```kotlin
return try {
    val response = apiService.getArticles(query)
    if (response.isSuccessful) {
        Result.success(response.body()!!.toDto())
    } else {
        Result.failure(Exception("Unable to connect"))
    }
} catch (e: Exception) {
    Result.failure(e)
}
```

### Fixed Room Setup
Added proper annotations:
```kotlin
@Entity
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String
)
```

## Improvements Beyond Bug Fixes

### Better UI/UX
- Added Material 3 design with elevated cards
- Proper loading, error, and success states with appropriate UI for each
- Dynamic toolbar title that changes based on current screen
- Clickable news items that navigate to detail screen

### Code Quality
- Organized code into proper packages (presentation, domain, data)
- Used meaningful names for classes and functions
- Followed Kotlin coding conventions
- Made the code testable (though I didn't write tests due to time)

### Performance
- Used LazyColumn for efficient list rendering
- Proper lifecycle management prevents memory leaks
- StateFlow only updates UI when state actually changes

---

## Assumptions I Made

1. **API Structure**: Assumed the News API returns a standard response with an `articles` array. Used a placeholder API key that needs to be replaced with a real one.

2. **Features**: Assumed this is a simple news reader - list of articles and detail view. No search, favorites, or categories needed for now.

3. **Design**: Went with Material 3 and a simple card-based layout since no specific design was provided.

4. **No Authentication**: Assumed the app doesn't need user login or authentication.

5. **Minimum SDK**: Kept it at API 24 (Android 7.0) which seemed reasonable for a modern app.

---

## What I'd Improve Given More Time

### Offline Support
Right now the app needs internet to work. I'd implement proper offline-first architecture:
- Use Room as the single source of truth
- Cache API responses locally
- Show cached data immediately, then update from network
- Add a refresh mechanism

### Testing
Would add:
- Unit tests for ViewModels and UseCases
- Repository tests with mock API
- UI tests for critical user flows

### Better Error Handling
- Different error messages for different scenarios (no internet, server error, timeout)
- Retry button on error screen
- Better loading indicators

### Additional Features
- Search functionality
- Pull-to-refresh
- Pagination for loading more articles
- Save favorites locally

### Image Loading
Add Coil library to load and cache article images properly.

---

## Summary

The app went from completely broken to a working, well-structured application following Clean Architecture and MVVM patterns. All major issues (memory leaks, UI freezing, poor architecture) are resolved. The code is now maintainable, testable, and ready for adding new features.

The build works correctly, BuildConfig generates properly, and navigation flows smoothly between screens. Hilt handles all dependency injection, making the code loosely coupled and easy to modify.