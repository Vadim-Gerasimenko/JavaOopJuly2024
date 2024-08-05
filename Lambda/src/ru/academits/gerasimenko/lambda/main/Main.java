package ru.academits.gerasimenko.lambda.main;

import ru.academits.gerasimenko.lambda.Person;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = new LinkedList<>();
        persons.add(new Person("Svetlana", 15));
        persons.add(new Person("Kirill", 23));
        persons.add(new Person("Kirill", 14));
        persons.add(new Person("Egor", 17));
        persons.add(new Person("Ivan", 23));
        persons.add(new Person("Alexandr", 45));
        persons.add(new Person("Olga", 47));
        persons.add(new Person("Olga", 13));
        persons.add(new Person("Egor", 27));

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

        double minorsAverageAge = minors.stream()
                .collect(Collectors.averagingDouble(Person::age));

        System.out.println("Minors average age: " + minorsAverageAge);

        Map<String, Double> namesakesAverageAge = persons.stream()
                .collect(Collectors.groupingBy(Person::name, Collectors.averagingDouble(Person::age)));

        System.out.println("Namesakes average age: " + namesakesAverageAge);

        System.out.println("Names of people from 20 to 45 in descending order of age:");
        persons.stream()
                .filter(person -> person.age() >= 20 && person.age() <= 45)
                .sorted((person1, person2) -> person2.age() - person1.age())
                .map(Person::name)
                .forEach(System.out::println);
    }
}