import io.qameta.allure.AllureId;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class AuthTests extends TestBase {

    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();

    @ValueSource(strings = {
            "testUsername",
            "testEmail"
    })
    @ParameterizedTest(name = "Проверка неуспешной авторизации {0}")
    @AllureId("11410")
    @Tag("unsuccessfulauth")
    void doUnsuccessfulAuth(String testData) {
        step("Открываем страницу для тестирования", () -> {
            open(config.baseUrl());
        });
        step("На главной странице кликаем кнопку авторизации", () -> mainPage.signInButton.click());

        step("Вводим невалидные данные, нажимаем кнопку 'Sign in'", () -> loginPage.setUsernameOrEmailField(testData)
                .setPassword("testPassword")
                .signInButton.click());
        step("Проверяем, что сообщение об ошибке содержит текст 'Incorrect username or password.'", () ->
                loginPage.errorText.shouldHave(text("Incorrect username or password.")));
    }
}
