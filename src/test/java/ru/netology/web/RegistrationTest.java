package ru.netology.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.SetValueOptions.withDate;

public class RegistrationTest {

    @Test
    void shouldRegisterByAccount() {
//        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        ElementsCollection text = $$("input[type='text']");
        text.first().setValue("Москва");
        text.last().setValue("Иванов Иван");
        ElementsCollection tel = $$("input[type='tel']");
        tel.first().sendKeys(Keys.CONTROL + "a");
        tel.first().sendKeys(Keys.DELETE);
        LocalDate date = LocalDate.now().plusDays(5);
        tel.first().setValue(date.format(DateTimeFormatter.ofPattern("ddMMyyyy")));
        tel.last().setValue("+79000000000");
        $(byText("Я соглашаюсь с условиями обработки и использования моих персональных данных")).click();
        $("span[class='button__text']").click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15));
    }
}
