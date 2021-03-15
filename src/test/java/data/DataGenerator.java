package data;

import com.github.javafaker.Faker;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {

    static Faker faker = new Faker(new Locale("ru"));

    public static String generateName() {
        return faker.name().fullName();
    }

    public static String generatePhone() {
        return faker.phoneNumber().phoneNumber();
    }

    public static String getCiti() {
        String[] admCiti = {"Воронеж", "Москва", "Рязань", "Тамбов", "Тула", "Казань"};
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
