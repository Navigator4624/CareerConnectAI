package com.careerconnect.Util;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateUtil {

    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private static final DateTimeFormatter DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private DateUtil() {
        // Prevent instantiation
    }

    public static String formatDate(LocalDate date) {

        if (date == null) {
            return "";
        }

        return date.format(DATE_FORMAT);
    }

    public static String formatDateTime(LocalDateTime dateTime) {

        if (dateTime == null) {
            return "";
        }

        return dateTime.format(DATE_TIME_FORMAT);
    }

    public static boolean isDeadlineExpired(LocalDate deadline) {

        return deadline != null && deadline.isBefore(LocalDate.now());
    }

    public static LocalDate today() {
        return LocalDate.now();
    }

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }
}
