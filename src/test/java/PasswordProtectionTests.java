import io.qameta.allure.AllureId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class PasswordProtectionTests extends TestBase {

    SignUpPage signUpPage = new SignUpPage();
    String testEmail = "test5555@mail.ru";

    @Test
    @AllureId("11403")
    @Tag("short")
    @DisplayName("Проверка защищенности пароля при создании аккаунта, тест-кейс 'short'")
    void checkPasswordProtectionShort() {
        step("Открываем страницу для тестирования", () -> {
            open(config.baseUrl());
        });
        step("На странице создания аккаунта вводим email", () ->
                signUpPage.setTestEmail(testEmail));
        step("Вводим короткий пароль", () -> signUpPage.passwordField.setValue("12345"));
        step("Проверяем текст ошибки", () -> signUpPage.passwordError.shouldHave(text("Password is too short\n" +
                "Make sure it's at least 15 characters OR at least 8 characters including a number and a lowercase letter.")));
    }

    @Test
    @AllureId("11404")
    @Tag("simple")
    @DisplayName("Проверка защищенности пароля при создании аккаунта, тест-кейс 'simple'")
    void checkPasswordProtectionSimple() {
        step("Открываем страницу для тестирования", () -> {
            open(config.baseUrl());
        });
        step("На странице создания аккаунта вводим email", () ->
                signUpPage.setTestEmail(testEmail));
        step("Вводим простой пароль", () -> signUpPage.passwordField.setValue("Qwerty12345"));
        step("Проверяем текст ошибки", () -> signUpPage.passwordError.shouldHave(text("Password may be compromised\n" +
                "Password is in a list of passwords commonly used on other websites")));
    }

    @Test
    @AllureId("11405")
    @Tag("onlyletters")
    @DisplayName("Проверка защищенности пароля при создании аккаунта, тест-кейс 'only letters'")
    void checkPasswordProtectionOnlyLetters() {
        step("Открываем страницу для тестирования", () -> {
            open(config.baseUrl());
        });
        step("На странице создания аккаунта вводим email", () ->
                signUpPage.setTestEmail(testEmail));
        step("Вводим пароль, состоящий только из букв", () -> signUpPage.passwordField.setValue("qwertyqwerty"));
        step("Проверяем текст ошибки", () -> signUpPage.passwordError.shouldHave(text("Password needs a number and lowercase letter\n" +
                "Make sure it's at least 15 characters OR " +
                "at least 8 characters including a number and a lowercase letter.")));
    }

    @Test
    @AllureId("11406")
    @Tag("strong")
    @DisplayName("Проверка защищенности пароля при создании аккаунта, тест-кейс 'strong'")
    void checkPasswordProtectionStrong() {
        step("Открываем страницу для тестирования", () -> {
            open(config.baseUrl());
        });
        step("На странице создания аккаунта вводим email", () ->
                signUpPage.setTestEmail(testEmail));
        step("Вводим сложный пароль", () -> signUpPage.passwordField.setValue("VeryStrongPassword12345"));
        step("Проверяем текст уведомления", () -> signUpPage.passwordError.shouldHave(text("Password is strong\n" +
                "Make sure it's at least 15 characters OR " +
                "at least 8 characters including a number and a lowercase letter.")));
    }
}
