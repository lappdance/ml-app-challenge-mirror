# ml-app-challenge-mirror

A mirror of Manulife's app challenge repo: https://github.com/seyDoggy/ml-app-challenge

[Use cases](instructions.md)

[![CircleCI](https://circleci.com/gh/lappdance/ml-app-challenge-mirror.svg?style=svg)](https://circleci.com/gh/lappdance/ml-app-challenge-mirror)

## Instructions for running

I've implemented this app using Gradle as the build system. The standard Android Gradle targets are available: `check`, `assemble`, etc. It is also importable into Android Studio via `File` > `New` > `Import project...`. I personally don't use Eclipse for Android development, but Eclipse does have a Gradle plugin, and so should also be importable there.

The `app` target is the application. There are (not enough) unit tests, which runnable in Android Studio by right-clicking the project or `test/java` folder and selecting "Run Tests". I have not provided any instrumentation tests.

I implemented a [CircleCI job](https://circleci.com/gh/lappdance/ml-app-challenge-mirror) executes the unit tests on each commit via `./gradlew testRelease`. The (admittedly trivial) build script is checked in at `.circleci/config.yml`.

## Libraries used
- ###### appcompat-v7
    The Android support library, which provides standard, backwards compatible behaviour to older versions of Android. I actually should have used the new recently published "androidx" library, which will replace this "appcompat" library. I use this library to provide a Material-style action bar in the app, even though the target API is 17.
- ###### recyclerview-v7
    RecyclerView is similar to a ListView, but is better at dealing with large data sets, and provides more animation options. The sample data set here is too small for the memory efficiency to come into play, but a real app, dealing with months and years of transaction history, probably would benefit.
- ###### cardview-v7
    I use the CardView library to render each Account and Transaction in the RecyclerView. CardViews render as rounded rects with drop shadows, to take advantage of Material's z-order-as-importance paradigm. The borders on each card also provide a pleasant visual breakup for the list of elements, so that items don't visually bleed into each other.
- ###### android.arch.lifecycle:extensions
    This library provides the LiveData and ViewModels objects, necessary for the MVVM pattern. This library, too, has been deprecated and replaced by the "androidx" library. Unfortunately, there is a coupling between the lifecycle extensions and the support library: if I update one, I must update the other at the same time.
- ###### jetbrains:annotations
    Necessary only to provide an IDE hint about null-safety. `Objects::requireNonNull(Object o)` asserts that `o` is non-null by throwing otherwise; the IDE reads this contract and so disables any "`o` may be null" warnings following that method invocation. Because `Objects` was only added in API 19, and I'm targetting 17, I had to implement my own method, and provide my own IDE hint.
- ###### junit
    JUnit is a standard unit testing framework for Java. I have provided an embarrassingly small number of unit tests.
- ###### mockito
    I prefer mockito over jmock: I find jmock too opinionated about verifying method invocations. It's easier to mock methods without also verifying them in Mockito; Mockito also has better support for inspecting or capturing mocked method arguments.
- ###### robolectric
    Robolectric is a testing framework for Android that has effectively ported the Android runtime, such that it will run in a standard JVM. This provides a significant speed boost over traditional instrumentation tests, which require an emulator instance to run. Robolectric also provides hooks into its reimplementation of the Android system, to allow tests to check state that is otherwise obscured: eg, if a test fires an Intent, Robolectric captures that Intent and offers it for inspection, whereas an instrumented test would execute and follow the intent. I have more practice with Robolectric than with instrumentation tests; I'd hoped to provide more than the paltry number of tests here to demonstrate that, but I'd rather submit this project which is "good enough" and speak to testing in a later interview.

## Architecture

I tried to follow Google's recommended MVVM pattern, though I'm not as familiar with it as I am with MVP and MVC. Each Activity has its own ViewModel; the ViewModel offers data from the Repository via LiveData instances, which can be observed by the Activity, Fragment, or View. Data is thus "pushed" from the repo to the view as it arrives (where MVP and MVC would "pull" the data from the repo asynchronously). By using the Observer pattern, the View (or any other layer) is decoupled from the ViewModel, allowing that observing layer to be reclaimed or replaced as the app is used (contrast with MVP where the presenter & View are tightly coupled, and each keeps a reference to the other).

I've given the ViewModels the business logic actions that would belong to the "Presenter" in an MVP pattern. The ViewModel, then, is able to update its own state and its own LiveData instances, and the View remains an Observer.

I have not used Dagger or any other dependency injection framework. Though I've used DI in Spring apps, I have no experience with Dagger; a coding challenge to demonstrate what I know is not the best place to experiement, I think.

I implemented the "login" page as an Activity, while the list of accounts & list of transaction in an account are Fragments hosted in another Activity: I'm able to share the same ViewModel instance between the tow fragments, and so also share the account transaction history trivially (if I used a DI tool then I would be more easily able to share the account repo itself, instead of sharing the ViewModel). The account views also have a common "Quit" button in the titlebar; I was able to define that once, in the Activity, and keep that behviour isolated from the Fragments.

Using Fragments instead of Activitys, however, complicated the Up button in the Action bar: you'll notice that there isn't one, and that you must use the hardware Back button to return to the list of accounts. I wasn't able to get the Up button to appear on the transaction list fragment, though I have been able to do so in the apps I've written professionally. I'd rather call this behaviour a known defect and submit this project now, than spend more time implementing a feature that's not strictly necessary.

The app's main activity is the login page. The login page checks the login state, and, if the user already has a session, terminates itself and launches the account list Activity. I chose to do it this way because the Quit implementation becomes simpler: I can fire an Intent with CLEAR_TASK set, to blow away the activity stack and return to the login page from any point. The alernative implementation would be to open the accounts page first, and possibly show the login page via `startActivityForResult`. If the user presses Back before logging in, the Result wold indicate an error and the account activity would terminate itself. However, the Quit action becomes more complicated in this case, because the root activity would have to reset its UI state after the user logged in again: it would not do to Quit from the transacation page, and return to it, instead of the account list page, after logging in again. Imagine if a different user had logged in, the old user's data would be visible!
