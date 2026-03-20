package webshop.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "system:properties",
        "classpath:config/${run}.properties"
})
@Config.LoadPolicy(Config.LoadType.MERGE)
public interface WebDriverConfig extends Config {
    @DefaultValue("local")
    String run();

    @DefaultValue("edge")
    String browser();

    @DefaultValue("1920x1080")
    String browserSize();

    String browserVersion();

    String selenoidUrl();

    String selenoidUser();

    String selenoidPassword();

    Boolean enableVideo();

    Boolean enableVNC();
}