package ru.academits.gerasimenko.temperature.view;

import ru.academits.gerasimenko.temperature.controller.Controller;

public interface View {
    void start();

    void setController(Controller controller);

    void showCelsiusTemperature(double celsiusTemperature);

    void showKelvinTemperature(double kelvinTemperature);

    void showFahrenheitTemperature(double fahrenheitTemperature);
}