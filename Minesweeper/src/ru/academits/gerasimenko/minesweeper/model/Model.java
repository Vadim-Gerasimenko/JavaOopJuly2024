package ru.academits.gerasimenko.minesweeper.model;

import ru.academits.gerasimenko.minesweeper.level.Level;

public interface Model {
    void startGame();

    void setLevel(Level level);

    boolean openCell(int cellX, int cellY);
}