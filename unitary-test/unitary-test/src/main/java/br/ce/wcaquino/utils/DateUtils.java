package br.ce.wcaquino.utils;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    /**
     * Returns the given date with the specified number of days added.
     * The date can be moved into the future (days > 0) or the past (days < 0).
     *
     * @param date
     * @param days
     * @return
     */
    public static Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    /**
     * Returns the current date with the specified day difference.
     * The resulting date can be in the future (positive value)
     * or in the past (negative value).
     *
     * @param days Number of days to add or subtract
     * @return Updated date
     */
    public static Date getDateWithDayDifference(int days) {
        return addDays(new Date(), days);
    }

    /**
     * Returns a Date instance using the provided day, month, and year values.
     *
     * @param day
     * @param month
     * @param year
     * @return
     */
    public static Date getDate(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(DAY_OF_MONTH, day);
        calendar.set(MONTH, month - 1);
        calendar.set(YEAR, year);
        return calendar.getTime();
    }

    /**
     * Checks whether two dates are equal.
     * This comparison considers only day, month, and year.
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDate(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        return (calendar1.get(DAY_OF_MONTH) == calendar2.get(DAY_OF_MONTH))
                && (calendar1.get(MONTH) == calendar2.get(MONTH))
                && (calendar1.get(YEAR) == calendar2.get(YEAR));
    }

    /**
     * Checks whether a given date falls on the specified day of the week.
     *
     * @param date Date to evaluate
     * @param dayOfWeek Returns true if the date matches the specified day of the week,
     *                  false otherwise
     * @return
     */
    public static boolean isDayOfWeek(Date date, int dayOfWeek) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(DAY_OF_WEEK) == dayOfWeek;
    }
}