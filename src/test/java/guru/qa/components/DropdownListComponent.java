package guru.qa.components;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class DropdownListComponent {

    public void selectState(String state) {
        $("#state").click();
        $("#react-select-3-input").setValue(state).pressEnter();
    }

    public void selectCity(String city) {
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).scrollTo().click();
    }
}
