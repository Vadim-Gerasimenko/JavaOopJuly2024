package ru.academits.gerasimenko.minesweeper.view.gui.panel;

import ru.academits.gerasimenko.minesweeper.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel implements Panel {
    private final JFrame frame;
    private final Controller controller;

    public MenuPanel(JFrame frame, Controller controller) {
        this.controller = controller;
        this.frame = frame;

        setLayout(new GridLayout(1, 1));

        JButton playButton = new JButton("Play");
        playButton.addActionListener(e -> {
            Panel playingPanel = new PlayingPanel(9, 9, frame, this, controller);
            playingPanel.showPanel();
        });
        add(playButton);
    }

    @Override
    public void showPanel() {
    }

    @Override
    public void hidePanel() {
    }
}