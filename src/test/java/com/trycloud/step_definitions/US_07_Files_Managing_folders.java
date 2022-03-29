package com.trycloud.step_definitions;

import com.trycloud.pages.FilePage;
import com.trycloud.pages.UploadFilesPage;
import com.trycloud.utilities.BrowserUtils;
import com.trycloud.utilities.ConfigurationReader;
import com.trycloud.utilities.Driver;
import com.trycloud.utilities.TryCloudUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class US_07_Files_Managing_folders {

    FilePage filePage = new FilePage();
    UploadFilesPage uploadFilesPage = new UploadFilesPage();

    @When("the user write a {string} to folder name")
    public void the_user_write_a_folder_name(String folderName) {
        BrowserUtils.highlight(filePage.newFolderInput);
        String customFolder = folderName+Driver.getDriver();
        filePage.newFolderInput.sendKeys(customFolder);
    }

    @When("the user click submit icon")
    public void the_user_click_submit_icon() {
        BrowserUtils.highlight(filePage.submitFolderNameBtn);
        filePage.submitFolderNameBtn.click();
    }

    @When("user click the {string} top-module")
    public void user_click_the_top_module(String module) {
        WebElement topModule = Driver.getDriver().findElement(By.xpath("//div[@class='newFileMenu popovermenu bubble menu open menu-left']//*[normalize-space(.)='"+module+"']"));
        BrowserUtils.highlight(topModule);
        topModule.click();
    }

    @Then("Verify the {string} folder is displayed on the page")
    public void verify_the_folder_is_displayed_on_the_page(String folder) {
        String customFolder = folder+Driver.getDriver();
        WebElement folderName = Driver.getDriver().findElement(By.xpath("//span[@class='innernametext' and .='" + customFolder + "']"));
        BrowserUtils.highlight(folderName);
        Assert.assertTrue(folderName.isDisplayed());

        // Remove created folder
        WebElement actionsForUploaded = Driver.getDriver().findElement(By.xpath("(//span[@class='innernametext' and .='" + customFolder + "']/../..//a[2])[1]"));
        BrowserUtils.highlight(actionsForUploaded);
        actionsForUploaded.click();
        BrowserUtils.sleep(0.2);
        FilePage.chooseOption("Delete");
    }

    @When("the user choose a {string} folder from the page")
    public void the_user_choose_a_folder_from_the_page(String folder) {
        WebElement folderName = Driver.getDriver().findElement(By.xpath("//span[@class='innernametext' and .='" + folder + "']"));
        BrowserUtils.highlight(folderName);
        folderName.click();
    }

    @When("user uploads file2 with the upload file option")
    public void user_uploads_file_with_the_upload_file_option() {
        String filePath = "D:/Uploads/TryCloud.txt";
        BrowserUtils.sleep(2);
        BrowserUtils.waitForPageToLoad(ConfigurationReader.getNumber("timeout"));
        filePage.upload.sendKeys(filePath);
        TryCloudUtils.waitTillUploadBarDisappears();
    }


    @Then("Verify the file2 is displayed on the page")
    public void verify_the_file_is_displayed_on_the_page(){
        BrowserUtils.highlight(uploadFilesPage.file2Name);
        Assert.assertTrue(uploadFilesPage.file2Name.isDisplayed());

        // Remove uploaded file
        BrowserUtils.highlight(uploadFilesPage.file2row);
        uploadFilesPage.file2row.click();
        BrowserUtils.highlight(filePage.optionDelete);
        filePage.optionDelete.click();
    }
}
