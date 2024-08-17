package ru.academits.gerasimenko.temperature.model;

public class TemperatureConverter implements Converter {
    @Override
    public double convertCelsiusToKelvin(double celsiusTemperature) {
        return celsiusTemperature + ICE_MELTING_KELVIN_TEMPERATURE;
    }

    @Override
    public double convertCelsiusToFahrenheit(double celsiusTemperature) {
        return celsiusTemperature * FAHRENHEIT_TO_CELSIUS_INVERSE_INCREMENTS_RATIO + ICE_MELTING_FAHRENHEIT_TEMPERATURE;
    }

    @Override
    public double convertKelvinToCelsius(double kelvinTemperature) {
        return kelvinTemperature - ICE_MELTING_KELVIN_TEMPERATURE;
    }

    @Override
    public double convertKelvinToFahrenheit(double kelvinTemperature) {
        return kelvinTemperature * FAHRENHEIT_TO_CELSIUS_INVERSE_INCREMENTS_RATIO + FAHRENHEIT_ABSOLUTE_ZERO;
    }

    @Override
    public double convertFahrenheitToCelsius(double fahrenheitTemperature) {
        return (fahrenheitTemperature - ICE_MELTING_FAHRENHEIT_TEMPERATURE) / FAHRENHEIT_TO_CELSIUS_INVERSE_INCREMENTS_RATIO;
    }

    @Override
    public double convertFahrenheitToKelvin(double fahrenheitTemperature) {
        return (fahrenheitTemperature - FAHRENHEIT_ABSOLUTE_ZERO) / FAHRENHEIT_TO_CELSIUS_INVERSE_INCREMENTS_RATIO;
    }
}