package ru.academits.gerasimenko.temperature.scales;

public interface Scale {
    String getScaleName();

    double convertToCelsius(double temperature);

    double convertFromCelsius(double temperature);
}