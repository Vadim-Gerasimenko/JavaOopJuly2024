package ru.academits.gerasimenko.temperature.view;

import ru.academits.gerasimenko.temperature.controller.Controller;

public interface View {
    void start();

    void setController(Controller controller);

    void showTemperature(double temperature);
}