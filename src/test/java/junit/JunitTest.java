package junit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import webshop.BaseTest;

import static org.junit.jupiter.api.Assertions.*;

public class JunitTest extends BaseTest {
    //
//    @BeforeAll
//    static void setUp() {
//        System.out.println("Перед всеми тестами");
//    }
//
//    @BeforeEach
//    void beforeEach() {
//        System.out.println("Перед каждым тестом");
//    }
//
//    @AfterEach
//    void afterEach() {
//        System.out.println("После каждого теста");
//    }
//
//
//    @AfterAll
//    static void afterAll() {
//        System.out.println("После всех тестов");
//    }

    @ParameterizedTest
    @ValueSource(strings = {"Ivan","Petr","Masha"})
    @Tag("positive")
    void paramTest(String name) {
        System.out.println(name);
    }

    @Nested
    public class PositiveTests {


        @Test
        @Tag("positive")
        @DisplayName("Комментарий к тесту человекочитаемый")
        public void test1() {
            System.out.println("Test1");
            assertAll(
                    () -> assertTrue(2 > 1),
                    () -> assertFalse(1 > 2),
                    () -> assertEquals(2, 2),
                    () -> assertNotEquals(1, 2)
            );

        }

        @Test
        @Timeout(5)
        @Tag("positive")
        public void test2() {
            System.out.println("Test2");
        }
    }

    @Nested
    public class NegativeTests {

        @Test
        @Tag("negative")
        @Disabled("Отключен из-за бага 123")
        public void test1() {
            System.out.println("Test1");
        }

        @Test
        @Tag("negative")
        public void test2() {
            System.out.println("Test2");
        }
    }

}
