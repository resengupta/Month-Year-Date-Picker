package com.studio.modifieddatepicker.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A suite of utilities for displaying {@link Calendar} and {@link Date}
 * objects.
 */
public final class DateDisplayUtils {

    private static final String MONTH_YEAR_DISPLAY_PATTERN = "MM/yyyy";

    private DateDisplayUtils() {
        // hide constructor
    }

    /**
     * Formats the month and year. If provided a separator then will be displayed as MM/yyyy or else
     * MMyyyy
     *
     * @param year        The year that was set.
     * @param monthOfYear The month that was set (0-11) for compatibility with {@link
     *                    Calendar}.
     * @return the formatted string
     */
    public static String formatMonthYear(int year, int monthOfYear) {
        Locale locale = Locale.getDefault();
        Calendar calendar = Calendar.getInstance(locale);
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        SimpleDateFormat format = new SimpleDateFormat(
                MONTH_YEAR_DISPLAY_PATTERN, Locale.getDefault());
        return format.format(calendar.getTime());
    }
}
