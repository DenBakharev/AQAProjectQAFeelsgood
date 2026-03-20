package webshop.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

public class JenkinsTest  {

    @Test
    @Tags({@Tag("UI"),@Tag("pos")})
    @DisplayName("UI pos test")
    void jenkinsTest1() {
        System.out.println("UI pos test");

    }
    @Test
    @Tags({@Tag("UI"),@Tag("neg")})
    @DisplayName("UI neg test")
    void jenkinsTest2() {
        System.out.println("UI neg test");
    }
    @Test
    @Tags({@Tag("API"),@Tag("pos")})
    @DisplayName("API pos test")
    void jenkinsTest3() {
        System.out.println("API pos test");
    }

    @Test
    @Tags({@Tag("API"),@Tag("neg")})
    @DisplayName("API neg test")
    void jenkinsTest4() {
        System.out.println("API neg test");
    }

    @Test
    @Tags({@Tag("HZ"),@Tag("poh")})
    void jenkinsTest5() {
        System.out.println("HZ poh test");
    }
}
