package ru.academits.gerasimenko.minesweeper.view.gui.panel;

import ru.academits.gerasimenko.minesweeper.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class PlayingPanel extends JPanel implements Panel {
    private final JFrame frame;
    private final JPanel menuPanel;
    private final Controller controller;

    private final PlayingPanelCell[][] cells;

    private static class PlayingPanelCell extends JButton {
        public PlayingPanelCell(int cellX, int cellY, Controller controller) {
            setFocusable(false);
            setOpaque(true);
            setBackground(new Color(253, 201, 241));

            addActionListener(e -> controller.openCell(cellX, cellY));
        }
    }

    public PlayingPanel(int rowsCount, int columnsCount, JFrame frame, JPanel menuPanel, Controller controller) {
        this.frame = frame;
        this.menuPanel = menuPanel;
        this.controller = controller;

        setLayout(new GridLayout(rowsCount, columnsCount));

        controller.startGame();
        cells = new PlayingPanelCell[rowsCount][columnsCount];

        for (int y = 0; y < rowsCount; ++y) {
            for (int x = 0; x < columnsCount; ++x) {
                PlayingPanelCell cell = new PlayingPanelCell(x, y, controller);
                cells[y][x] = cell;

                add(cell);
            }
        }

        frame.add(this);
    }

    @Override
    public void showPanel() {
        menuPanel.setVisible(false);
        setVisible(true);
    }

    @Override
    public void hidePanel() {
        // frame.add(menuPanel);
        // frame.remove(this);
        //menuPanel.setVisible(true);
        setVisible(false);
        menuPanel.setVisible(true);
    }
}