package framework.helpers;

import com.github.javafaker.Faker;
import framework.pages.BasePage;
import io.qameta.allure.Attachment;
import java.io.File;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Helpers {

    @SneakyThrows
    public static void makeScreenShot() {
        File scrFile = ((TakesScreenshot) BasePage.getDriver()).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile,
                new File("/Users/user/Documents/Java project/final_project/src/test/resources/screenshots"
                        + new Faker().random().hex(10) + ".png"));
    }


    @Attachment(value = "{fileName}", type = "image/png")
    public static byte[] takeScreenShot(String fileName) {
        return ((TakesScreenshot) BasePage.getDriver()).getScreenshotAs(OutputType.BYTES);
    }


}
