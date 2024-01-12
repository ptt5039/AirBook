package edu.snhu.airbook.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date time converter class
 *
 * @author phongtran
 */
@Slf4j
public class DateTimeConverter {

    /**
     * Convert time to formatted String
     *
     * @param time SQL Time
     * @return formatted String
     */
    public static String fromTimeStringToString(String time) {
        int hour = Integer.parseInt(time.split(":")[0]);
        int minutes = Integer.parseInt(time.split(":")[1]);
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
            if (hour == 0) {
                newTime = "12:" + newMinutes + period;
            } else {
                newTime = hour + ":" + newMinutes + period;
            }
        }
        return newTime;
    }

    /**
     * convert Date to formatted String
     *
     * @param date Date object
     * @return formatted string
     */
    public static String convertDateToFormattedString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("E, MMM dd, yyyy");
        return formatter.format(date);
    }

    /**
     * Convert DateTime String to Date.
     *
     * @param date yyyy-MM-ddTHH:mm:ss format
     * @return yyyy-MM-dd Date format
     */
    public static Date convertDateTimeStringToDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
            return formatter.parse(date.split("T")[0]);
        } catch (ParseException e) {
            log.error("Wrong date format!");
            return null;
        }
    }

    /**
     * Convert DateTime String to Time String.
     *
     * @param date yyyy-MM-ddTHH:mm:ss format
     * @return HH:mm AM or PM format
     */
    public static String convertDateTimeStringToTimeString(String date) {
        String time = date.split("T")[1];
        //removing seconds from time
        return fromTimeStringToString(time.substring(0, time.length() - 3));
    }

    /**
     * Convert Amadeus Duration time to readable duration time.
     *
     * @param duration PTHHHmmM format
     * @return HHh mmm format
     */
    public static String converterAmadeusDuration(String duration) {
        return duration.replace("PT", "").replace("H", "h ").replace("M", "m");
    }
}
