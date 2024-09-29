package ru.academits.gerasimenko.temperature.model;

import ru.academits.gerasimenko.temperature.scales.Scale;

import java.util.Collections;
import java.util.List;

public class TemperatureConverter implements Converter {
    private final List<Scale> scalesList;

    public TemperatureConverter(List<Scale> scalesList) {
        this.scalesList = scalesList;
    }

    @Override
    public double convert(double temperature, Scale inputScale, Scale outputScale) {
        return outputScale.convertFromCelsius(inputScale.convertToCelsius(temperature));
    }

    @Override
    public List<Scale> getAvailableScalesList() {
        return Collections.unmodifiableList(scalesList);
    }
}