package test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static data.DataGenerator.*;

public class CardOrderTest {

    @BeforeEach
    void opening() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSubmitRequest() {
        $("[data-test-id=city] input").setValue(getCiti());
        $("[placeholder='Дата встречи']").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(generateDate(9));
        $("[data-test-id=name] input").setValue(generateName());
        $("[data-test-id=phone] input").setValue(generatePhone());
        $("[data-test-id=agreement]").click();
        $(withText("Запланировать")).click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofMillis(15000));
        $(withText(generateDate(9))).shouldBe(Condition.visible);
        $(withText("Запланировать")).click();
        $(withText("Необходимо подтверждение")).shouldBe(Condition.visible, Duration.ofMillis(15000));
        $(withText("Перепланировать")).click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofMillis(15000));
        $(withText(generateDate(9))).shouldBe(Condition.visible);
    }
}
