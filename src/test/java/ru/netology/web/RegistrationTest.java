package ru.netology.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
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


    @Test
    void shouldRegisterByAccountKomplexElements() {
//        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        ElementsCollection text = $$("input[type='text']");
        text.first().setValue("Ке");
        ElementsCollection cities = $$(".menu-item");
        SelenideElement city = cities.find(text("Кемерово"));
        city.click();
        text.last().setValue("Иванов Иван");
        ElementsCollection tel = $$("input[type='tel']");
        tel.first().sendKeys(Keys.CONTROL + "a");
        tel.first().sendKeys(Keys.DELETE);
        ElementsCollection arrows = $$(".calendar__arrow_direction_right");
        SelenideElement name = $(".calendar__name");
        String[] parts = name.getText().split(" ");
        String month = parts[0].toLowerCase();
        String year = parts[1];
        LocalDate date = LocalDate.now().plusDays(395);
        Locale ru = new Locale("ru", "RU");
//        System.out.println(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        String check_month = date.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, ru);
        if (!check_month.equals(month)) {
            arrows.get(1).click();
        }
        int check_year = date.getYear();
        if (check_year != Integer.parseInt(year)) {
            arrows.get(0).click();
        }
        ElementsCollection days = $$(".calendar__day");
        SelenideElement day = days.find(text(date.format(DateTimeFormatter.ofPattern("d"))));
        day.click();
        tel.last().setValue("+79000000000");
        $(byText("Я соглашаюсь с условиями обработки и использования моих персональных данных")).click();
        $("span[class='button__text']").click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15));
    }
}
