package ru.academits.gerasimenko.temperature;

import ru.academits.gerasimenko.temperature.controller.*;
import ru.academits.gerasimenko.temperature.model.*;
import ru.academits.gerasimenko.temperature.scales.CelsiusScale;
import ru.academits.gerasimenko.temperature.scales.FahrenheitScale;
import ru.academits.gerasimenko.temperature.scales.KelvinScale;
import ru.academits.gerasimenko.temperature.view.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Converter converter = new TemperatureConverter(List.of(
                new CelsiusScale(),
                new FahrenheitScale(),
                new KelvinScale()
        ));
        View view = new DesktopView();

        Controller controller = new Controller(converter, view);
        view.setController(controller);

        view.start();
    }
}