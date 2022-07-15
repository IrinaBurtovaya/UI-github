import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    SelenideElement icon = $(".octicon-mark-github");
    SelenideElement headerProduct = $x("(//summary[@class='HeaderMenu-summary HeaderMenu-link " +
            "px-0 py-3 border-0 no-wrap d-block d-lg-inline-block'])[1]");
    SelenideElement headerTeam = $x("(//a[@class='HeaderMenu-link no-underline py-3 d-block " +
            "d-lg-inline-block'])[1]");
    SelenideElement headerEnterprise = $x("(//a[@class='HeaderMenu-link no-underline py-3 d-block " +
            "d-lg-inline-block'])[2]");
    SelenideElement headerExplore = $x("(//summary[@class='HeaderMenu-summary HeaderMenu-link px-0 py-3 " +
            "border-0 no-wrap d-block d-lg-inline-block'])[2]");
    SelenideElement headerMarketplace = $x("(//a[@class='HeaderMenu-link no-underline py-3 d-block " +
            "d-lg-inline-block'])[3]");
    SelenideElement headerPricing = $x("(//summary[@class='HeaderMenu-summary HeaderMenu-link px-0 py-3 " +
            "border-0 no-wrap d-block d-lg-inline-block'])[3]");

    SelenideElement searchField = $x("//input[@type='text']");

    SelenideElement signInButton = $x("//a[@href='/login']");

    SelenideElement EmailAddressField = $("#user_email");

    SelenideElement signUpButton = $x("//button[@type='submit']");

    SelenideElement errorSignUpText = $("#email-err");
}
