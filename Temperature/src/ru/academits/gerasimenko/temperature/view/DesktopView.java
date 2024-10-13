package ru.academits.gerasimenko.temperature.view;

import ru.academits.gerasimenko.temperature.view.constants.*;
import ru.academits.gerasimenko.temperature.controller.Controller;
import ru.academits.gerasimenko.temperature.scales.*;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class DesktopView implements View {
    private Controller controller;

    private JLabel resultLabel;

    private List<Scale> scales;
    private Scale inputScale;
    private Scale outputScale;

    @Override
    public void start() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Temperature converter");
            configureFrameWithDefaultSettings(frame);

            controller.setAvailableScales();

            List<AbstractButton> inputScalesSelectButtons = new LinkedList<>();
            List<AbstractButton> outputScalesSelectButtons = new LinkedList<>();

            ButtonGroup inputScalesSelectButtonsGroup = new ButtonGroup();
            ButtonGroup outputScalesSelectButtonsGroup = new ButtonGroup();

            for (Scale scale : scales) {
                AbstractButton inputScaleSelectButton = getInputScaleSelectButton(scale);
                inputScalesSelectButtons.add(inputScaleSelectButton);
                inputScalesSelectButtonsGroup.add(inputScaleSelectButton);

                AbstractButton outputScaleSelectButton = getOutputScaleSelectButton(scale);
                outputScalesSelectButtons.add(outputScaleSelectButton);
                outputScalesSelectButtonsGroup.add(outputScaleSelectButton);
            }

            inputScalesSelectButtons.getFirst().doClick();
            outputScalesSelectButtons.getLast().doClick();

            JLabel inputScaleLabel = new JLabel("Input scale:");
            JLabel inputTemperatureLabel = new JLabel("Enter temperature:");
            JLabel outputScaleLabel = new JLabel("Output scale:");

            final int inputTextFieldColumnsCount = 10;
            JTextField inputTemperatureTextField = new JTextField(inputTextFieldColumnsCount);
            JButton temperatureConversionButton = getTemperatureConversionButton(inputTemperatureTextField, frame);

            JPanel panel = new JPanel();
            panel.add(inputScaleLabel);

            for (AbstractButton button : inputScalesSelectButtons) {
                panel.add(button);
            }

            panel.add(inputTemperatureLabel);
            panel.add(inputTemperatureTextField);
            panel.add(temperatureConversionButton);
            panel.add(outputScaleLabel);

            for (AbstractButton button : outputScalesSelectButtons) {
                panel.add(button);
            }

            resultLabel = new JLabel();
            panel.add(resultLabel);

            frame.add(panel);
        });
    }

    private JRadioButton getInputScaleSelectButton(Scale inputScale) {
        JRadioButton scaleSelectButton = new JRadioButton(inputScale.getScaleName());
        scaleSelectButton.addActionListener(e -> this.inputScale = inputScale);
        return scaleSelectButton;
    }

    private JRadioButton getOutputScaleSelectButton(Scale outputScale) {
        JRadioButton scaleSelectButton = new JRadioButton(outputScale.getScaleName());
        scaleSelectButton.addActionListener(e -> this.outputScale = outputScale);
        return scaleSelectButton;
    }

    private JButton getTemperatureConversionButton(JTextField inputTemperatureTextField, JFrame frame) {
        JButton temperatureConversionButton = new JButton("Convert");

        temperatureConversionButton.addActionListener(e -> {
            try {
                double inputTemperature = Double.parseDouble(inputTemperatureTextField.getText());
                controller.convert(inputTemperature, inputScale, outputScale);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Temperature must be a real number.", "Error",
                        JOptionPane.ERROR_MESSAGE, ImagesConstants.TEMPERATURE_ICON);
            }
        });

        return temperatureConversionButton;
    }

    private static void configureFrameWithDefaultSettings(JFrame frame) {
        frame.setSize(FrameConfigureConstants.FRAME_DEFAULT_WIDTH, FrameConfigureConstants.FRAME_DEFAULT_HEIGHT);
        frame.setResizable(FrameConfigureConstants.IS_RESIZABLE_BY_DEFAULT);
        frame.setLocationRelativeTo(FrameConfigureConstants.DEFAULT_LOCATION_RELATIVE_TO);
        frame.setDefaultCloseOperation(FrameConfigureConstants.DEFAULT_CLOSE_OPERATION);
        frame.setBackground(FrameConfigureConstants.DEFAULT_BACKGROUND_COLOR);
        frame.setVisible(FrameConfigureConstants.IS_VISIBLE_BY_DEFAULT);
    }

    @Override
    public void setController(Controller controller) {
        if (controller == null) {
            throw new NullPointerException("Controller must not refer to null");
        }

        this.controller = controller;
    }

    @Override
    public void showTemperature(double temperature) {
        resultLabel.setText("Temperature in " + outputScale.getScaleName() + " degrees: " + temperature);
    }

    @Override
    public void setAvailableScales(List<Scale> scales) {
        if (scales == null) {
            throw new NullPointerException("The list of available scales must not refer to null");
        }

        this.scales = scales;
    }
}