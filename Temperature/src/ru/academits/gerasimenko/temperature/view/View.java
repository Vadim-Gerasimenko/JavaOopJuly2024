package ru.academits.gerasimenko.temperature.view;

import ru.academits.gerasimenko.temperature.controller.Controller;
import ru.academits.gerasimenko.temperature.scales.Scale;

import java.util.List;

public interface View {
    void start();

    void setController(Controller controller);

    void showTemperature(double temperature);

    void setAvailableScales(List<Scale> scales);
}