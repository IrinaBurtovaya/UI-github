import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import config.WebConfig;
import io.qameta.allure.AllureId;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class GithubTests extends TestBase {

    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();
    SignUpPage signUpPage = new SignUpPage();
    String testEmail = "test5555@mail.ru";

    @Test
    @AllureId("11403")
    @Tag("short")
    @DisplayName("Проверка защищенности пароля при создании аккаунта, тест-кейс 'short'")
    void checkPasswordProtectionShort() {
        step("На странице создания аккаунта вводим тестовый email", () -> signUpPage
                .createAnAccountSetEmail(testEmail));

        step("Вводим короткий пароль", () -> signUpPage.passwordField.setValue("12345"));

        step("Проверяем текст ошибки", () -> signUpPage.passwordError.shouldHave(Condition
                .text("Password is too short\n" +
                        "Make sure it's at least 15 characters OR at least 8 characters including a number and" +
                        " a lowercase letter.")));
    }

    @Test
    @AllureId("11404")
    @Tag("simple")
    @DisplayName("Проверка защищенности пароля при создании аккаунта, тест-кейс 'simple'")
    void checkPasswordProtectionSimple() {
        step("На странице создания аккаунта вводим тестовый email", () -> signUpPage
                .createAnAccountSetEmail(testEmail));

        step("Вводим простой пароль", () -> signUpPage.passwordField.setValue("Qwerty12345"));

        step("Проверяем текст ошибки", () -> signUpPage.passwordError.shouldHave(Condition
                .text("Password may be compromised\n" +
                        "Password is in a list of passwords commonly used on other websites")));
    }

    @Test
    @AllureId("11405")
    @Tag("onlyletters")
    @DisplayName("Проверка защищенности пароля при создании аккаунта, тест-кейс 'only letters'")
    void checkPasswordProtectionOnlyLetters() {
        step("На странице создания аккаунта вводим тестовый email", () -> signUpPage
                .createAnAccountSetEmail(testEmail));

        step("Вводим пароль, состоящий только из букв", () -> signUpPage.passwordField.setValue("qwertyqwerty"));

        step("Проверяем текст ошибки", () -> signUpPage.passwordError.shouldHave(Condition
                .text("Password needs a number and lowercase letter\n" +
                        "Make sure it's at least 15 characters OR " +
                        "at least 8 characters including a number and a lowercase letter.")));
    }

    @Test
    @AllureId("11406")
    @Tag("strong")
    @DisplayName("Проверка защищенности пароля при создании аккаунта, тест-кейс 'strong'")
    void checkPasswordProtectionStrong() {
        step("На странице создания аккаунта вводим тестовый email", () -> signUpPage
                .createAnAccountSetEmail(testEmail));

        step("Вводим сложный пароль", () -> signUpPage.passwordField.setValue("VeryStrongPassword12345"));

        step("Проверяем текст уведомления", () -> signUpPage.passwordError.shouldHave(Condition
                .text("Password is strong\n" +
                        "Make sure it's at least 15 characters OR " +
                        "at least 8 characters including a number and a lowercase letter.")));
    }

    @Test
    @AllureId("11407")
    @Tag("repeatedreg")
    @DisplayName("Проверка повторной регистрации")
    void doRepeatedRegistration() {
        step("Вводим email уже зарегистрированного пользователя", () -> mainPage.EmailAddressField.
                setValue(config.login()));
        step("Кликаем кнопку Sign Up", () -> mainPage.signUpButton.click());
        step("Проверяем текст ошибки", () -> mainPage.errorSignUpText.should(Condition.have(Condition.
                text("Email is invalid or already taken"))));
    }

    @Test
    @AllureId("11408")
    @Tag("headers")
    @DisplayName("Проверка отображения логотипа и заголовков главной страницы")
    void checkHeaders() {
        step("Проверяем отображение лого", () -> Assertions.assertTrue(mainPage.icon.isDisplayed()));
        step("проверяем наличие хедэра Product", () -> mainPage.headerProduct.shouldHave(Condition.
                text("Product")));
        step("проверяем наличие хедэра Team", () -> mainPage.headerTeam.shouldHave(Condition.text("Team")));
        step("проверяем наличие хедэра Enterprise", () -> mainPage.headerEnterprise.shouldHave(Condition.
                text("Enterprise")));
        step("проверяем наличие хедэра Explore", () -> mainPage.headerExplore.shouldHave(Condition.
                text("Explore")));
        step("проверяем наличие хедэра Marketplace", () -> mainPage.headerMarketplace.shouldHave(Condition.
                text("Marketplace")));
        step("проверяем наличие хедэра Pricing", () -> mainPage.headerPricing.shouldHave(Condition.text("Pricing")));
    }

    @Test
    @AllureId("11409")
    @Tag("search")
    @DisplayName("Проверка результатов поиска")
    void checkSearchResults() {
        step("На главной странице вводим слово для поиска", () -> {
            mainPage.searchField.setValue("java").pressEnter();
        });
        step("Проверяем, что искомых результатов больше чем 5", () -> $$x("//ul[@class='repo-list']/*")
                .shouldHave(CollectionCondition.sizeGreaterThan(5)));
    }

    @ValueSource(strings = {
            "testUsername",
            "testEmail"
    })
    @ParameterizedTest(name = "Проверка неуспешной авторизации {0}")
    @AllureId("11410")
    @Tag("unsuccessfulauth")
    void doUnsuccessfulAuth(String testData) {
        step("На главной странице кликаем кнопку авторизации", () -> mainPage.signInButton.click());

        step("Вводим невалидные данные, нажимаем кнопку 'Sign in'", () -> loginPage.setUsernameOrEmailField(testData)
                .setPassword("testPassword")
                .signInButton.click());
        step("Проверяем, что сообщение об ошибке содержит текст 'Incorrect username or password.'",
                () -> loginPage.errorText.shouldHave(Condition.text("Incorrect username or password.")));
    }
}
