package ru.academits.gerasimenko.temperature.model;

public interface Converter {
    double FAHRENHEIT_ABSOLUTE_ZERO = -459.67;
    double ICE_MELTING_KELVIN_TEMPERATURE = 273.15;
    double ICE_MELTING_FAHRENHEIT_TEMPERATURE = 32;
    double FAHRENHEIT_TO_CELSIUS_INVERSE_INCREMENTS_RATIO = 1.8;

    double convertCelsiusToKelvin(double celsiusTemperature);

    double convertCelsiusToFahrenheit(double celsiusTemperature);

    double convertKelvinToCelsius(double kelvinTemperature);

    double convertKelvinToFahrenheit(double kelvinTemperature);

    double convertFahrenheitToCelsius(double fahrenheitTemperature);

    double convertFahrenheitToKelvin(double fahrenheitTemperature);
}