package ru.academits.gerasimenko.lambda;

public record Person(String name, int age) {
    public Person {
        if (name == null) {
            throw new IllegalArgumentException("The 'name' field must not refer to null.");
        }

        if (age < 0) {
            throw new IllegalArgumentException("The 'age' field must not be negative. "
                    + "Current age: " + age);
        }
    }
}