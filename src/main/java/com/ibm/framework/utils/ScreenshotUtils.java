package com.ibm.framework.utils;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.ibm.framework.driver.DriverFactory;

public final class ScreenshotUtils {

    private ScreenshotUtils() {}

    /**
     * Captures screenshot and returns file path
     */
public static String takeScreenshot(String testName) {

    String timestamp =
            new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

    String screenshotDir =
            System.getProperty("user.dir") + "/target/screenshots/";

    String screenshotName =
            testName + "_" + timestamp + ".png";

    String absolutePath = screenshotDir + screenshotName;

    try {
        File src =
                ((TakesScreenshot) DriverFactory.getDriver())
                        .getScreenshotAs(OutputType.FILE);

        File dest = new File(absolutePath);
        dest.getParentFile().mkdirs();
        Files.copy(src.toPath(), dest.toPath());

    } catch (Exception e) {
        e.printStackTrace();
    }
    return "../screenshots/" + screenshotName;
}

}
