# Smart News
> A Smart News application developed in Kotlin

## Table of contents
* [General info](#general-info)
* [Components](#components)
* [Screenshots](#screenshots)
* [Status](#status)

## General info

SmartNews is a news app that allows users to read news easily on their mobile device. Currently built for Android in Kotlin, it has multiple features such as news recommendation, news search, and bookmarking user’s favorite news for them to read later.

## Components

The project is built in Kotlin, and is structured in an MVVM format. The ViewModel holds the data so that when the activity fragment is recreated, then the data is not recreated as well, conforming to the MVVM architecture. We use Retrofit, which turns our HTTP API calls from NewsAPI into requests that will be shown in the Activity Fragment class. We also implement LiveData binding, which allows our application to be lifecycle aware and notify UI of any changes in data. For the activity fragment, we have a main page where we implement RecyclerView for scrollable content, and then on click to the news app we can see the detailed view where we can actually read the article in full. On the NavBar we have the title of the article and an up button, which can take us back to the MainActivity fragment.

Here are the main components:
- MVVM Model
- Retrofit
- RecyclerView
- LiveData
- NewsAPI

## Screenshots

## Status
Project is: _Completed_
