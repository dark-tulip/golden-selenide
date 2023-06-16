package authPage;

import configs.GeneralFunctions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static configs.LOGIN_PASSWD.*;
import static configs.PageURLs.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static authPage.AuthPageLocators.*;

public class UserAuthorizationTest extends GeneralFunctions {

    @Test
    public void userCanLoginToMainPage() {
        /**
         * TEST ID #1
         * Название:  userCanLoginToMainPage
         *
         * Описание: Введя правильный ИИН и Пароль пользователь заходит на главную страницу
         *
         * Шаги воспроизведения:
         *      1.	Зайти на страницу авторизации
         *      2.	Ввести логин
         *      3.	Ввести пароль
         *      4.	Нажать - Войти
         *
         * Ожидаемый результат: Пользователь перешел на главную страницу
         * */

        fastLogin();
        assertEquals(MAIN_PAGE_URL, getCurrentUrl());
    }


    @Test
    public void userCannotLoginWithWrongPasswd(){
        /**
         * TEST ID #2
         * Название: userCannotLoginWithWrongPasswd
         *
         * Описание: Введя правильный ИИН и Неверный Пароль пользователь не заходит на главную страницу
         *
         * Шаги воспроизведения:
         *      1.	Зайти на страницу авторизации
         *      2.	Ввести логин
         *      3.	Ввести пароль - неправильный
         *      4.	Нажать - Войти
         *
         * Ожидаемый результат: Пользователь не перешел на главную страницу. Вышло предупреждение «Неверный логин или пароль»
         * */

        openAuthPage();
        $(LOGIN_INPUT).shouldBe(visible).setValue(LOGIN);
        $(By.xpath(PASSWD_INPUT)).shouldBe(visible).setValue(PASSWD + "addSomethingBad");
        $("button").shouldBe(enabled).click();
        $(WRONG_PASSWD_OR_LOGIN).shouldHave(matchText("Введен неверный логин или пароль"));
    }


    @Test
    public void userCannotLoginWithWrongLogin(){
        /**
         * Название: userCannotLoginWithWrongLogin
         *
         * Описание: Введя НЕправильный ИИН пользователь не заходит на главную страницу
         *
         * Шаги воспроизведения:
         *      1.	Зайти на страницу авторизации
         *      2.	Ввести логин - неправильный
         *      3.	Ввести пароль
         *      4.	Нажать - Войти
         *
         * Ожидаемый результат: вышло предупреждение «Неверный логин или пароль»
         *
         * */
        openAuthPage();
        $(LOGIN_INPUT).shouldBe(visible).setValue(LOGIN + "123");
        $(By.xpath(PASSWD_INPUT)).shouldBe(visible).setValue(PASSWD);
        $("button").shouldBe(enabled).click();
        $(WRONG_PASSWD_OR_LOGIN).shouldHave(matchText("Введен неверный логин или пароль"));
    }


    @Test
    public void userCannotAddLettersToLoginForm(){
        /**
         * Название: userCannotAddLettersToLoginForm
         *
         * Описание: Пользователь не может ввести буквы в поле ввода ИИН
         *
         * Шаги воспроизведения:
         *      1.	Зайти на страницу авторизации
         *      2.	Ввести логин (ИИН) – содержащий буквы
         *
         * Ожидаемый результат: Буквы не вводятся
         * */

        openAuthPage();
        $(LOGIN_INPUT).shouldBe(visible).setValue("HAHAHA");
        $(LOGIN_INPUT).shouldBe(visible).shouldBe(empty);

        $(LOGIN_INPUT).shouldBe(visible).setValue("12345HAHAHA");
        $(LOGIN_INPUT).shouldBe(visible).shouldHave(value("12345"));
    }
}
