package test;
import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
public class testTestCase02 {
    @Test
    public static void testTC02() {

        // Init web driver session;
        int scc = 0;
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            // Step 1: Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");
            // debug purpose only
            Thread.sleep(2000);

            // Step 2: Click on Mobile menu
            driver.findElement(By.linkText("MOBILE")).click();
            // debug purpose only
            Thread.sleep(2000);

            // Step 3: In the list of all mobile , select SORT BY -> dropdown as name
            new Select(driver.findElement(By.cssSelector("select[title=\"Sort By\"]"))).selectByVisibleText("Price");
            WebElement sonyElement = driver.findElement(By.cssSelector("span[id='product-price-1'] span[class='price']"));
            double priceSony = Double.parseDouble(sonyElement.getText().substring(1));
            // debug purpose only
            Thread.sleep(2000);

            // Step 4: Click on Sony Xperia mobile
            driver.findElement(By.cssSelector("h2[class='product-name'] a[title='Sony Xperia']")).click();
            // debug purpose only
            Thread.sleep(2000);

            // Step 5: Read the Sony Xperia mobile from detail page.
            WebElement sonyDetailElement = driver.findElement(By.cssSelector(".price"));
            double priceSonyDetail = Double.parseDouble(sonyDetailElement.getText().substring(1));
            // debug purpose only
            Thread.sleep(2000);

            // Step 6: Compare Product value in list and details page should be equal ($100).
            System.out.println("priceSony: " + priceSony);
            System.out.println("priceSonyDetail: " + priceSonyDetail);
            AssertJUnit.assertEquals(priceSony, priceSonyDetail);
            // debug purpose only
            Thread.sleep(2000);

            scc = scc + 1;
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

            String png = ("D:\\FPT\\Kì 5\\SWT\\selenium-webdriver\\selenium-webdriver-java-master\\src\\test\\java\\BAITAP\\TestCase0" +
                    scc + ".java");
            FileUtils.copyFile(scrFile, new File(png));
        }catch (Error e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            driver.quit();
        }
    }
}
