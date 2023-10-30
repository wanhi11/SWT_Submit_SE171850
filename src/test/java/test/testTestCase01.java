package test;
import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
public class testTestCase01 {
    @Test
    public static void testTC01()
    {
        int scc= 0;
        StringBuffer verificationErrors = new StringBuffer();

        //Initweb-driver session
        WebDriver driver = driverFactory.getChromeDriver();

        try
        {
            //1. Goto http://live.techpanda.org/
            driver.get("http://live.techpanda.org");
            //Step 2. Verify Title of the page
            String demoSite = driver.findElement(By.cssSelector("h2")).getText();
            System.out.printf(demoSite);

            try{
                AssertJUnit.assertEquals("THIS IS DEMO SITE FOR ",demoSite);
            }catch(Error e)
            {
                verificationErrors.append(e.toString());
            }
            //debug purpose only
            Thread.sleep(4000);

            //Step 3. Click on -> MOBILE -> menu
            driver.findElement(By.linkText("MOBILE")).click();
            Thread.sleep(4000);
            //Step 4. In the list of all mobile , select SORT BY -> dropdown as name
            new Select(driver.findElement(By.cssSelector("select[title=\"Sort By\"]"))).selectByVisibleText("Name");
            Thread.sleep(4000);
            //Step 5. Verify all products are sorted by name
            // this will takea screen shot of the manager's page after a successful login
            scc = scc+1;
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

            String png = ("C:\\Users\\HP\\Desktop\\selenium-webdriver-java-master\\src\\test\\java\\BAITAP\\TestCase0"+scc+".java");
            FileUtils.copyFile(scrFile,new File(png));



        }catch(Error e)
        {


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
