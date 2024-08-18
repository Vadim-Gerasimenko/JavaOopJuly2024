package ru.academits.gerasimenko.temperature.view;

import ru.academits.gerasimenko.temperature.constants.FrameConfigureConstants;
import ru.academits.gerasimenko.temperature.constants.ImagesConstants;
import ru.academits.gerasimenko.temperature.constants.TemperatureScalesConstants;
import ru.academits.gerasimenko.temperature.controller.Controller;

import javax.swing.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class DesktopView implements View {
    private Controller controller;

    private JLabel resultLabel;

    private int inputScaleNumber;
    private int outputScaleNumber;

    @Override
    public void start() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Temperature converter");
            configureFrameWithDefaultSettings(frame);

            JPanel panel = new JPanel();

            JRadioButton celsiusInputScaleSelectButton = getInputScaleSelectButton(TemperatureScalesConstants.CELSIUS_SCALE, TemperatureScalesConstants.CELSIUS_SCALE_NUMBER);
            JRadioButton kelvinInputScaleSelectButton = getInputScaleSelectButton(TemperatureScalesConstants.KELVIN_SCALE, TemperatureScalesConstants.KELVIN_SCALE_NUMBER);
            JRadioButton fahrenheitInputScaleSelectButton = getInputScaleSelectButton(TemperatureScalesConstants.FAHRENHEIT_SCALE, TemperatureScalesConstants.FAHRENHEIT_SCALE_NUMBER);
            ButtonGroup inputScalesSelectButtons = getButtonGroup(List.of(celsiusInputScaleSelectButton, kelvinInputScaleSelectButton, fahrenheitInputScaleSelectButton));

            JRadioButton celsiusOutputScaleSelectButton = getOutputScaleSelectButton(TemperatureScalesConstants.CELSIUS_SCALE, TemperatureScalesConstants.CELSIUS_SCALE_NUMBER);
            JRadioButton kelvinOutputScaleSelectButton = getOutputScaleSelectButton(TemperatureScalesConstants.KELVIN_SCALE, TemperatureScalesConstants.KELVIN_SCALE_NUMBER);
            JRadioButton fahrenheitOutputScaleSelectButton = getOutputScaleSelectButton(TemperatureScalesConstants.FAHRENHEIT_SCALE, TemperatureScalesConstants.FAHRENHEIT_SCALE_NUMBER);
            ButtonGroup outputScalesSelectButtons = getButtonGroup(List.of(celsiusOutputScaleSelectButton, kelvinOutputScaleSelectButton, fahrenheitOutputScaleSelectButton));

            JLabel inputScaleLabel = new JLabel("Input scale:");
            JLabel inputTemperatureLabel = new JLabel("Enter temperature:");
            JLabel outputScaleLabel = new JLabel("Output scale:");

            final int inputTextFieldColumnsCount = 10;
            JTextField inputTemperatureTextField = new JTextField(inputTextFieldColumnsCount);

            JButton temperatureConvertingButton = new JButton("convert");
            temperatureConvertingButton.addActionListener(e -> {
                try {
                    validateSelectedButton(inputScalesSelectButtons);
                    validateSelectedButton(outputScalesSelectButtons);

                    double inputTemperature = Double.parseDouble(inputTemperatureTextField.getText());
                    controller.convert(inputScaleNumber, outputScaleNumber, inputTemperature);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Temperature must be a real number.", "Error", JOptionPane.ERROR_MESSAGE, ImagesConstants.TEMPERATURE_ICON);
                } catch (IllegalCallerException ex) {
                    JOptionPane.showMessageDialog(frame, "Select I/O scales.", "Warning", JOptionPane.WARNING_MESSAGE, ImagesConstants.TEMPERATURE_ICON);
                }
            });

            resultLabel = new JLabel();

            panel.add(inputScaleLabel);
            panel.add(celsiusInputScaleSelectButton);
            panel.add(kelvinInputScaleSelectButton);
            panel.add(fahrenheitInputScaleSelectButton);

            panel.add(inputTemperatureLabel);
            panel.add(inputTemperatureTextField);
            panel.add(temperatureConvertingButton);

            panel.add(outputScaleLabel);
            panel.add(celsiusOutputScaleSelectButton);
            panel.add(kelvinOutputScaleSelectButton);
            panel.add(fahrenheitOutputScaleSelectButton);

            panel.add(resultLabel);

            frame.add(panel);
        });
    }

    private JRadioButton getInputScaleSelectButton(String buttonText, int scaleNumber) {
        JRadioButton scaleSelectButton = new JRadioButton(buttonText);
        scaleSelectButton.addActionListener(e -> inputScaleNumber = scaleNumber);
        return scaleSelectButton;
    }

    private JRadioButton getOutputScaleSelectButton(String buttonText, int scaleNumber) {
        JRadioButton scaleSelectButton = new JRadioButton(buttonText);
        scaleSelectButton.addActionListener(e -> outputScaleNumber = scaleNumber);
        return scaleSelectButton;
    }

    private static void validateSelectedButton(ButtonGroup buttonGroup) {
        boolean isSelected = false;

        for (Iterator<AbstractButton> buttonsIterator = buttonGroup.getElements().asIterator();
             buttonsIterator.hasNext();
             ) {
            if (buttonsIterator.next().isSelected()) {
                isSelected = true;
                break;
            }
        }

        if (!isSelected) {
            throw new IllegalCallerException();
        }
    }

    private static ButtonGroup getButtonGroup(Collection<AbstractButton> buttonsCollection) {
        ButtonGroup buttonGroup = new ButtonGroup();

        for (AbstractButton button : buttonsCollection) {
            buttonGroup.add(button);
        }

        return buttonGroup;
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
        this.controller = controller;
    }

    @Override
    public void showCelsiusTemperature(double celsiusTemperature) {
        resultLabel.setText("Temperature in Celsius degrees: " + celsiusTemperature);
    }

    @Override
    public void showKelvinTemperature(double kelvinTemperature) {
        resultLabel.setText("Temperature in Kelvin degrees: " + kelvinTemperature);
    }

    @Override
    public void showFahrenheitTemperature(double fahrenheitTemperature) {
        resultLabel.setText("Temperature in Fahrenheit degrees: " + fahrenheitTemperature);
    }
}