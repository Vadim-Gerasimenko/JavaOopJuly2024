package ru.academits.gerasimenko.temperature.scales;

public class FahrenheitScale implements Scale {
    private static final String SCALE_NAME = "Fahrenheit";
    private static final double ICE_MELTING_FAHRENHEIT_TEMPERATURE = 32;
    private static final double FAHRENHEIT_TO_CELSIUS_INVERSE_INCREMENTS_RATIO = 1.8;

    @Override
    public String getScaleName() {
        return SCALE_NAME;
    }

    @Override
    public double convertToCelsius(double temperature) {
        return (temperature - ICE_MELTING_FAHRENHEIT_TEMPERATURE) / FAHRENHEIT_TO_CELSIUS_INVERSE_INCREMENTS_RATIO;
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return (temperature * FAHRENHEIT_TO_CELSIUS_INVERSE_INCREMENTS_RATIO) + ICE_MELTING_FAHRENHEIT_TEMPERATURE;
    }
}