package ru.academits.gerasimenko.temperature.view;

import ru.academits.gerasimenko.temperature.controller.Controller;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsoleView implements View {
    private Controller controller;

    private static final HashSet<Integer> scalesNumbersSet = new HashSet<>(List.of(1, 2, 3));

    @Override
    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean isContinued = true;

            while (isContinued) {
                printHead();

                boolean isCorrectInputDataScaleNumber = false;
                int inputDataScaleNumber = -1;

                while (!isCorrectInputDataScaleNumber) {
                    try {
                        System.out.print("Enter the number of the input data scale: ");
                        inputDataScaleNumber = getScaleNumber(scanner);
                        isCorrectInputDataScaleNumber = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("Scale number must be an integer. "
                                + "Current value: " + scanner.nextLine()
                        );
                    }
                }

                boolean isCorrectOutputDataScaleNumber = false;
                int outputDataScaleNumber = -1;

                while (!isCorrectOutputDataScaleNumber) {
                    try {
                        System.out.print("Enter the number of the output data scale: ");
                        outputDataScaleNumber = getScaleNumber(scanner);
                        isCorrectOutputDataScaleNumber = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("Scale number must be an integer. "
                                + "Current value: " + scanner.nextLine()
                        );
                    }
                }

                boolean isCorrectTemperature = false;
                double temperature = 0;

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

                switch (inputDataScaleNumber) {
                    case 1 -> {
                        if (outputDataScaleNumber == 1) {
                            showCelsiusTemperature(temperature);
                        } else if (outputDataScaleNumber == 2) {
                            controller.convertCelsiusToKelvin(temperature);
                        } else {
                            controller.convertCelsiusToFahrenheit(temperature);
                        }
                    }

                    case 2 -> {
                        if (outputDataScaleNumber == 1) {
                            controller.convertKelvinToCelsius(temperature);
                        } else if (outputDataScaleNumber == 2) {
                            showKelvinTemperature(temperature);
                        } else {
                            controller.convertKelvinToFahrenheit(temperature);
                        }
                    }

                    case 3 -> {
                        if (outputDataScaleNumber == 1) {
                            controller.convertFahrenheitToCelsius(temperature);
                        } else if (outputDataScaleNumber == 2) {
                            controller.convertFahrenheitToKelvin(temperature);
                        } else {
                            showFahrenheitTemperature(temperature);
                        }
                    }
                }

                scanner.nextLine();

                final String terminationText = "end";
                System.out.print("To end enter \"" + terminationText + "\", to continue enter any character or sequence: ");

                if (scanner.nextLine().equalsIgnoreCase(terminationText)) {
                    isContinued = false;
                } else {
                    System.out.println();
                }
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
        if (!scalesNumbersSet.contains(scaleNumber)) {
            throw new IllegalArgumentException("Invalid value of scale number. "
                    + "Valid numbers: " + scalesNumbersSet + ". "
                    + "Current number: " + scaleNumber);
        }
    }
}