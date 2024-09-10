package ru.academits.gerasimenko.temperature.scales;

public class CelsiusScale implements Scale {
    private static final String SCALE_NAME = "Celsius";

    @Override
    public String getScaleName() {
        return SCALE_NAME;
    }

    @Override
    public double convertToCelsius(double temperature) {
        return temperature;
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return temperature;
    }
}