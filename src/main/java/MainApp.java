import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainApp {
    public static void main(String[] args) {

        String[] words = { "Длинношеее", "Длинношеее", "Нитка", "Кошка", "Кошка" };

        List<Person> persons = new ArrayList<>(Arrays.asList(
                new Person("Михаил",40,55000),
                new Person("Роман",30,35000),
                new Person("Павел",35,40000),
                new Person("Иван",20,25000)
        ));


        System.out.println(
                Stream.of(words)
                        .collect(Collectors.groupingBy(String::valueOf, Collectors.counting()))
                        .entrySet().stream()
                        .filter(o -> o.getValue() == Stream.of(words)
                                .collect(Collectors.groupingBy(String::valueOf, Collectors.counting()))
                                .values().stream().mapToInt(Long::intValue).max().getAsInt())
                        .map(Map.Entry::getKey)
                        .sorted((s1, s2) -> s1.length() - s2.length())
                        .collect(Collectors.joining(", ","Наиболее часто встречающееся слова ",""))
        );




        System.out.println( "Средняя зарплата сотрудников: " +
                persons.stream()
                        .mapToDouble(Person::getMoney)
                        .average()
                        .getAsDouble()
        );




        System.out.println(
                persons.stream()
                        .sorted((o1,o2) -> o2.getAge() - o1.getAge())
                        .map(Person::getName)
                        .limit(3)
                        .collect(Collectors.joining(", ","Имена 3 старших сотрудников ","."))
        );
    }
}
