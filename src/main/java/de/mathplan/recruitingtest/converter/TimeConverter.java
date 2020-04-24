package de.mathplan.recruitingtest.converter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author hoener
 * 
 * This class converts the string representing a time from the xml-file to a 
 * LocalTime object.
 * 
 */
public class TimeConverter {

    private static final DateTimeFormatter fmt;

    static {
        fmt = DateTimeFormatter.ofPattern("HH:mm:ss");
    }

    public static String printDate(LocalTime value) {
        return value.format(fmt);
    }

    public static LocalTime parseDate(String value) {
        LocalTime result = LocalTime.parse(value, fmt);
        return result;
    }

    private TimeConverter() {

    }

}
