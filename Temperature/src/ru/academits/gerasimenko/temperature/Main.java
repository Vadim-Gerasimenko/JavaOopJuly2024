package ru.academits.gerasimenko.temperature;

import ru.academits.gerasimenko.temperature.controller.Controller;
import ru.academits.gerasimenko.temperature.model.Converter;
import ru.academits.gerasimenko.temperature.model.TemperatureConverter;
import ru.academits.gerasimenko.temperature.view.ConsoleView;
import ru.academits.gerasimenko.temperature.view.View;

public class Main {
    public static void main(String[] args) {
        Converter converter = new TemperatureConverter();
        View view = new ConsoleView();

        Controller controller = new Controller(converter, view);
        view.setController(controller);
        view.start();
    }
}