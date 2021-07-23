# Movie and TV Show Application

Submission for "Belajar Android Jetpack Pro (BAJP)" on Dicoding Academy

## Demo

<img src="https://cdn.discordapp.com/attachments/791866991995650081/868120106717282314/video_2021-07-23_20-03-14.gif" height=450> 

## Features

- Get List Popular Movies
- Get List Popular TV Shows

## Tech Stack

- Design Pattern : MVVM
- Local Database : Room
- API Library : Retrofit
- API Source : [TMDB API](https://www.themoviedb.org/documentation/api)
- UI Library : Android Material Design
- Instrumentation Test Library : Mockito
- Unit Test Library : Espresso

## Build

1. Create account on [TMDB](https://www.themoviedb.org/signup)
2. Create your api key
3. Create new file named `gradle.properties` on the root project directory, then paste this code below.

```properties
org.gradle.jvmargs=-Xmx2048m -Dfile.encoding=UTF-8
android.useAndroidX=true
kotlin.code.style=official
tmdbApiKey="YOUR_API_KEY" 
```
