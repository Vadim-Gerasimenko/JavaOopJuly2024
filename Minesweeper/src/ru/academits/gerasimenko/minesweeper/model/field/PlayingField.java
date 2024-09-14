package ru.academits.gerasimenko.minesweeper.model.field;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PlayingField {
    private Cell[][] playingField;
    private final int minesCount;
    private boolean isMinesPutted;

    public PlayingField(int rowsCount, int columnsCount, int minesCount) {
        create(rowsCount, columnsCount);
        this.minesCount = minesCount;
    }

    private static final class Cell {
        private final int coordinateX;
        private final int coordinateY;

        private boolean isMined;
        private boolean isOpen;

        private final List<Cell> adjacentCells = new LinkedList<>();

        private Cell(int coordinateX, int coordinateY) {
            this.coordinateX = coordinateX;
            this.coordinateY = coordinateY;
        }

        @Override
        public String toString() {//TODO: remove
            return "y = " + coordinateY + " " + "x = " + coordinateX + ", mine = " + isMined + ", isOpen= " + isOpen;
        }

        private void setAdjacentCells(Cell[][] playingField) {//TODO: come up with a simpler algorithm
            int cellX = coordinateX;
            int cellY = coordinateY;

            for (int y = cellY - 1; y <= cellY + 1; ++y) {
                if (y >= 0 && y < playingField.length) {
                    for (int x = cellX - 1; x <= cellX + 1; ++x) {
                        if (x >= 0 && x < playingField[0].length) {
                            if (y != cellY || x != cellX) {
                                Cell adjacentCell = playingField[y][x];
                                adjacentCells.add(adjacentCell);
                            }
                        }
                    }
                }
            }
        }

        private int getAdjacentMinedCellsCount() {
            int adjacentMinedCellsCount = 0;

            for (Cell cell : adjacentCells) {
                if (cell.isMined) {
                    ++adjacentMinedCellsCount;
                }
            }

            return adjacentMinedCellsCount;
        }
    }

    private void create(int rowsCount, int columnsCount) {
        playingField = new Cell[rowsCount][columnsCount];

        for (int y = 0; y < playingField.length; ++y) {
            for (int x = 0; x < playingField[0].length; ++x) {
                playingField[y][x] = new Cell(x, y);
            }
        }

        for (int y = 0; y < rowsCount; ++y) {
            for (int x = 0; x < columnsCount; ++x) {
                playingField[y][x].setAdjacentCells(playingField);
            }
        }
    }

    private void putMines(int minesCount, int startCellX, int startCellY) {
        Cell startCell = playingField[startCellY][startCellX];
        List<Cell> safeCells = new LinkedList<>(startCell.adjacentCells);
        safeCells.add(startCell);

        Random random = new Random();

        for (int i = 0; i < minesCount; ++i) {
            boolean isMinePutted = false;

            while (!isMinePutted) {
                int mineY = random.nextInt(playingField.length);
                int mineX = random.nextInt(playingField[0].length);

                if (!playingField[mineY][mineX].isMined && !safeCells.contains(playingField[mineY][mineX])) {
                    playingField[mineY][mineX].isMined = true;
                    isMinePutted = true;
                }
            }
        }
    }

    public boolean openCell(int cellX, int cellY) {
        if (!isMinesPutted) {
            putMines(minesCount, cellX, cellY);
            isMinesPutted = true;

            return true;
        }

        Cell cell = playingField[cellY][cellX];

        if (cell.isMined) {
            return false;
        }

        Deque<Cell> stack = new LinkedList<>();
        stack.push(cell);

        while (!stack.isEmpty()) {
            cell = stack.pop();

            if (!cell.isOpen && !cell.isMined) {
                cell.isOpen = true;

                if (cell.getAdjacentMinedCellsCount() == 0) {
                    stack.addAll(cell.adjacentCells);
                }

                System.out.println(cell + "  OPEEEEEN: ADJMINES = " + cell.getAdjacentMinedCellsCount());
            }
        }

        return true;
    }

    @Override
    public String toString() {
        for (Cell[] cells : playingField) { //TODO: remove
            for (int x = 0; x < playingField[0].length; ++x) {
                System.out.println(cells[x]);
            }
        }
        return "";
    }
}