# CryptoCoinTracker
## Description
The Crypto Coin Tracker app was written in Kotlin within Android Studio and consists of 4 screens:
- Splash Screen
-	Bitcoin Tracker Screen
-	Alt Coins Screen
-	About Screen

The purpose of this app is to display to the user the current cryptocurrency information to the user to includes but is not limited to: Bitcoin price on page one (Bitcoin Tracker View), information about other cryptocurrency alt-coins coins on a second page (Alt Coins Tracker View) and finally a third page (About View) that lists the name of the developer. These views are displayed in a Bottom Navigation tab. In addition to the Views described here, there is also a splash screen with a dollar sign that appears momentarily upon initial app invocation.

## External libraries used
-	JetBrains Coroutines
-	Google GSON
-	Squareup Retrofit

## Dependencies added to build.gradle:
- implementation 'androidx.core:core-splashscreen:1.0.0-beta01'
- implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
- implementation 'com.google.code.gson:gson:2.8.7'
- implementation 'com.squareup.retrofit2:retrofit:2.9.0'
- implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
 

## Known Bugs
-	Bitcoin value is not rounded to two decimal places to properly represent currency.
- TextView names were not assigned to the fields in RecyclerView layout (Ex. Rank is shown as a number without a title before the value indicating what the field is).
-	ViewModel implementation should be expanded upon.

 
## App Design & Architecture 
The design of the Crypto Coin Tracker is a Bottom Navigation style app that opens with a Splash Screen (only used on initial app invocation) and contains individual pages to display cryptocurrency data to the user. 
The architecture includes three Fragments (HomeFragment is used for BitcoinTracker, DashboardFragment is used for Alt Coins Tracker, and the NotificationsFragment is used to display the developer’s name. Each of these Fragments are displayed within the tabs of the Bottom Navigation. By design, the Fragments are paired with ViewModel classes; however, they are minimally used due to time constraints for delivery.
Within the Fragments, cryptocurrency information is displayed from a third-party data source that utilizes Retrofit to make RESTful API requests for JSON data. The Bitcoin Tracker Screen uses the CoinDesk API that returns a JSON object with data containing both the Bitcoin price and time of which that price was quoted. The Alt Coins Tracker Screen uses Paprika and returns a very large (39,000+) array of JSON objects containing info on all cryptocurrency coins. This data was capped at 20 to prevent excessive data and scrolling issues with the RecyclerView. If it is desired that the entire data set is to be displayed, a more robust UI display (possibly with filtering) needs to be implemented. 

## Developer
The Crypto Coin Tracker app was made by Sherry Easter Dubin on 2/19/2022
