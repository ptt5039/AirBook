package edu.snhu.airbook.utils;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date time converter class
 *
 * @author phongtran
 */
public class DateTimeConverter {

    /**
     * Convert time to formatted String
     * @param time SQL Time
     * @return formatted String
     */
    public static String fromTimeToString(Time time) {
        String rawTime = time.toString();
        int hour = time.toLocalTime().getHour();
        int minutes = time.toLocalTime().getMinute();
        String newTime;
        String newMinutes = String.valueOf(minutes).length() == 1 ? "0" + minutes : String.valueOf(minutes);
        String period;
        if (hour >= 12) {
            period = " PM";
            if (hour > 12) {
                newTime = (hour - 12) + ":" + newMinutes + period;
            } else {
                newTime = hour + ":" + newMinutes + period;
            }
        } else {
            period = " AM";
            if(hour == 0) {
                newTime = "12:" + newMinutes + period;
            } else {
                newTime = hour + ":" + newMinutes + period;
            }
        }
        return newTime;
    }

    /**
     * convert Date to formatted String
     * @param date Date object
     * @return formatted string
     */
    public static String convertDateToFormattedString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("E, MMM dd, yyyy");
        return formatter.format(date);
    }
}
