package guru.qa.pages;


import com.codeborne.selenide.SelenideElement;
import guru.qa.components.CalendarComponent;
import guru.qa.components.DropdownListComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.format;

public class RegistrationsPage {

    //locators & elements
    private final static String RESULTS_TITLE = "Thanks for submitting the form";

    private SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailAddressInput = $("#userEmail"),
            mobileNumberInput = $("#userNumber"),
            subjectsInput = $("#subjectsInput"),
            uploadFile = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            submit = $("#submit"),
            tableResults = $(".table-responsive");
    public CalendarComponent calendar = new CalendarComponent();
    public DropdownListComponent dropdownList = new DropdownListComponent();

    //actions
    public RegistrationsPage openPage() {
        open("https://demoqa.com/automation-practice-form");
        return this;
    }

    public RegistrationsPage typeFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    public RegistrationsPage typeLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public RegistrationsPage typeEmailAddress(String email) {
        emailAddressInput.setValue(email);
        return this;
    }

    public RegistrationsPage selectGender(String gender) {
        $(format("[for='gender-radio-%s']", gender)).click();
        return this;
    }

    public RegistrationsPage typeMobileNumber(String mobileNumber) {
        mobileNumberInput.setValue(mobileNumber);
        return this;
    }

    public RegistrationsPage typeSubject(String subject) {
        subjectsInput.setValue(subject).pressEnter();
        return this;
    }

    public RegistrationsPage selectHobbies(String hobbies) {
        $(format("[for=hobbies-checkbox-%s]", hobbies)).click();
        return this;
    }

    public RegistrationsPage uploadFile(String fileName) {
        uploadFile.uploadFromClasspath(fileName);
        return this;
    }

    public RegistrationsPage typeAddress(String address) {
        addressInput.setValue(address);
        return this;
    }

    public RegistrationsPage clickSubmit() {
        submit.click();
        return this;
    }

    public RegistrationsPage checkResultsTitle() {
        $("#example-modal-sizes-title-lg").shouldHave(text(RESULTS_TITLE));
        return this;
    }

    public RegistrationsPage checkResultsValue(String key, String value) {
        tableResults.$(byText(key))
                .parent().shouldHave(text(value));
        return this;
    }
}
