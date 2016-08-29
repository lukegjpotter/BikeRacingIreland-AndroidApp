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

## Version Alpha Features - In Development

* [x] Read `BikeRace` objects from `CyclingIrelandEventsRestService`.
* [x] Store `BikeRace` objects in a local database.
* [x] When reading the REST Service, only read races to the end of the
      month.
* [ ] Present `BikeRace` objects in a ListView for the Master Fragment.
* [ ] Present `BikeRace` objects in the Details Fragment.
* [ ] When the end of the ListView is reached, download the next month's
      races.

## Version 1.0 Features

* [ ] Add a Profile Filter to the ActionBar to filter on the Category of
      the racer. 
* [ ] Use the Strava API and Google Maps Lite Mode to display the
      routes, in CardView style.

## Version 2.0 Features

* [ ] Add parts of the card for individual Stages of the `BikeRace`.