package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/${env}.properties")

public interface WebConfig extends Config {

    @Key("browser")
    @DefaultValue("chrome")
    String browser();

    @Key("version")
    @DefaultValue("100.0")
    String version();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String browserSize();

    @Key("baseUrl")
    @DefaultValue("https://github.com/")
    String baseUrl();

    @Key("remote")
    String remote();
}
