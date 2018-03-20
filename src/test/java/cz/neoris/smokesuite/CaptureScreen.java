package cz.neoris.smokesuite;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

import java.io.File;
import java.io.IOException;

public class CaptureScreen {

    public static String main() {
        String path;
        WebDriver driver = null;

        try {
            WebDriver augdriver = new Augmenter().augment(driver);
            File source = ((TakesScreenshot)augdriver).getScreenshotAs(OutputType.FILE);
            path = "C:\\Users\\jan.svehlak\\code\\SmokeSuite\\src\\test\\java\\cz\\neoris\\smokesuite\\screenshots" + source.getName();
            FileUtils.copyFile(source, new File(path));
        } catch (IOException e) {
            path = "Fail taking screenshot" + e;
        }

        return path;
    }

}
