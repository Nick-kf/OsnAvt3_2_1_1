package ru.netology.domain;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CartTest {
    @Test
    void shouldSumbitRequest() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Олег Иванов");
        $("[data-test-id=phone] input").setValue("+79999999999");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $(By.className("paragraph")).shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldNotSendIncorrectName() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Oleg Ivanov");
        $("[data-test-id=phone] input").setValue("+79999999999");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $(".input_type_text .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotSendIncorrectPhone() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Олег Иванов");
        $("[data-test-id=phone] input").setValue("+7123456789102");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $(".input_type_tel .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSendIncorrectCheckbox() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Олег Иванов");
        $("[data-test-id=phone] input").setValue("+79999999999");
        $(By.className("button")).click();
       // $(".checkbox__text").shouldHave(cssValue("color", "rgba(255, 92, 92, 1)"));
        $("[data-test-id=agreement].input_invalid").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

    @Test
    void shouldNotSendEmptyName() {
        open("http://localhost:9999/");
        $("[data-test-id=phone] input").setValue("+79999999999");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $(".input_type_text .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotSendEmptyPhone() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Олег Иванов");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $(".input_type_tel .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
}
