package lab5.steps.serenity;

import lab5.pages.DictionaryPage;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

public class EndUserSteps {

    DictionaryPage DictionaryPage;

    @Step
    public void SearchBook(String bookName) {
        this.OpenLink()
                .SearchBook(bookName);
    }

    @Step
    public void ShouldSeeBook(String bookName) {
        assertThat(DictionaryPage.GetBooksList(), hasItem(containsString(bookName)));
    }

    @Step
    public void ShouldSeeNoBook() {
        Assert.assertEquals(DictionaryPage.GetResultsCount(), "0");
    }

    @Step
    public DictionaryPage OpenLink() {
        DictionaryPage.open();
        return this.DictionaryPage;
    }

    @Step
    public boolean TestLoginSuccessful() {
        return this.OpenLink()
                .ClickOnSignIn()
                .EnterEmailAddress("hidona7372@doerma.com")
                .EnterPassword("P1a2s3s4!")
                .LoginForm()
                .LoginSuccessfully();
    }

    @Step
    public boolean TestLoginFailed() {
        return this.OpenLink()
                .ClickOnSignIn()
                .EnterEmailAddress("random_email_address@gmail.com")
                .EnterPassword("random_password")
                .LoginForm()
                .LoginFailed();
    }


    @Step
    public boolean TestChangePasswordSuccessful(String email, String newPassword) {
        return this.OpenLink()
                .ChangePasswordSuccessful(email, newPassword);
    }

    @Step
    public boolean TestChangePasswordFailed(String email, String newPassword) {
        return this.OpenLink()
                .ChangePasswordFailed(email, newPassword);
    }

    @Step
    public void RedoPassword(String email, String password, boolean signOut) {
        DictionaryPage.RedoPassword(email, password, signOut);
    }
}