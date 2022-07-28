import io.qameta.allure.AllureId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class SearchTests extends TestBase {

    MainPage mainPage = new MainPage();

    @Test
    @AllureId("11409")
    @Tag("search")
    @DisplayName("Проверка результатов поиска")
    void checkSearchResults() {
        step("Открываем страницу для тестирования", () -> {
            open(config.baseUrl());
        });
        step("На главной странице вводим слово для поиска", () -> {
            mainPage.searchField.setValue("java").pressEnter();
        });
        step("Проверяем, что искомых результатов больше чем 5", () ->
                $$x("//ul[@class='repo-list']/*").shouldHave(sizeGreaterThan(5)));
    }
}
