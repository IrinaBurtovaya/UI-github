import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class SignUpPage {

    SelenideElement emailField = $("#email");
    SelenideElement passwordField = $("#password");
    SelenideElement passwordError = $("#password-err");
    SelenideElement continueButton = $x("//button[@data-continue-to='password-container']");

    public void setTestEmail(String testEmail) {
        open("https://github.com/signup?source=login");
        emailField.setValue(testEmail).pressEnter();
        continueButton.pressEnter();
    }
}
