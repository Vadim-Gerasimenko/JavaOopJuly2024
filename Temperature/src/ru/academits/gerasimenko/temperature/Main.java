package ru.academits.gerasimenko.temperature;

import ru.academits.gerasimenko.temperature.controller.*;
import ru.academits.gerasimenko.temperature.model.*;
import ru.academits.gerasimenko.temperature.view.*;

public class Main {
    public static void main(String[] args) {
        Converter converter = new TemperatureConverter();
        View view = new DesktopView();

        Controller controller = new Controller(converter, view);
        view.setController(controller);

        view.start();
    }
}