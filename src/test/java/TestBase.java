import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.WebConfig;
import helpers.Attachments;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestBase {

    static WebConfig config = ConfigFactory.create(WebConfig.class, System.getProperties());

    @BeforeAll
    public static void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        Configuration.browser = config.browser();
        Configuration.browserVersion = config.version();
        Configuration.browserSize = config.browserSize();
        Configuration.timeout = 10000;

        if (config.remote() != null) {
            Configuration.remote = config.remote();
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        Attachments.screenshotAs("Last screenshot");
        Attachments.pageSource();
        Attachments.browserConsoleLogs();
        Attachments.addVideo();
        Selenide.closeWebDriver();
    }
}
