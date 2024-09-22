package ru.academits.gerasimenko.temperature.model;

import ru.academits.gerasimenko.temperature.scales.Scale;

import java.util.List;

public interface Converter {
    double convert(double temperature, Scale inputScale, Scale outputScale);

    List<Scale> getAvailableScalesList();
}