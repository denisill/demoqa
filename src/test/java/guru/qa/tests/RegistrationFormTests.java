package guru.qa.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationFormTests extends TestBase {

    @BeforeEach
    void start() {
        open("https://demoqa.com/automation-practice-form");
    }

    @Test
    void registrationTest() {

        $("#firstName").setValue("Denis");
        $("#lastName").setValue("Illarionov");
        $("#userEmail").setValue("123@mt.com");
        $("[for='gender-radio-2']").click();
        $("#userNumber").setValue("1234567890");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("May");
        $(".react-datepicker__year-select").selectOption("2013");
        $(".react-datepicker__day--028:not(.react-datepicker__day--outside-month)").click();

        $("#subjectsInput").setValue("Math").pressEnter();
        $("[for=hobbies-checkbox-2]").click();
        $("#hobbiesWrapper").$(byText("Music")).click();
        $("#uploadPicture").uploadFromClasspath("img/file1.png");

        $("#currentAddress").setValue("someAddress");
        $("#state").click();
        $("#react-select-3-input").setValue("Rajasthan").pressEnter();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Jaiselmer")).scrollTo().click();
        $("#submit").click();

        //Проверка данных
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Denis Illarionov"),
                text("123@mt.com"),
                text("Female"),
                text("1234567890"),
                text("28 May,2013"),
                text("Maths"),
                text("Reading, Music"),
                text("file1.png"),
                text("someAddress"),
                text("Rajasthan Jaiselmer"));
    }
}