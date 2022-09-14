package com.degree.petFeeder.utils;

public class MotorUtils {

    public static float calculateTurnsFromSeconds(Float seconds) {
        // 1 turn ~ 8s
        return seconds * ((float)1/8);
    }
}
