# General information of the project:

- **Language**: Kotlin
- **IDE**: Android Studio
- **Design Pattern**: MVVM (Model - View - ViewModel) with LiveData
- **DI**: Dagger Hilt
- **Concurrency**:  RxJava
- **Unit Test Framework**: Spec
- **UI Test Framework**: Espresso

The project follows a feature structure, there is a feature folder where all additional features would be placed, in this example, the feature provided is `Breeds`

Each feature manages the three layers of clean architecture (Data - Domain - UI).

As DI framework I used Dagger Hilt, its configuration is done in the subfolder DI

The management of the response is done with `RxJava`, using `Moshi` library to adapt the response.

I decided to create two models for the Network Response `BreedResponse` and `ImageResponse` and the one used in the domain is `Breed`, the transformation is done in a  `Mapper`
  
The project use the `UseCases` pattern in order to help identify the different use cases the app could have, in this case `GetAllBreeds` and `GetBreedImages`

In order to handle the navigation between the two fragments I decided to use the Android Navigation Component and adding an animation between the screens.

For adapting the images I used `ViewPager2` and `Glide` library, my aproach for displaying the image is the one implemented by Istagram (scrolling the diferent imagaes left or right).

For the view holder while the image are loading I introduced an animation where the image is rotating until the picture is loaded

For `UnitTest` I use the spec framework, as I think it improves the readability of the different use cases and as well the scalability for more complex input data structures.
Please note for runing it is needed the Spec framework plugin to be installed.

The  `UnitTest` test the 3 different layers of the feature

I provide a `UITest` using espresso to test the displayed of the list in the fragment. This is done mocking the response of the view states. Please use an emulator with API 28 to run it.

Please note, I didn't add end to end test, that should be added to test the navigation between the different screens.

The gradle file has 2 build types, one for debug and other for production, both of them refer to the same API URL.

# Scrum:
In order to manage this project I followed the principles of _Scrum Methodology_ identifying the following tasks and estimating them as follows:

- [x] **TaskA**: Setup the project -> Story Points: **2**
- [x] **TaskB**: Establish the MVVM architecture with Data-Domain-UI -> Story Points: **5**
- [x]  **TaskC**: Implement Dagger-> Story Points: **5**
- [x] **TaskD**: Implement retrieving the data for all breed case-> Story Points: **3**
- [x] **TaskE**: UI implementation of the list -> Story Points: **3**
- [x]  **TaskF**: Implement retrieving the data for image breed case-> Story Points: **3**
- [x] **TaskG**: Implementation of detail screen -> Story Points: **2**
- [x] **TaskH**: UITest of the  list view -> Story Points: **3**

Other tasks identified not implemented:
- [ ] **TaskI**: Improve designs
- [ ] **TaskJ**: Define more custom styles for the app
- [ ] **TaskK**: Implement end to end tests
- [ ] **TaskL**: Implement UI test for detail screen
- [ ] **TaskM**: Implement persistence for not network
- [ ] **TaskN**: Implement pull to refresh  on the list
- [ ] **TaskO**: Migrate gradle to KTS
