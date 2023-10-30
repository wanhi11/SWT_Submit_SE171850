/* “Verify that you cannot add more product in cart than the product available in store”

Test Steps:

1. Go to http://live.techpanda.org/

2. Click on �MOBILE� menu

3. In the list of all mobile , click on �ADD TO CART� for Sony Xperia mobile

4. Change �QTY� value to 1000 and click �UPDATE� button. Expected that an error is displayed

"The requested quantity for "Sony Xperia" is not available.

5. Verify the error message

6. Then click on �EMPTY CART� link in the footer of list of all mobiles. A message "SHOPPING CART IS EMPTY" is shown.

7. Verify cart is empty

 */
package homework;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class TestCase03 {

    @Test
    public static void TestCase03(){
        // Init WebDriver
        WebDriver driver =  driverFactory.getChromeDriver();
        try {
            // TC03: Verify that you cannot add more product in cart than the product available in store
            // Step 1: Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            // Step 2: Click on "MOBILE" menu
            WebElement mobileMenu = driver.findElement(By.xpath("//a[normalize-space()='Mobile']"));
            mobileMenu.click();

            // Step 3: Click on "ADD TO CART" for Sony Xperia mobile
            WebElement sonyXperiaAddToCart = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[3]/ul[1]/li[2]/div[1]/div[3]/button[1]/span[1]/span[1]"));
            sonyXperiaAddToCart.click();

            // Step 4: Change "QTY" value to 1000 and click "UPDATE" button. Verify the error message
            WebElement qtyInput = driver.findElement(By.xpath("//input[@title='Qty']"));
            qtyInput.clear();
            qtyInput.sendKeys("1000");
            WebElement updateButton = driver.findElement(By.xpath("//button[@title='Update']//span//span[contains(text(),'Update')]"));
            updateButton.click();
            WebElement errorMessage = driver.findElement(By.xpath("//p[@class='item-msg error']"));
//            Assert.assertTrue(errorMessage.isDisplayed());

            // Step 5: Verify the error message
            Assert.assertEquals(errorMessage.getText(),"* The maximum quantity allowed for purchase is 500.");

            // Step 6: Click on "EMPTY CART" link
            WebElement emptyCartLink = driver.findElement(By.xpath("//span[contains(text(),'Empty Cart')]"));
            emptyCartLink.click();
            WebElement emptyCartMessage = driver.findElement(By.xpath("//h1[normalize-space()='Shopping Cart is Empty']"));
            //Assert.assertTrue(emptyCartMessage.isDisplayed());

            // Step 7: Verify cart is empty
            Assert.assertEquals(emptyCartMessage.getText(),"SHOPPING CART IS EMPTY");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed due to exception: " + e.getMessage());
        } finally {
            // quit driver session
            driver.quit();
        }
    }
}