package ru.academits.gerasimenko.temperature.view;

import ru.academits.gerasimenko.temperature.constants.TemperatureScalesConstants;
import ru.academits.gerasimenko.temperature.controller.Controller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleView implements View {
    private Controller controller;

    @Override
    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean isContinued = true;

            while (isContinued) {
                printHead();

                final String messageToGetInputScale = "Enter the number of the input data scale: ";
                int inputScaleNumber = getScaleNumber(messageToGetInputScale, scanner);

                final String messageToGetOutputScale = "Enter the number of the output data scale: ";
                int outputScaleNumber = getScaleNumber(messageToGetOutputScale, scanner);

                double temperature = getCorrectTemperature(scanner);

                controller.convert(inputScaleNumber, outputScaleNumber, temperature);
                scanner.nextLine();

                isContinued = terminateOrContinue(scanner);
            }
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void showCelsiusTemperature(double celsiusTemperature) {
        System.out.println("Temperature in Celsius degrees: " + celsiusTemperature);
    }

    @Override
    public void showKelvinTemperature(double kelvinTemperature) {
        System.out.println("Temperature in Kelvin degrees: " + kelvinTemperature);
    }

    @Override
    public void showFahrenheitTemperature(double fahrenheitTemperature) {
        System.out.println("Temperature in Fahrenheit degrees: " + fahrenheitTemperature);
    }

    private static void printHead() {
        System.out.println("[CONVERTING TEMPERATURE]");
        System.out.println("Available scales:");
        System.out.println("1. Celsius");
        System.out.println("2. Kelvin");
        System.out.println("3. Fahrenheit");
    }

    private static int getScaleNumber(Scanner scanner) {
        int scaleNumber = scanner.nextInt();
        validateScaleNumber(scaleNumber);
        return scaleNumber;
    }

    private static double getTemperature(Scanner scanner) {
        return scanner.nextDouble();
    }

    private static void validateScaleNumber(int scaleNumber) {
        if (!TemperatureScalesConstants.SCALES_NUMBERS_SET.contains(scaleNumber)) {
            throw new IllegalArgumentException("Invalid value of scale number. "
                    + "Valid numbers: " + TemperatureScalesConstants.SCALES_NUMBERS_SET + ". "
                    + "Current number: " + scaleNumber);
        }
    }

    private static int getScaleNumber(String welcomeMessage, Scanner scanner) {
        boolean isCorrectScaleNumber = false;
        int ScaleNumber = -1;

        while (!isCorrectScaleNumber) {
            try {
                System.out.print(welcomeMessage);
                ScaleNumber = getScaleNumber(scanner);
                isCorrectScaleNumber = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Scale number must be an integer. "
                        + "Current value: " + scanner.nextLine()
                );
            }
        }

        return ScaleNumber;
    }

    private static double getCorrectTemperature(Scanner scanner) {
        boolean isCorrectTemperature = false;
        Double temperature = null;

        while (!isCorrectTemperature) {
            try {
                System.out.print("Enter temperature: ");
                temperature = getTemperature(scanner);
                isCorrectTemperature = true;
            } catch (InputMismatchException e) {
                System.out.println("Temperature must be specified as a real number. "
                        + "Current value: " + scanner.nextLine()
                );
            }
        }

        return temperature;
    }

    private static boolean terminateOrContinue(Scanner scanner) {
        final String terminationText = "end";
        System.out.print("To end enter \"" + terminationText + "\", to continue enter any character or sequence: ");
        boolean isContinued = true;

        if (scanner.nextLine().equalsIgnoreCase(terminationText)) {
            isContinued = false;
        } else {
            System.out.println();
        }

        return isContinued;
    }
}