package guru.qa.allure;

import guru.qa.allure.steps.WebSteps;
import org.junit.jupiter.api.Test;

public class StepAnnotatedTest extends TestBase {

    private static final String REPOSITORY = "denisill/demoqa";
    private static final Integer ISSUE_NUMBER = 1;

    private WebSteps webSteps = new WebSteps();

    @Test
    public void testGithub() {
        webSteps.openMainPage();
        webSteps.searchForRepository(REPOSITORY);
        webSteps.goToRepository(REPOSITORY);
        webSteps.openIssueTab();
        webSteps.shouldSeeIssueWithNumber(ISSUE_NUMBER);
    }
}
