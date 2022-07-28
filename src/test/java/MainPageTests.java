import io.qameta.allure.AllureId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class MainPageTests extends TestBase {
    MainPage mainPage = new MainPage();

    @Test
    @AllureId("11408")
    @Tag("headers")
    @DisplayName("Проверка отображения логотипа и заголовков главной страницы")
    void checkHeaders() {
        step("Открываем страницу для тестирования", () -> {
            open(config.baseUrl());
        });
        step("Проверяем отображение лого", () -> Assertions.assertTrue(mainPage.icon.isDisplayed()));
        step("проверяем наличие хедэра Product", () -> mainPage.headerProduct.shouldHave(text("Product")));
        step("проверяем наличие хедэра Team", () -> mainPage.headerTeam.shouldHave(text("Team")));
        step("проверяем наличие хедэра Enterprise", () -> mainPage.headerEnterprise.shouldHave(text("Enterprise")));
        step("проверяем наличие хедэра Explore", () -> mainPage.headerExplore.shouldHave(text("Explore")));
        step("проверяем наличие хедэра Marketplace", () -> mainPage.headerMarketplace.shouldHave(text("Marketplace")));
        step("проверяем наличие хедэра Pricing", () -> mainPage.headerPricing.shouldHave(text("Pricing")));
    }
}
