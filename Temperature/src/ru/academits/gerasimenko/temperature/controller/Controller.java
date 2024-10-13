package ru.academits.gerasimenko.temperature.controller;

import ru.academits.gerasimenko.temperature.model.Converter;
import ru.academits.gerasimenko.temperature.scales.Scale;
import ru.academits.gerasimenko.temperature.view.View;

public class Controller {
    private final Converter converter;
    private final View view;

    public Controller(Converter converter, View view) {
        if (converter == null) {
            throw new NullPointerException("Converter must not refer to null");
        }

        if (view == null) {
            throw new NullPointerException("View must not refer to null");
        }

        this.converter = converter;
        this.view = view;
    }

    public void convert(double temperature, Scale inputScale, Scale outputScale) {
        if (inputScale == null || outputScale == null) {
            throw new NullPointerException("Passed scales must not refer to null");
        }

        double convertedTemperature = converter.convert(temperature, inputScale, outputScale);
        view.showTemperature(convertedTemperature);
    }

    public void setAvailableScales() {
        view.setAvailableScales(converter.getAvailableScalesList());
    }
}