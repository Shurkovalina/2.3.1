package data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {

    static Faker faker = new Faker(new Locale("ru"));

    public static String getName() {
        return faker.name().fullName();
    }

    public static String getPhone() {
        return faker.phoneNumber().phoneNumber();
    }

    public static String getCiti() {
        String[] admCiti = {"Воронеж", "Москва", "Рязань", "Тамбов", "Тула", "Сантк-Петербург", "Казань"};
        Random random = new Random();
        int citi = random.nextInt(admCiti.length);
        return admCiti[citi];
    }

    public static String generateDate(int shift) {
        LocalDate generateDate = LocalDate.now();
        LocalDate returnDate = generateDate.plusDays(shift);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return formatter.format(returnDate);
    }

}
