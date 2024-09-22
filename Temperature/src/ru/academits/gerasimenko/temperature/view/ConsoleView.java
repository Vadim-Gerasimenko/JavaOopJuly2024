package ru.academits.gerasimenko.temperature.view;

import ru.academits.gerasimenko.temperature.scales.*;
import ru.academits.gerasimenko.temperature.controller.Controller;

import java.util.*;

public class ConsoleView implements View {
    private Controller controller;

    private final TreeMap<Integer, Scale> availableScales = new TreeMap<>();
    private Scale outputScale;

    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean isContinued = true;

        controller.setAvailableScales();

        while (isContinued) {
            printHead();

            final String messageToGetInputScale = "Enter the number of the input data scale: ";
            int inputScaleNumber = getScaleNumber(messageToGetInputScale, scanner);

            final String messageToGetOutputScale = "Enter the number of the output data scale: ";
            int outputScaleNumber = getScaleNumber(messageToGetOutputScale, scanner);
            outputScale = availableScales.get(outputScaleNumber);

            double temperature = getCorrectTemperature(scanner);

            controller.convert(temperature, availableScales.get(inputScaleNumber), outputScale);
            scanner.nextLine();

            isContinued = terminateOrContinue(scanner);
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void showTemperature(double temperature) {
        System.out.println("Temperature in " + outputScale.getScaleName() + " degrees: " + temperature);
    }

    @Override
    public void setAvailableScales(List<Scale> scales) {
        int i = 1;

        for (Scale scale : scales) {
            availableScales.put(i, scale);
            ++i;
        }
    }

    private void printHead() {
        System.out.println("[TEMPERATURE CONVERSION]");
        System.out.println("Available scales:");
        availableScales.forEach((k, v) -> System.out.println(k + ". " + v.getScaleName()));
    }

    private int getScaleNumber(Scanner scanner) {
        int scaleNumber = scanner.nextInt();
        validateScaleNumber(scaleNumber);
        return scaleNumber;
    }

    private static double getTemperature(Scanner scanner) {
        return scanner.nextDouble();
    }

    private void validateScaleNumber(int scaleNumber) {
        if (!availableScales.containsKey(scaleNumber)) {
            throw new IllegalArgumentException("Invalid value of scale number. "
                    + "Valid numbers: " + availableScales.keySet() + ". "
                    + "Current number: " + scaleNumber);
        }
    }

    private int getScaleNumber(String welcomeMessage, Scanner scanner) {
        while (true) {
            try {
                System.out.print(welcomeMessage);

                return getScaleNumber(scanner);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Scale number must be an integer. "
                        + "Current value: " + scanner.nextLine()
                );
            }
        }
    }

    private static double getCorrectTemperature(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Enter temperature: ");

                return getTemperature(scanner);
            } catch (InputMismatchException e) {
                System.out.println("Temperature must be specified as a real number. "
                        + "Current value: " + scanner.nextLine()
                );
            }
        }
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