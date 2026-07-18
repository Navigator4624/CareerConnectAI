package com.careerconnect.Util;


import java.util.UUID;

public final class IdGenerator {

    private IdGenerator() {
        // Prevent instantiation
    }

    public static String generateStudentId() {
        return "STU-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public static String generateCompanyId() {
        return "COM-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public static String generateDriveId() {
        return "DRV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public static String generateApplicationId() {
        return "APP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
