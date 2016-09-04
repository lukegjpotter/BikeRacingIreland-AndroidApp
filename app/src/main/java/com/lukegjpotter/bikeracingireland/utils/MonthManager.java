package com.lukegjpotter.bikeracingireland.utils;

import java.util.Calendar;

/**
 * The {@code MonthManager} class is used to handle the months in the app.
 */
public class MonthManager {

    private static int sCurrentMonthNumber = Calendar.getInstance().get(Calendar.MONTH) + 1;
    private static int sListViewMonthNumber = Calendar.getInstance().get(Calendar.MONTH) + 2;

    public static int currentMonthNumber() {
        // Update the CurrentMonth in case the user is using the app at midnight on the turn of a month.
        int monthNumberFromCalendar = Calendar.getInstance().get(Calendar.MONTH) + 1;

        if (sCurrentMonthNumber != monthNumberFromCalendar) {
            sCurrentMonthNumber = monthNumberFromCalendar;
            reachedBottomOfListView();
        }

        return sCurrentMonthNumber;
    }

    public static int nextMonthNumberForListView() {
        // Update the NextMonth in case the user is using the app at midnight on the turn of a month.
        int nextMonthNumberFromCalendar = Calendar.getInstance().get(Calendar.MONTH) + 2;

        if (sListViewMonthNumber < nextMonthNumberFromCalendar) {
            reachedBottomOfListView();
        }

        return sListViewMonthNumber;
    }

    public static void reachedBottomOfListView() {
        sListViewMonthNumber += 1;
    }
}
