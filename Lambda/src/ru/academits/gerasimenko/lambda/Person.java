package ru.academits.gerasimenko.lambda;

public record Person(String name, int age) {
    public Person {
        if (age < 0) {
            throw new IllegalArgumentException("The 'age' field must not be negative. "
                    + "Current age: " + age);
        }
    }
}