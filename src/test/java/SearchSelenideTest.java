import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class SearchSelenideTest {

    @BeforeAll
    static void beforeAll(){
          baseUrl = "https://github.com/";
          pageLoadStrategy ="eager";
          browserSize = "1920x1080";
    }

    @AfterEach
    void closeWebDriver(){
        Selenide.closeWebDriver();
    }

    @Test
    void searchExampleJUnit5() {
        open("selenide/selenide");
        $("#wiki-tab").click();
        $("#wiki-pages-box").$(withTagAndText("button","Show 3 more pages")).click();
        $("#wiki-pages-box").$(withTagAndText("a","SoftAssertions")).click();
        $("#wiki-body > div.markdown-body").shouldHave(text(
                """
                        @ExtendWith({SoftAssertsExtension.class})
                        class Tests {
                        @Test
                        void test() {
                        Configuration.assertionMode = SOFT;
                        open("page.html");

                        $("#first").should(visible).click();
                        $("#second").should(visible).click();
                        }
                        }"""));
    }
}
