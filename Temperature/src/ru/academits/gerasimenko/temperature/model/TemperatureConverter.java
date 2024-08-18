package ru.academits.gerasimenko.temperature.model;

import ru.academits.gerasimenko.temperature.constants.TemperatureScalesConstants;

public class TemperatureConverter implements Converter {
    @Override
    public double convertCelsiusToKelvin(double celsiusTemperature) {
        return celsiusTemperature + TemperatureScalesConstants.ICE_MELTING_KELVIN_TEMPERATURE;
    }

    @Override
    public double convertCelsiusToFahrenheit(double celsiusTemperature) {
        return celsiusTemperature * TemperatureScalesConstants.FAHRENHEIT_TO_CELSIUS_INVERSE_INCREMENTS_RATIO + TemperatureScalesConstants.ICE_MELTING_FAHRENHEIT_TEMPERATURE;
    }

    @Override
    public double convertKelvinToCelsius(double kelvinTemperature) {
        return kelvinTemperature - TemperatureScalesConstants.ICE_MELTING_KELVIN_TEMPERATURE;
    }

    @Override
    public double convertKelvinToFahrenheit(double kelvinTemperature) {
        return kelvinTemperature * TemperatureScalesConstants.FAHRENHEIT_TO_CELSIUS_INVERSE_INCREMENTS_RATIO + TemperatureScalesConstants.FAHRENHEIT_ABSOLUTE_ZERO;
    }

    @Override
    public double convertFahrenheitToCelsius(double fahrenheitTemperature) {
        return (fahrenheitTemperature - TemperatureScalesConstants.ICE_MELTING_FAHRENHEIT_TEMPERATURE) / TemperatureScalesConstants.FAHRENHEIT_TO_CELSIUS_INVERSE_INCREMENTS_RATIO;
    }

    @Override
    public double convertFahrenheitToKelvin(double fahrenheitTemperature) {
        return (fahrenheitTemperature - TemperatureScalesConstants.FAHRENHEIT_ABSOLUTE_ZERO) / TemperatureScalesConstants.FAHRENHEIT_TO_CELSIUS_INVERSE_INCREMENTS_RATIO;
    }
}