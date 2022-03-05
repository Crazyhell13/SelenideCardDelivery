package ru.netology.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryCardFormTest {
    String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    String invalidDate = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

@BeforeEach
public void SetUp(){
    open("http://localhost:9999/");
    $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
}
    @Test
    void shouldTestHappyPath(){
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Енотовый Енот");
        $("[data-test-id='phone'] input").setValue("+79000000000");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='notification']").shouldHave(text("Встреча успешно забронирована на " + date),Duration.ofSeconds(15));
    }
    @Test
    void shouldTestEmpty(){
        $("[data-test-id='city'] input").setValue("");
        $("[data-test-id='date'] input").setValue("");
        $("[data-test-id='name'] input").setValue("");
        $("[data-test-id='phone'] input").setValue("");
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }
    @Test
    void shouldTestWithoutCity(){
        $("[data-test-id='city'] input").setValue("");
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Енотовый Енот");
        $("[data-test-id='phone'] input").setValue("+79000000000");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }
    @Test
    void shouldTestInvalidCity1(){
        $("[data-test-id='city'] input").setValue("Елец");
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Енотовый Енот");
        $("[data-test-id='phone'] input").setValue("+79000000000");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldHave(text("Доставка в выбранный город недоступна"));
    }
    @Test
    void shouldTestInvalidCity2(){
        $("[data-test-id='city'] input").setValue("Moscow");
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Енотовый Енот");
        $("[data-test-id='phone'] input").setValue("+79000000000");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldHave(text("Доставка в выбранный город недоступна"));
    }
    @Test
    void shouldTestWithoutName(){
        $("[data-test-id='city'] input").setValue("Санкт-Петербург");
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("");
        $("[data-test-id='phone'] input").setValue("+79000000000");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }
    @Test
    void shouldTestInvalidName1(){
        $("[data-test-id='city'] input").setValue("Саратов");
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Rabid Racoon");
        $("[data-test-id='phone'] input").setValue("+79000000000");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
    void shouldTestInvalidName2(){
        $("[data-test-id='city'] input").setValue("Мурманск");
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Енотовый_Енот");
        $("[data-test-id='phone'] input").setValue("+79000000000");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
    void shouldTestInvalidName3(){
        $("[data-test-id='city'] input").setValue("Казань");
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("999");
        $("[data-test-id='phone'] input").setValue("+79000000000");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
    void shouldTestWithoutPhone(){
        $("[data-test-id='city'] input").setValue("Вологда");
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Енотовый Енот");
        $("[data-test-id='phone'] input").setValue("");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }
    @Test
    void shouldTestInvalidPhone1(){
        $("[data-test-id='city'] input").setValue("Ульяновск");
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Енотовый Енот");
        $("[data-test-id='phone'] input").setValue("+7900000000");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    void shouldTestInvalidPhone2(){
        $("[data-test-id='city'] input").setValue("Владивосток");
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Енотовый Енот");
        $("[data-test-id='phone'] input").setValue("89000000000");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    void shouldTestInvalidPhone3(){
        $("[data-test-id='city'] input").setValue("Ростов-на-Дону");
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Енотовый Енот");
        $("[data-test-id='phone'] input").setValue("Енот");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    void shouldTestWithoutAgreement (){
        $("[data-test-id='city'] input").setValue("Абакан");
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Енотовый Енот");
        $("[data-test-id='phone'] input").setValue("+79000000000");
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='agreement'].input_invalid .checkbox__text").shouldHave(text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }
    @Test
    void shouldTestWithoutDate (){
        $("[data-test-id='city'] input").setValue("Иваново");
        $("[data-test-id='date'] input").setValue("");
        $("[data-test-id='name'] input").setValue("Енотовый Енот");
        $("[data-test-id='phone'] input").setValue("+79000000000");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(".calendar-input .input_invalid .input__sub").shouldHave(text("Неверно введена дата"));
    }
    @Test
    void shouldTestInvalidDate (){
        $("[data-test-id='city'] input").setValue("Владимир");
        $("[data-test-id='date'] input").setValue(invalidDate);
        $("[data-test-id='name'] input").setValue("Енотовый Енот");
        $("[data-test-id='phone'] input").setValue("+79000000000");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(".calendar-input .input_invalid .input__sub").shouldHave(text("Заказ на выбранную дату невозможен"));
    }
    @Test
    void shouldTestInvalidDate2 (){
        $("[data-test-id='city'] input").setValue("Сыктывкар");
        $("[data-test-id='date'] input").setValue("01.01.2022");
        $("[data-test-id='name'] input").setValue("Енотовый Енот");
        $("[data-test-id='phone'] input").setValue("+79000000000");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(".calendar-input .input_invalid .input__sub").shouldHave(text("Заказ на выбранную дату невозможен"));
    }
    }

