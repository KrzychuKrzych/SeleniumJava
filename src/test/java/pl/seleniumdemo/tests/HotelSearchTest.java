package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.tests.BaseTest;

import java.util.List;

public class HotelSearchTest extends BaseTest {

    @Test
    public void searchHotelTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setDates("25/04/2021", "30/04/2021");
        hotelSearchPage.setTravellers();
        hotelSearchPage.performSearch();

        List<String> hotelNames = driver.findElements(By.xpath("//h4[contains(@class,'list_title')]//b"))
                .stream()
                .map(WebElement::getText).toList();

        /*
        System.out.println(hotelNames.size());
        hotelNames.forEach(el -> System.out.println(el));
        */

        Assert.assertEquals(hotelNames.get(0),"Jumeirah Beach Hotel");
        Assert.assertEquals(hotelNames.get(1),"Oasis Beach Tower");
        Assert.assertEquals(hotelNames.get(2),"Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3),"Hyatt Regency Perth");
    }

    //praca domowa - asercja
    @Test
    public void searchHotelWithoutNameTest() {
        driver.findElement(By.name("checkin")).sendKeys("04/05/2022");
        driver.findElement(By.name("checkout")).sendKeys("14/05/2022");
        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("adultMinusBtn")).click();
        driver.findElement(By.id("childPlusBtn")).click();
        driver.findElement(By.xpath("//button[text()=' Search']")).click();

        WebElement alert = driver.findElement(By.xpath("//h2[@class='text-center']"));

        Assert.assertTrue(alert.isDisplayed());
        Assert.assertEquals(alert.getText(), "No Results Found");
    }
}
