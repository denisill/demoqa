package guru.qa.book;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ParameterizedTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.startMaximized = true;
    }

    @CsvSource(value = {
            "test@| Введите корректный Email",
            "почта| В Email не допускаются русские буквы"
    }, delimiter = '|')
    @Tag("Blocker")
    @DisplayName("Заполнение электронной почты при регистарции")
    @ParameterizedTest(name = "Ввод {0} в поле email при регистрации и проверка отображения текста: {1}")
    void inputEmailAddress(String email, String expectedResult) {
        open("https://book24.ru/");
        $(byText("Регистрация")).click();
        $("[inputmode=email]").setValue(email);
        $(byText(expectedResult)).shouldBe(Condition.visible);
    }

    static Stream<Arguments> inputUserName() {
        return Stream.of(
                Arguments.of("Иванов Иван1", "Допускаются только символы A-Z, a-z, А-Я, а-я, -, каждая часть имени должна быть длиннее 1 символа"),
                Arguments.of("Иван", "Нужно указать фамилию и имя")
        );
    }

    @MethodSource
    @Tag("Blocker")
    @DisplayName("Заполнение поля ФИО при регистарции")
    @ParameterizedTest(name = "Ввод {0} в поле ФИО при регистрации и проверка отображения текста: {1}")
    void inputUserName(String userName, String expectedResult) {
        open("https://book24.ru/");
        $(byText("Регистрация")).click();
        $("[name=userName]").setValue(userName);
        $(byText(expectedResult)).shouldBe(Condition.visible);
    }
}
