package ru.netology.selenide;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class DeliveryCardTest {
    private String getLocalDate(int daysToAdd, String pattern){
        return LocalDate.now().plusDays(daysToAdd).format(DateTimeFormatter.ofPattern(pattern));
    }
    @Test
    void shouldVerifyFields(){
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(getLocalDate(5, "dd.MM.yyyy"));
        $("[data-test-id='name'] input").setValue("Петров Василий");
        $("[data-test-id='phone'] input").setValue("+79123456789");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(".notification__title").shouldHave(visible.text("Успешно!"), Duration.ofSeconds(15));
        $(".notification__content").shouldHave(visible.text("Встреча успешно забронирована на " +
                getLocalDate(5, "dd.MM.yyyy")), Duration.ofSeconds(15));

    }

}
