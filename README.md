# City Search App

This Android application is designed to efficiently search through a large dataset of cities, providing a seamless and responsive user experience. The app leverages advanced data structures and clean architecture principles to ensure optimal performance and maintainability.

## Key Features

1. **Efficient City Search with Trie Data Structure**
   - The app uses a custom `TrieCity` data structure for prefix-based search, allowing fast and efficient filtering of over 200,000 city entries.
   - This approach drastically reduces the search time compared to traditional linear search algorithms.
   - The Trie data structure is initialized with all city names, ensuring that each search operation retrieves results in optimal time, making the UI highly responsive.

2. **Responsive UI with Jetpack Compose**
   - The user interface is built using Jetpack Compose, Android's modern UI toolkit, ensuring a smooth and intuitive experience.
   - An animated content composable was implemented to handle transitions between different UI states, such as loading and displaying search results.
   - The app supports real-time updates as users type, instantly filtering and displaying cities that match the given prefix.

3. **Google Maps Integration**
   - Users can tap on any city in the list to open its location on Google Maps.
   - The app uses a geo URI to launch Google Maps with the specified latitude and longitude coordinates.
   - A check is included to ensure compatibility with older Android versions, gracefully handling cases where the necessary intent cannot be processed, thus preventing crashes.

4. **Optimized for All Android Versions (5.0+)**
   - The app ensures compatibility across a wide range of Android versions, starting from Android 5.0 (Lollipop).
   - Special attention was given to handle legacy Android versions, including managing potential issues related to memory usage and intent handling for older devices.

5. **Dependency Injection with Hilt**
   - The project utilizes Hilt for dependency injection, ensuring a clean and modular codebase.
   - This setup makes the code more testable, maintainable, and scalable.

6. **Comprehensive Error Handling**
   - The app includes robust error handling, including the display of user-friendly toast messages for errors that may occur during operation.
   - Edge cases, such as the absence of applications to handle specific intents, are managed gracefully.

## How It Works

### Initial Loading
- The app loads the city data from a JSON file containing around 200,000 entries. The initial loading time is optimized but not prioritized over search performance.

### City Search
- As the user types a prefix in the search bar, the Trie-based search algorithm filters the cities in real-time, displaying them in alphabetical order by city name, followed by the country code.

### Display on Map
- Upon selecting a city, the app will open Google Maps to the corresponding location, with checks to ensure compatibility across all supported Android versions.

## Conclusion

This project demonstrates the critical role of efficient data structures in managing large datasets and delivering a seamless user experience. By employing modern Android development practices such as Jetpack Compose, Hilt for dependency injection, and the Trie data structure for optimized search functionality, the application achieves both high performance and maintainability. The thoughtful integration of these technologies ensures that the app remains responsive and scalable, providing a robust solution for handling and displaying extensive city data.

