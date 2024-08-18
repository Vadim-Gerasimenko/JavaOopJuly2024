package ru.academits.gerasimenko.temperature.constants;

import java.util.HashSet;
import java.util.List;

public final class TemperatureScalesConstants {
    public static final String CELSIUS_SCALE = "Celsius";
    public static final String KELVIN_SCALE = "Kelvin";
    public static final String FAHRENHEIT_SCALE = "Fahrenheit";

    public static final int CELSIUS_SCALE_NUMBER = 1;
    public static final int KELVIN_SCALE_NUMBER = 2;
    public static final int FAHRENHEIT_SCALE_NUMBER = 3;
    public static final HashSet<Integer> SCALES_NUMBERS_SET = new HashSet<>(
            List.of(CELSIUS_SCALE_NUMBER, KELVIN_SCALE_NUMBER, FAHRENHEIT_SCALE_NUMBER)
    );

    public static final double FAHRENHEIT_ABSOLUTE_ZERO = -459.67;
    public static final double ICE_MELTING_KELVIN_TEMPERATURE = 273.15;
    public static final double ICE_MELTING_FAHRENHEIT_TEMPERATURE = 32;
    public static final double FAHRENHEIT_TO_CELSIUS_INVERSE_INCREMENTS_RATIO = 1.8;
}