package com.lukegjpotter.bikeracingireland.utils;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * The {@code MonthManager} class is used to handle the months in the app.
 */
public class MonthManager {

    private static int sCurrentMonthNumber = Calendar.getInstance().get(Calendar.MONTH) + 1;
    private static int sListViewMonthNumber = Calendar.getInstance().get(Calendar.MONTH) + 2;
    private static Set<Integer> sMonthsInListView = new HashSet<>();

    /**
     * Returns the Current Month Number.
     *
     * @return The Current Month Number.
     */
    public static int currentMonthNumber() {
        // Update the CurrentMonth in case the user is using the app at midnight on the turn of a month.
        int monthNumberFromCalendar = Calendar.getInstance().get(Calendar.MONTH) + 1;

        if (sCurrentMonthNumber != monthNumberFromCalendar) {
            sCurrentMonthNumber = monthNumberFromCalendar;
            reachedBottomOfListView();
        }

        sMonthsInListView.add(sCurrentMonthNumber);

        return sCurrentMonthNumber;
    }

    /**
     * Returns the Next Month Number.
     * @return the Next Month Number.
     */
    public static int nextMonthNumberForListView() {
        // Update the NextMonth in case the user is using the app at midnight on the turn of a month.
        int nextMonthNumberFromCalendar = Calendar.getInstance().get(Calendar.MONTH) + 2;

        if (sListViewMonthNumber < nextMonthNumberFromCalendar) {
            reachedBottomOfListView();
        }

        sMonthsInListView.add(sListViewMonthNumber);

        return sListViewMonthNumber;
    }

    public static void reachedBottomOfListView() {
        sListViewMonthNumber += 1;
    }

    /**
     * Keeps the months in use in the {@code ListView}. This is useful for the DataBase Queries.
     *
     * @return The months in use in the {@code ListView}.
     */
    public static Set<Integer> getMonthsInListView() {
        return sMonthsInListView;
    }
}
