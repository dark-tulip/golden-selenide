package mainPage;

import configs.GeneralFunctions;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static configs.PageURLs.PORTFOLIO_PAGE_URL;
import static mainPage.MainPageLocators.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class PortfolioPageTest extends GeneralFunctions {

    @Test
    public void userCanGoToPortfolioFromMainPage(){
        /**
         * TEST ID #10
         * Название: userCanGoToPortfolioFromMainPage
         *
         * Описание: Юзер может перейти в свой портфель с Главной страницы
         *
         * Шаги воспроизведения:
         *      1.	Зарегистрироваться
         *      2.	Зайти на вкладку Портфель
         *
         * Ожидаемый результат: Открылась страница Портфель
         * */

        fastLogin();

        $(byXpath(HEADER_PORTFOLIO_LINK)).shouldBe(visible).click();
        $("div.title").shouldHave(text("Портфель"));
        assertEquals(getCurrentUrl(), PORTFOLIO_PAGE_URL);
    }


    @Test
    public void userHasActivesSum(){
        /**
         * TEST ID #11
         * Название: userHasActivesSum
         *
         * Описание: У юзера есть активная сумма в портфеле
         *
         * Шаги воспроизведения:
         *      1.	Зарегистрироваться
         *      2.	Зайти на вкладку Портфель
         *
         * Ожидаемый результат: Открылась страница Портфель, отображена текузая сумма
         * */
        fastLogin();

        $(byXpath(HEADER_PORTFOLIO_LINK)).shouldBe(visible).click();
        assertEquals(getCurrentUrl(), PORTFOLIO_PAGE_URL);
        $(PORTFOLIO_TOTAL_CASH).shouldNotBe(empty);
    }


    @Test
    public void userHasActivesSumOnPortfolioTable(){
        /**
         * TEST ID #12
         * Название: userHasActivesSumOnPortfolioTable
         *
         * Описание: У каждой строки в таблице портфеля есть сумма
         *
         * Шаги воспроизведения:
         *      1.	Зарегистрироваться
         *      2.	Зайти на вкладку Портфель
         *
         * Ожидаемый результат: У каждой строки в таблице снизу портфеля есть сумма
         * */

        fastLogin();

        $(byXpath(HEADER_PORTFOLIO_LINK)).shouldBe(visible).click();
        $("div.title").shouldHave(text("Портфель"));

        assertEquals(getCurrentUrl(), PORTFOLIO_PAGE_URL);
        $("tr.portfolioTable__item").shouldBe(visible);
        $("tr.portfolioTable__item td").shouldBe(visible);

        for(int i = 1; i < 4; i++){
            String moneyValue = $(byXpath(String.format("(//td[@class='portfolioTable__item-amount'])[%d]", i)))
                    .shouldBe(visible)
                    .shouldNotBe(empty).getText();
            // System.out.println(moneyValue); - for debugging
        }
    }
}
