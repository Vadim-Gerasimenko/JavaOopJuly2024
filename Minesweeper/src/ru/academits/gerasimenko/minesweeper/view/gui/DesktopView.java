package ru.academits.gerasimenko.minesweeper.view.gui;

import ru.academits.gerasimenko.minesweeper.controller.Controller;
import ru.academits.gerasimenko.minesweeper.view.View;
import ru.academits.gerasimenko.minesweeper.view.gui.panel.MenuPanel;

import javax.swing.*;
import java.awt.*;

public class DesktopView extends JFrame implements View {
    private Controller controller;

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void start() {
        SwingUtilities.invokeLater(() -> {
            setTitle("Minesweeper");
            setSize(330, 450);
            setMinimumSize(new Dimension(280, 320));
            setResizable(true);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setBackground(Color.MAGENTA);
            setVisible(true);

            MenuPanel menuPanel = new MenuPanel(this, controller);
            add(menuPanel);
        });
    }
}