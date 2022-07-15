import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SignUpPage {

    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();
    SelenideElement emailField = $("#email");
    SelenideElement passwordField = $("#password");
    SelenideElement passwordError = $("#password-err");
    SelenideElement continueButton = $x("//button[@data-continue-to='password-container']");

    public void createAnAccountSetEmail(String testEmail) {
        mainPage.signInButton.click();
        loginPage.createAnAccountButton.click();
        emailField.setValue(testEmail).pressEnter();
        continueButton.pressEnter();
    }
}
