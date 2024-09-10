package ru.academits.gerasimenko.temperature.scales;

public class KelvinScale implements Scale {
    private static final String SCALE_NAME = "Kelvin";
    private static final double ICE_MELTING_KELVIN_TEMPERATURE = 273.15;

    @Override
    public String getScaleName() {
        return SCALE_NAME;
    }

    @Override
    public double convertToCelsius(double temperature) {
        return temperature - ICE_MELTING_KELVIN_TEMPERATURE;
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return temperature + ICE_MELTING_KELVIN_TEMPERATURE;
    }
}