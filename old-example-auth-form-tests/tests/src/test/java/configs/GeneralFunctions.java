package configs;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;

import static authPage.AuthPageLocators.LOGIN_INPUT;
import static authPage.AuthPageLocators.PASSWD_INPUT;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static configs.LOGIN_PASSWD.LOGIN;
import static configs.LOGIN_PASSWD.PASSWD;
import static configs.PageURLs.AUTH_PAGE_URL;
import static configs.PageURLs.MAIN_PAGE_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class GeneralFunctions {

    protected void setUp() {
        Configuration.timeout = 20000;    // Неявное ожидание
        // Configuration.headless = true; // Для запуска без графического показа
    }

    protected void fastLogin(){
        // Here will be more powerful to realize it by API
        setUp();
        openAuthPage();

        $(LOGIN_INPUT).shouldBe(visible).setValue(LOGIN);
        $(By.xpath(PASSWD_INPUT)).shouldBe(visible).setValue(PASSWD);
        $("button").shouldBe(enabled).click();

        $(LOGIN_INPUT).should(disappear);                 // Само подождёт, пока элемент исчезнет
        $(By.xpath(PASSWD_INPUT)).shouldBe(disappear);
        assertEquals(MAIN_PAGE_URL, getCurrentUrl());
    }

    protected String getCurrentUrl(){
        return WebDriverRunner.getWebDriver().getCurrentUrl();
    }

    protected void openAuthPage(){
        setUp();
        open(AUTH_PAGE_URL);
        confirm();

        localStorage().clear();
        sessionStorage().clear();
        open(AUTH_PAGE_URL);
        confirm();
    }
}
