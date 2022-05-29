# Technologies

Quick overview of the technologies used

- Kotlin
- Jetpack compose
- NavHostController for navigation (in MainActvitiy.kt)

# Architecture

The architecture/file structure used for the project

- MVVM (Model View ViewModel)

# Requirements

The requirements of both the project as the platform in which it was developed

## Project

### Required

✔️ Trainers can see a list of Pokémon with some general information, such as name, number, picture and types..

✔️ Trainers can search for Pokémon by name and number..

✔️ Trainers can see the details of a Pokémon, with the general info (name, number, picture, types) but also things like abilities, move set and stats..

✔️ Trainers can see a bigger version of the picture of a Pokémon (from the details of one). Include zoom & pan. (for web: we’re looking for a lightbox component)

✔️ Trainers can mark a Pokémon as favourite (or remove it from favourites)..

✔️ Trainers have a separate list with only their favourite Pokémon.

✔️ Trainers can use the app offline for content they’ve already viewed. [NOT FOR WEB]

### Nice to have

✔️ Besides their favourites, trainers would also like to manage their team of Pokémon. A trainer’s team is limited to 6 Pokémon..

✔️ [NOT FOR WEB] Quite a few trainers have small devices. The list header (title + search) is quite large, it should collapse when scrolling.

✔️ Trainers would like to see more than one picture for Pokémon if available on the detail screen (think carousel like UI).

✔️ Trainers would like to see the evolutions of a Pokémon if available, from the detail screen.

✔️ Trainers are always on the road in search of new Pokémon so they don’t always have the best connection. The customer would like to have loading and error indicators when data can not be found or it takes long too long.

✔️ Some trainers have large devices. Your implementation should scale gracefully to larger screens. Even better would be to have a “split view”, with the list of Pokémon on the left, and the details of one on the right..

✔️ Some trainers are forgetful, and don’t remember the name or number of a pokemon. Searching by type (such as “fire”) should also be possible.

✔️ [NOT FOR WEB] Trainers would like to share a Pokémon with other people. When sharing provide as content the name of a Pokémon, their picture (sprite) and URL

✔️ [NOT FOR WEB] Trainers would like to link to a Pokémon in the app from somewhere else. This is called a deeplink, the format should be pokedex://details/eevee or pokedex://details/133

✔️ Trainers are used to seeing the stats of a Pokémon in the typical hexagon graph..<br />

## Platform specifics

### Required

✔️ Kotlin

✔️ XML or Jetpack Compose --> Jetpack compose

✔️ Use native UI components as needed

✔️ Navigation: replace fragment and start activity or Navigation Components --> Used a SingleActivity application (which is what jetpack compose is made for) + made use of NavHostController in MainActivity.kt instead of an XML file with the navigation definitions, this has been done to keep everything in kotlin files

✔️ Gradle

✔️ Free choice in architecture (MVC, MVVM, …), but must have a clear separation between layers such as network, data/model, etc… --> MVVM

### Nice to have

✔️ Coroutines and Flows --> Coroutines was used with mutableStateOf for state management

✔️ Data storage using Room or Realm --> Room

❌ Unit Tests or other tests

✔️ Networking using Retrofit

❌ Styles and Themes

✔️ Databinding --> Using ViewModels + by remember in the Composables to achieve this

✔️ Image loading using Glide, Picasso or Coil --> Coil (AsyncImage) & Picasso to easily share the image (downloads image locally and sends it without requiring imageviews)

# Libraries/packages

// NavController (default navController)<br /> implementation "androidx.navigation:navigation-compose:2.4.2"<br />

// AnimatedNavigation (Animation on navigation between screens)<br /> implementation "com.google.accompanist:accompanist-navigation-animation:0.23.1"<br />

// Retrofit (API calls)<br /> implementation 'com.squareup.retrofit2:retrofit:2.7.2'<br /> implementation 'com.squareup.retrofit2:converter-gson:2.7.2'<br /> implementation 'com.squareup.okhttp3:okhttp:4.9.3'<br />

// AsyncImage Coil (load images from url)<br /> implementation "io.coil-kt:coil-compose:2.0.0-rc01"<br />

// Collapsing Toolbar (collapsing toolbar)<br /> implementation "me.onebone:toolbar-compose:2.3.2"<br />

// Carousel (Image carousel)<br /> implementation "com.google.accompanist:accompanist-pager:$accompanist_version"<br />

// Rooms (SQLite database)<br /> implementation "androidx.room:room-runtime:$room_version"<br />
kapt "androidx.room:room-compiler:$room_version"<br />

// optional - Kotlin Extensions and Coroutines support for Room<br /> implementation "androidx.room:room-ktx:$room_version"<br />

// Koin (for DI)<br /> implementation "io.insert-koin:koin-androidx-compose:$koin_version"<br />

// Swipe to refresh (to refresh detail screen)<br /> implementation "com.google.accompanist:accompanist-swiperefresh:$accompanist_version"<br />

// Charting library for Radar chart (stats)<br /> implementation 'com.github.PhilJay:MPAndroidChart:v3.1.0'<br />

// RxJava (for communication between viewmodels)<br /> implementation "io.reactivex.rxjava3:rxjava:3.1.4"<br />

# Deeplink test<br />

MewTwo: **.\adb shell am start -W -a android.intent.action.VIEW -d "pokedex://details/150" com.tomtruyen.pokedex** <br /> Bulbasaur: **.\adb shell am start -W -a android.intent.action.VIEW -d "pokedex://details/bulbasaur" com.tomtruyen.pokedex**<br />
