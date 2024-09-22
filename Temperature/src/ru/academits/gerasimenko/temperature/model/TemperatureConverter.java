package ru.academits.gerasimenko.temperature.model;

import ru.academits.gerasimenko.temperature.scales.CelsiusScale;
import ru.academits.gerasimenko.temperature.scales.FahrenheitScale;
import ru.academits.gerasimenko.temperature.scales.KelvinScale;
import ru.academits.gerasimenko.temperature.scales.Scale;

import java.util.List;

public class TemperatureConverter implements Converter {
    private final List<Scale> scalesList = List.of(
            new CelsiusScale(),
            new KelvinScale(),
            new FahrenheitScale()
    );

    @Override
    public double convert(double temperature, Scale inputScale, Scale outputScale) {
        return outputScale.convertFromCelsius(inputScale.convertToCelsius(temperature));
    }

    @Override
    public List<Scale> getAvailableScalesList() {
        return scalesList;
    }
}