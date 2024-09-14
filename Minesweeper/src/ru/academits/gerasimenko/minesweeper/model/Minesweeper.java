package ru.academits.gerasimenko.minesweeper.model;

import ru.academits.gerasimenko.minesweeper.model.field.PlayingField;
import ru.academits.gerasimenko.minesweeper.level.*;

public class Minesweeper implements Model {
    private Level level = new MediumLevel();
    private PlayingField playingField;

    @Override
    public void startGame() { //TODO: remove print
        playingField = new PlayingField(
                level.rowsCount(),
                level.columnsCount(),
                level.minesCount()
        );
    }

    public boolean openCell(int cellX, int cellY) {
        return playingField.openCell(cellX, cellY);
    }

    @Override
    public void setLevel(Level level) {
        this.level = level;
    }
}