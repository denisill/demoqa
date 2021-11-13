package guru.qa.tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import static guru.qa.tests.TestData.*;

public class RegistrationFormWithFakerTests extends TestBase {

    Faker faker = new Faker();
    String firstName = faker.name().firstName();
    String lastname = faker.name().lastName();
    String email = faker.internet().emailAddress();
    String mobileNumber = faker.phoneNumber().subscriberNumber(10);
    String address = faker.address().streetAddress();

    @Test
    void registrationTest() {

        registrationsPage.openPage()
                .typeFirstName(firstName)
                .typeLastName(lastname)
                .typeEmailAddress(email)
                .selectGender(selectGender)
                .typeMobileNumber(mobileNumber);

        registrationsPage.calendar.setDate(day, month, year);

        registrationsPage.typeSubject(subject)
                .selectHobbies(selectHobbiesReading)
                .selectHobbies(selectHobbiesMusic)
                .uploadFile(uploadFile)
                .typeAddress(address);

        registrationsPage.dropdownList.selectState(state);
        registrationsPage.dropdownList.selectCity(city);
        registrationsPage.clickSubmit();

        //Проверка данных
        registrationsPage.checkResultsTitle();
        registrationsPage.checkResultsValue("Student Name", firstName + " " + lastname)
                .checkResultsValue("Student Email", email)
                .checkResultsValue("Gender", gender)
                .checkResultsValue("Mobile", mobileNumber)
                .checkResultsValue("Date of Birth", day + " " + month + "," + year)
                .checkResultsValue("Subjects", subject)
                .checkResultsValue("Hobbies", hobbiesReading + ", " + hobbiesMusic)
                .checkResultsValue("Picture", file)
                .checkResultsValue("Address", address)
                .checkResultsValue("State and City", state + " " + city);
    }
}
