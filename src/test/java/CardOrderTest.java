import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderTest {

    private Faker faker;

    @BeforeEach
    void setUpAll() {
        faker = new Faker(new Locale("ru"));
    }

    static final String[] admCiti = {"Белгород", "Брянск", "Владимир", "Воронеж", "Иваново", "Калуга", "Кострома",
            "Курск", "Липецк", "Москва", "Орел", "Рязань", "Тамбов", "Тверь", "Тула", "Сантк-Петербург", "Казань"};
    Random random = new Random();
    int citi = random.nextInt(admCiti.length);

    @BeforeEach
    void opening() {
        open("http://localhost:9999");
    }

    public String date(int shift) {
        LocalDate date = LocalDate.now();
        LocalDate returnDate = date.plusDays(shift);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return formatter.format(returnDate);
    }

    @Test
    void shouldSubmitRequest() {
        $("[data-test-id=city] input").setValue(admCiti[citi]);
        $("[placeholder='Дата встречи']").sendKeys(Keys.CONTROL + "a");
        $("[placeholder='Дата встречи']").sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue("" + date(9));
        $("[data-test-id=name] input").setValue(faker.name().fullName());
        $("[data-test-id=phone] input").setValue(faker.phoneNumber().phoneNumber());
        $("[data-test-id=agreement]").click();
        $(withText("Запланировать")).click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofMillis(15000));
        $(withText(""+date(9))).shouldBe(Condition.visible);
        $(withText("Запланировать")).click();
        $(withText("Необходимо подтверждение")).shouldBe(Condition.visible, Duration.ofMillis(15000));
        $(withText("Перепланировать")).click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofMillis(15000));
        $(withText(""+date(9))).shouldBe(Condition.visible);
    }
}
