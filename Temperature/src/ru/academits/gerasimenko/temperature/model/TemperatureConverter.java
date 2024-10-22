package ru.academits.gerasimenko.temperature.model;

import ru.academits.gerasimenko.temperature.scales.Scale;

import java.util.Collections;
import java.util.List;

public class TemperatureConverter implements Converter {
    private final List<Scale> scalesList;

    public TemperatureConverter(List<Scale> scalesList) {
        if (scalesList == null) {
            throw new NullPointerException("The list of available scales must not refer to null");
        }

        this.scalesList = Collections.unmodifiableList(scalesList);
    }

    @Override
    public double convert(double temperature, Scale inputScale, Scale outputScale) {
        if (inputScale == null) {
            throw new NullPointerException("Input scale must not refer to null");
        }

        if (outputScale == null) {
            throw new NullPointerException("Output scale must not refer to null");
        }

        return outputScale.convertFromCelsius(inputScale.convertToCelsius(temperature));
    }

    @Override
    public List<Scale> getAvailableScalesList() {
        return scalesList;
    }
}