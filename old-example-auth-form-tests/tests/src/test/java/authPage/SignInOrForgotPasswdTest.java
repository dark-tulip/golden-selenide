package authPage;

import configs.GeneralFunctions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static authPage.AuthPageLocators.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static configs.LOGIN_PASSWD.LOGIN;


public class SignInOrForgotPasswdTest extends GeneralFunctions {

    @Test
    public void showsRestorePasswdDiv (){
        /***
         * TEST ID #5
         * Название: showsRestorePasswdDiv
         *
         * Описание: На странице авторизации есть кнопка для восстановления пароля / регистрации
         *
         * Шаги воспроизведения:
         *      1.	Зайти на страницу авторизации
         *      2.	Нажать на кнопку восстановить пароль / регистрация
         *
         * Ожидаемый результат: Кнопка нажимается, выходит соответсвующее модальное окно
         */

        $(REGISTRATION_OR_FORGET_PASSWD).shouldBe(visible).click();
        $(RESTORE_PASSWD_DIV).shouldBe(visible);
        $(RESTORE_PASSWD_DIV).shouldHave(matchText("Регистрация / Забыли пароль?"));
    }


    @Test
    public void userCannotRestorePasswdWithEmptyIINAndPhone(){
        /**
         * TEST ID #6
         * Название: userCannotRestorePasswdWithEmptyIINAndPhone
         *
         * Описание: Юзер не может восстановить пароль с пустым ИИН и телефоном
         *
         * Шаги воспроизведения:
         *      1.	Зайти на страницу авторизации
         *      2.	Нажать на кнопку восстановить пароль / регистрация
         *      3.	Нажать на Отправить
         *
         * Ожидаемый результат: Выходит предупреждение о неверном логине или пароле
         *
         * **/

        $(REGISTRATION_OR_FORGET_PASSWD).shouldBe(visible).click();
        $(RESTORE_PASSWD_DIV).shouldBe(visible);
        $(RESTORE_PASSWD_DIV).shouldHave(matchText("Регистрация / Забыли пароль?"));
        $(RESTORE_PASSWD_SUBMIT_BTN).shouldBe(visible).click();
        $(RESTORE_PASSWD_DIV).shouldHave(matchText("Пользователь с такими данными не найден!"));
    }


    @Test
    public void userCannotRestorePasswdWithEmptyIIN(){
        /**
         * TEST ID #7
         * Название: userCannotRestorePasswdWithEmptyIIN
         *
         * Описание: Юзер не может восстановить пароль с пустым ИИН
         *
         * Шаги воспроизведения:
         *      1.	Зайти на страницу авторизации
         *      2.	Нажать на кнопку восстановить пароль / регистрация
         *      3.	Ввести ИИН
         *      4.	Нажать на Отправить
         *
         * Ожидаемый результат: Выходит предупреждение о неверном логине или пароле
         *
         * */
        $(REGISTRATION_OR_FORGET_PASSWD).shouldBe(visible).click();
        $(RESTORE_PASSWD_DIV).shouldBe(visible);
        $(RESTORE_PASSWD_DIV).shouldHave(matchText("Регистрация / Забыли пароль?"));

        $(byXpath(RESTORE_PASSWD_INPUT_PHONE)).should(visible).click();
        $(byXpath(RESTORE_PASSWD_INPUT_PHONE)).setValue("+77072644009");

        $(RESTORE_PASSWD_SUBMIT_BTN).shouldBe(visible).click();
        $(RESTORE_PASSWD_DIV).shouldHave(matchText("Пользователь с такими данными не найден!"));
    }


    @Test
    public void userCannotRestorePasswdWithEmptyPhone(){
        /**
         * TEST ID #8
         * Название: userCannotRestorePasswdWithEmptyPhone
         *
         * Описание: Юзер не может восстановить пароль с пустым полем Телефон
         *
         * Шаги воспроизведения:
         *      1.	Зайти на страницу авторизации
         *      2.	Нажать на кнопку восстановить пароль / регистрация
         *      3.	Ввести номер телефона
         *      4.	Нажать на Отправить
         *
         * Ожидаемый результат: Выходит предупреждение о неверном логине или пароле
         *
         * */
        $(REGISTRATION_OR_FORGET_PASSWD).shouldBe(visible).click();
        $(RESTORE_PASSWD_DIV).shouldBe(visible);
        $(RESTORE_PASSWD_DIV).shouldHave(matchText("Регистрация / Забыли пароль?"));

        $(byXpath(RESTORE_IIN_INPUT)).should(visible).click();
        $(byXpath(RESTORE_IIN_INPUT)).setValue(LOGIN);

        $(RESTORE_PASSWD_SUBMIT_BTN).shouldBe(visible).click();
        $(RESTORE_PASSWD_DIV).shouldHave(matchText("Пользователь с такими данными не найден!"));
    }


    @Test
    public void userCannotRestorePasswdWithWrongPhoneAndIIN(){
        /**
         * TEST ID #9
         * Название: userCannotRestorePasswdWithWrongPhoneAndIIN
         *
         * Описание: Юзер не может восстановить пароль с неверными данными ИИН и Телефон
         *
         * Шаги воспроизведения:
         *      1.	Зайти на страницу авторизации
         *      2.	Нажать на кнопку восстановить пароль / регистрация
         *      3.	Ввести номер телефона
         *      4.	Ввести ИИН
         *      5.	Нажать на Отправить
         *
         * Ожидаемый результат: Выходит предупреждение о неверном логине или пароле
         * */

        $(REGISTRATION_OR_FORGET_PASSWD).shouldBe(visible).click();
        $(RESTORE_PASSWD_DIV).shouldBe(visible);
        $(RESTORE_PASSWD_DIV).shouldHave(matchText("Регистрация / Забыли пароль?"));

        $(byXpath(RESTORE_IIN_INPUT)).should(visible).click();
        $(byXpath(RESTORE_IIN_INPUT)).setValue(LOGIN);

        $(byXpath(RESTORE_PASSWD_INPUT_PHONE)).should(visible).click();
        $(byXpath(RESTORE_PASSWD_INPUT_PHONE)).setValue("+77072644009");

        $(RESTORE_PASSWD_SUBMIT_BTN).shouldBe(visible).click();
        $(RESTORE_PASSWD_DIV).shouldHave(matchText("Пользователь с такими данными не найден!"));
    }

    @BeforeEach
    public void beforeEachClearCache(){
        setUp();
        openAuthPage();
    }
}
