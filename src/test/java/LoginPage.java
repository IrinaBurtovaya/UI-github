import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    SelenideElement usernameOrEmailField = $("#login_field");
    SelenideElement passwordField = $("#password");
    SelenideElement signInButton = $x("//input[@type='submit']");
    SelenideElement errorText = $x("//div[@class='px-2']");

    public LoginPage setUsernameOrEmailField(String testData) {
        usernameOrEmailField.setValue(testData);
        return this;
    }

    public LoginPage setPassword(String testPassword) {
        passwordField.setValue(testPassword);
        return this;
    }
}
