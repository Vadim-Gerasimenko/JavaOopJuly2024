package ru.academits.gerasimenko.minesweeper.view;

import ru.academits.gerasimenko.minesweeper.controller.Controller;

public interface View {
    void start();

    void setController(Controller controller);
}