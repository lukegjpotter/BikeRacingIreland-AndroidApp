# Bike Racing Ireland - Android App

An Android app that will display the Bicycle Races in Ireland that are
on the CyclingIreland Calendar.

## Initial Setup

1. Follow the "Initial Database Setup" and "Build, Run and Test" steps
in the [CyclingIrelandEventsHTMLScraper project's README file](https://github.com/lukegjpotter/cycling-ireland-events-html-scraper/blob/master/README.md).
1. Follow the "Initial Database Setup" and "Build, Run and Test" steps
in the [CyclingIrelandEventsRestService project's README file](https://github.com/lukegjpotter/cycling-ireland-events-rest-service/blob/master/README.md).

## Build, Run and Test

1. Use Android Studio to build and run the app on the Emulator.

## Version Alpha Features - Complete

* [x] Read `BikeRace` objects from `CyclingIrelandEventsRestService`.
* [x] Store `BikeRace` objects in a local database.
* [x] When reading the REST Service, only read races to the end of the
      month.
* [x] Present `BikeRace` objects in a ListView for the Master Fragment.
* [x] Present `BikeRace` objects in the Details Fragment.
* [x] Add a `ProfileFilter` to the `ActionBar` to filter on the Category
      of the User.

## Version 1.0 Features - In Development

* [x] Apply an Icon to the App.
* [ ] When the end of the ListView is reached, download the next month's
      races.
* [ ] User is able to define their `ProfileFilter`.
* [ ] Use the Strava API and Google Maps Lite Mode to display the
      routes, in `CardView` style.
* [ ] Apply Styling to the User Interface

## Version 2.0 Features

* [ ] Add a Search to the Toolbar to search the local and remote
      repositories for bike race names.
* [ ] Enable the user to set a Calendar Event for the Race. Store which
      races have Calendar 
* [ ] Add parts of the card for individual Stages of the `BikeRace`.