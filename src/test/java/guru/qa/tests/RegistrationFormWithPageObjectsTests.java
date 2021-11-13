package guru.qa.tests;

import org.junit.jupiter.api.Test;

public class RegistrationFormWithPageObjectsTests extends TestBase {

    @Test
    void registrationTest() {

        registrationsPage.openPage()
                .typeFirstName("Denis")
                .typeLastName("Illarionov")
                .typeEmailAddress("123@mail.ru")
                .selectGender("2")
                .typeMobileNumber("1234567890");

        registrationsPage.calendar.setDate("28", "May", "2013");

        registrationsPage.typeSubject("Math")
                .selectHobbies("2")
                .selectHobbies("3")
                .uploadFile("img/file1.png")
                .typeAddress("someAddress");

        registrationsPage.dropdownList.selectState("Rajasthan");
        registrationsPage.dropdownList.selectCity("Jaiselmer");
        registrationsPage.clickSubmit();

        //Проверка данных
        registrationsPage.checkResultsTitle();
        registrationsPage.checkResultsValue("Student Name", "Denis Illarionov")
                .checkResultsValue("Student Email", "123@mail.ru")
                .checkResultsValue("Gender", "Female")
                .checkResultsValue("Mobile", "1234567890")
                .checkResultsValue("Date of Birth", "28 May,2013")
                .checkResultsValue("Subjects", "Maths")
                .checkResultsValue("Hobbies", "Reading, Music")
                .checkResultsValue("Picture", "file1.png")
                .checkResultsValue("Address", "someAddress")
                .checkResultsValue("State and City", "Rajasthan Jaiselmer");
    }
}
