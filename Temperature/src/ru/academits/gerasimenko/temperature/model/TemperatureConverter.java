package ru.academits.gerasimenko.temperature.model;

import ru.academits.gerasimenko.temperature.scales.Scale;

public class TemperatureConverter implements Converter {
    @Override
    public double convert(double temperature, Scale inputScale, Scale outputScale) {
        return outputScale.convertFromCelsius(inputScale.convertToCelsius(temperature));
    }
}