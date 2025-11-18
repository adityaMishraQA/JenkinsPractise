package org.project;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.*;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class WebSiteAutomate {

    public static WebDriver driver;
    public static InputStream inputStream;
    public static Properties property=new Properties();


    static {
        try {
            inputStream = new FileInputStream(new File(System.getProperty("user.dir"))+"\\src\\main\\java\\config\\config.properties");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    @BeforeMethod
    public void preSetup() throws IOException {
        property.load(inputStream);

        if(property.getProperty("browser").equalsIgnoreCase("Chrome"))
        {
            WebDriverManager.chromedriver().setup();
            driver=new ChromeDriver();
        }
        else
        {
            WebDriverManager.edgedriver().setup();
            driver=new EdgeDriver();
        }
        driver.manage().window().maximize();

        driver.get(property.getProperty("url"));

    }

    @Test
    public void testPracticePage() throws InterruptedException {
        //CheckBox Validation
        driver.findElement(By.xpath("//input[@value=\"radio2\"]")).click();
        driver.findElement(By.id("autocomplete")).sendKeys("India"+Keys.SPACE);
        WebElement elementSelect=driver.findElement(By.id("dropdown-class-example"));
        Select select=new Select(elementSelect);
        select.selectByValue("option2");
        driver.findElement(By.id("checkBoxOption2")).click();

        //New Windows and new Tab validation
        driver.findElement(By.id("openwindow")).click();
        Set<String> handles=driver.getWindowHandles();
        Iterator<String> handleSelect=handles.iterator();
        String parent=handleSelect.next();
        String child=handleSelect.next();
        driver.switchTo().window(child);
        driver.findElement(By.xpath("//a[text()='Access all our Courses']")).click();
        driver.close();
        driver.switchTo().window(parent);

        driver.findElement(By.id("opentab")).click();
        Set<String> handles1=driver.getWindowHandles();
        Iterator<String> handleSwitch=handles1.iterator();
        String parent1=handleSwitch.next();
        String child1=handleSwitch.next();
        driver.switchTo().window(child1);
        driver.findElement(By.xpath("//li[@class='nav-item']/a[text()='Blog']")).click();
        driver.close();
        driver.switchTo().window(parent1);

        //Alert Functionality check
        driver.findElement(By.id("name")).sendKeys("Aditya");
        driver.findElement(By.id("alertbtn")).click();
        Alert alert=driver.switchTo().alert();
        alert.accept();
        driver.findElement(By.id("name")).sendKeys("Aditya");
        driver.findElement(By.id("confirmbtn")).click();
        alert.accept();

        //Scrolling down the page to view the UI getting selected as element
        Actions action=new Actions(driver);
        WebElement element=driver.findElement(By.xpath("//div[text()=' Total Amount Collected: 296 ']"));
        action.scrollToElement(element).perform();

        //Printing the Table elements---
        List<WebElement> elements=driver.findElements(By.xpath("//tbody/tr"));
        for (int i=0;i<11;i++)
        {
            if (i==0)
            {
                System.out.println("__________________________________________________________________________________________________________");
                System.out.println(elements.get(i).getText());
                System.out.println("---------------------------------------------------------------------------------------------------------");
            }
            else {
                System.out.println(elements.get(i).getText());
            }
        }

        //Element displayed box validation
        driver.findElement(By.id("displayed-text")).sendKeys("Ben");
        driver.findElement(By.id("hide-textbox")).click();
        driver.findElement(By.id("show-textbox")).click();

        //Mouse hover functionality
        WebElement framElement=driver.findElement(By.xpath("//legend[text()='iFrame Example']"));
        action.scrollToElement(framElement).perform();
        WebElement mouseHover=driver.findElement(By.id("mousehover"));
        action.moveToElement(mouseHover).perform();
        driver.findElement(By.xpath("//a[text()='Reload']")).click();

        //taking Screen shot of final i frame Finally
    }

    @AfterMethod
    public void closingSetup()
    {
        driver.close();
    }
}

