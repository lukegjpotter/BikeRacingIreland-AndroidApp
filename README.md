# Bike Racing Ireland - Android App

An Android app that will display the Bicycle Races in Ireland that are
on the CyclingIreland Calendar.

[![Dependency Status](https://www.versioneye.com/user/projects/59f44ead0fb24f1ec28638e4/badge.svg)](https://www.versioneye.com/user/projects/59f44ead0fb24f1ec28638e4)

## Initial Setup

1. Follow the "Initial Database Setup" and "Build, Run and Test" steps
in the [CyclingIrelandEventsHTMLScraper project's README file](https://github.com/lukegjpotter/cycling-ireland-events-html-scraper/blob/master/README.md).
1. Follow the "Initial Database Setup" and "Build, Run and Test" steps
in the [CyclingIrelandEventsRestService project's README file](https://github.com/lukegjpotter/cycling-ireland-events-rest-service/blob/master/README.md).

## Build, Run and Test

1. Use Android Studio to build and run the app on the Emulator.

## Version Alpha Features - Complete

* [x] Read `BikeRaceWithStageDetails` objects from `CyclingIrelandEventsRestService`.
* [x] Store `BikeRaceWithStageDetails` objects in a local database.
* [x] When reading the REST Service, only read races to the end of the
      month.
* [x] Present `BikeRaceWithStageDetails` objects in a ListView for the Master Fragment.
* [x] Present `BikeRaceWithStageDetails` objects in the Details Fragment.
* [x] Add a `ProfileFilter` to the `ActionBar` to filter on the Category
      of the User.

## Version 1.0 Features - In Development

* [x] Apply an Icon to the App.
* [x] When the end of the ListView is reached, download the next month's
      races.
* [ ] Retest with Android Oreo Components.
* [ ] User is able to define their `ProfileFilter`.
* [ ] Apply Styling to the User Interface
* [ ] Use the Strava API and Google Maps Lite Mode to display the
      routes, in `CardView` style.

## Version 2.0 Features

* [ ] Add a Search to the Toolbar to search the local and remote
      repositories for bike race names.
* [ ] Enable the user to set a Calendar Event for the Race. Store which
      races have Calendar 
* [ ] Add parts of the card for individual Stages of the `BikeRace`.

## Refactoring Tasks

* [x] Use `Room` to reduce Database code. [Room Sample from Google](https://github.com/googlesamples/android-architecture-components).
* [x] Use `ViewModel` and `LiveData` to maintain data when rotated.
* [ ] Remove the code for the components that Room and Lifecycles replaces.
