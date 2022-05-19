package lab5.pages;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import net.serenitybdd.core.pages.WebElementFacade;
import java.util.stream.Collectors;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;

import java.util.List;

@DefaultUrl("https://www.pdfdrive.com")
public class DictionaryPage extends PageObject {
    @FindBy(id="q")
    private WebElementFacade searchInput;

    @FindBy(id="search-form")
    private WebElementFacade searchForm;

    @FindBy(id="inputIdentity")
    private WebElementFacade emailInput;

    @FindBy(id="inputPassword")
    private WebElementFacade passwordInput;

    public DictionaryPage SearchBook(String bookName) {
        searchInput.type(bookName);
        searchForm.submit();
        return this;
    }

    public List<String> GetBooksList() {
        WebElementFacade definitionList = find(By.cssSelector("div.file-right"));
        return definitionList.findElements(By.tagName("h2")).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public String GetResultsCount() {
        return find(By.id("result-found")).find(By.tagName("strong")).getText();
    }

    public DictionaryPage LoginForm() {
        find(By.cssSelector("div.main-content")).find(By.tagName("form")).submit();
        return this;
    }

    public DictionaryPage EnterEmailAddress(String email) {
        emailInput.type(email);
        return this;
    }

    public DictionaryPage EnterPassword(String password) {
        passwordInput.type(password);
        return this;
    }

    public DictionaryPage ClickOnSignIn() {
        find(By.cssSelector("a.btn-g.hidemobile")).click();
        return this;
    }

    public DictionaryPage SignIn(String email ,String password) {
        this.ClickOnSignIn()
                .EnterEmailAddress(email)
                .EnterPassword(password)
                .LoginForm();
        return this;
    }

    public DictionaryPage SignOut() {
        find(By.cssSelector("div.btn-group")).find(By.cssSelector("button.dropdown-toggle")).click();
        getDriver().findElements(By.cssSelector("a.dropdown-item")).get(5).click();
        return this;
    }

    public DictionaryPage ChangePassword(String newPassword) {
        find(By.cssSelector("div.btn-group")).find(By.cssSelector("button.dropdown-toggle")).click();
        getDriver().findElements(By.cssSelector("a.dropdown-item")).get(3).click();
        find(By.id("inputPassword")).type(newPassword);
        find(By.id("inputPasswordConfirm")).type(newPassword);
        find(By.cssSelector("form.form-signin")).submit();
        return this;
    }

    public boolean LoginSuccessfully() {
        return getDriver().findElements(By.cssSelector("div.files-new")).size() != 0;
    }

    public boolean LoginFailed() {
        return getDriver().findElements(By.cssSelector("div.alert.alert-danger")).size() != 0;
    }

    public boolean ChangePasswordSuccessful(String email, String newPassword) {
        return this.SignIn(email, "P1a2s3s4!")
                .ChangePassword(newPassword)
                .SignOut()
                .SignIn("nafejaw954@cupbest.com", newPassword)
                .LoginSuccessfully();
    }



    public boolean ChangePasswordFailed(String email, String newPassword) {
        return this.SignIn(email, "P1a2s3s4!")
                .ChangePassword(newPassword)
                .SignOut()
                .SignIn("kocebef767@roxoas.com", "P1a2s3s4!")
                .LoginFailed();
    }

    public void RedoPassword(String email, String password, boolean signOut) {
        if(signOut)
            this.SignOut();
        this.SignIn(email, password)
                .ChangePassword("P1a2s3s4!");
    }
}