package ru.academits.gerasimenko.lambda.main;

import ru.academits.gerasimenko.lambda.Person;

import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = List.of(
                new Person("Ivan", 23),
                new Person("Alexandr", 25),
                new Person("Olga", 47),
                new Person("Olga", 25),
                new Person("Egor", 27)
        );

        List<String> uniqueNames = persons.stream()
                .map(Person::name)
                .distinct()
                .toList();

        String uniqueNamesString = uniqueNames.stream()
                .collect(Collectors.joining(", ", "Names: ", "."));

        System.out.println(uniqueNamesString);

        List<Person> minors = persons.stream()
                .filter(person -> person.age() < 18)
                .toList();

        OptionalDouble minorsAverageAge = minors.stream()
                .mapToInt(Person::age)
                .average();

        if (minorsAverageAge.isEmpty()) {
            System.out.println("There are no people under 18 years of age on the list.");
        } else {
            System.out.println("Minors average age: " + minorsAverageAge.getAsDouble());
        }

        Map<String, Double> namesakesAverageAges = persons.stream()
                .collect(Collectors.groupingBy(Person::name, Collectors.averagingInt(Person::age)));

        System.out.println("Namesakes average age: " + namesakesAverageAges);

        System.out.println("Names of people from 20 to 45 in descending order of age:");
        persons.stream()
                .filter(person -> person.age() >= 20 && person.age() <= 45)
                .sorted((person1, person2) -> person2.age() - person1.age())
                .map(Person::name)
                .forEach(System.out::println);
    }
}