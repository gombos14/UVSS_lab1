package lab5.features.search;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import lab5.steps.serenity.EndUserSteps;

@RunWith(SerenityRunner.class)
public class SearchByKeywordStory {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public EndUserSteps steps;


    @Test
    public void searchExistingBook() {
        steps.SearchBook("Selenium");
        steps.ShouldSeeBook("Selenium Testing Tools Cookbook, 2nd Edition: Over 90 recipes to help you build and run automated tests for your web applications with Selenium WebDriver");
    }

    @Test
    public void searchInexistingBook() {
        steps.SearchBook("asfgasgaslkgasjghasjgsajgasgalsgsal");
        steps.ShouldSeeNoBook();
    }

    @Test
    public void TestLoginSuccessful() {
        Assert.assertTrue(steps.TestLoginSuccessful());
    }

    @Test
    public void TestLoginFailed() {
        Assert.assertTrue(steps.TestLoginFailed());
    }

    @Test
    public void TestChangePasswordSuccessful() {
        String email = "nafejaw954@cupbest.com";
        String newPassword = "someNewPassword";
        Assert.assertTrue(steps.TestChangePasswordSuccessful(email, newPassword));
        steps.RedoPassword(email, newPassword, true);
    }

    @Test
    public void TestChangePasswordFailed() {
        String email = "kocebef767@roxoas.com";
        String newPassword = "someNewPassword";
        Assert.assertTrue(steps.TestChangePasswordFailed(email, newPassword));
        webdriver.navigate().refresh();
        steps.RedoPassword(email, newPassword, false);
    }
}