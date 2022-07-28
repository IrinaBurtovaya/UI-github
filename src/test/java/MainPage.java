import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    SelenideElement icon = $(".octicon-mark-github");
    SelenideElement headerProduct = $x("(//summary[contains(@class,'HeaderMenu-summary')])[1]");
    SelenideElement headerTeam = $x("((//a[contains(@class,'HeaderMenu-link')])[1]");
    SelenideElement headerEnterprise = $x("(//a[contains(@class,'HeaderMenu-link')])[2]");
    SelenideElement headerExplore = $x("(//summary[contains(@class,'HeaderMenu-summary')])[2]");
    SelenideElement headerMarketplace = $x("(//a[contains(@class,'HeaderMenu-link')])[3]");
    SelenideElement headerPricing = $x("(//summary[contains(@class,'HeaderMenu-summary')])[3]");
    SelenideElement searchField = $x("//input[@type='text']");
    SelenideElement signInButton = $x("//a[@href='/login']");
}
