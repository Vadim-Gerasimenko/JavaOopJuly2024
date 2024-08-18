package ru.academits.gerasimenko.temperature.controller;

import ru.academits.gerasimenko.temperature.constants.TemperatureScalesConstants;
import ru.academits.gerasimenko.temperature.model.Converter;
import ru.academits.gerasimenko.temperature.view.View;

public class Controller {
    private final Converter converter;
    private final View view;

    public Controller(Converter converter, View view) {
        this.converter = converter;
        this.view = view;
    }

    public void convert(int inputScaleNumber, int outputScaleNumber, double temperature) {
        switch (inputScaleNumber) {
            case TemperatureScalesConstants.CELSIUS_SCALE_NUMBER -> {
                if (outputScaleNumber == TemperatureScalesConstants.CELSIUS_SCALE_NUMBER) {
                    view.showCelsiusTemperature(temperature);
                } else if (outputScaleNumber == TemperatureScalesConstants.KELVIN_SCALE_NUMBER) {
                    convertCelsiusToKelvin(temperature);
                } else {
                    convertCelsiusToFahrenheit(temperature);
                }
            }

            case TemperatureScalesConstants.KELVIN_SCALE_NUMBER -> {
                if (outputScaleNumber == TemperatureScalesConstants.CELSIUS_SCALE_NUMBER) {
                    convertKelvinToCelsius(temperature);
                } else if (outputScaleNumber == TemperatureScalesConstants.KELVIN_SCALE_NUMBER) {
                    view.showKelvinTemperature(temperature);
                } else {
                    convertKelvinToFahrenheit(temperature);
                }
            }

            case TemperatureScalesConstants.FAHRENHEIT_SCALE_NUMBER -> {
                if (outputScaleNumber == TemperatureScalesConstants.CELSIUS_SCALE_NUMBER) {
                    convertFahrenheitToCelsius(temperature);
                } else if (outputScaleNumber == TemperatureScalesConstants.KELVIN_SCALE_NUMBER) {
                    convertFahrenheitToKelvin(temperature);
                } else {
                    view.showFahrenheitTemperature(temperature);
                }
            }
        }
    }

    public void convertCelsiusToKelvin(double celsiusTemperature) {
        double kelvinTemperature = converter.convertCelsiusToKelvin(celsiusTemperature);
        view.showKelvinTemperature(kelvinTemperature);
    }

    public void convertCelsiusToFahrenheit(double celsiusTemperature) {
        double fahrenheitTemperature = converter.convertCelsiusToFahrenheit(celsiusTemperature);
        view.showFahrenheitTemperature(fahrenheitTemperature);
    }

    public void convertKelvinToCelsius(double kelvinTemperature) {
        double celsiusTemperature = converter.convertKelvinToCelsius(kelvinTemperature);
        view.showCelsiusTemperature(celsiusTemperature);
    }

    public void convertKelvinToFahrenheit(double kelvinTemperature) {
        double fahrenheitTemperature = converter.convertKelvinToFahrenheit(kelvinTemperature);
        view.showFahrenheitTemperature(fahrenheitTemperature);
    }

    public void convertFahrenheitToCelsius(double fahrenheitTemperature) {
        double celsiusTemperature = converter.convertFahrenheitToCelsius(fahrenheitTemperature);
        view.showCelsiusTemperature(celsiusTemperature);
    }

    public void convertFahrenheitToKelvin(double fahrenheitTemperature) {
        double kelvinTemperature = converter.convertFahrenheitToKelvin(fahrenheitTemperature);
        view.showKelvinTemperature(kelvinTemperature);
    }
}