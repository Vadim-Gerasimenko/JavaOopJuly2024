package ru.academits.gerasimenko.temperature.model;

import ru.academits.gerasimenko.temperature.scales.Scale;

public interface Converter {
    double convert(double temperature, Scale inputScale, Scale outputScale);
}