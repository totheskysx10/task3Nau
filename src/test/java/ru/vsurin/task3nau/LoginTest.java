package ru.vsurin.task3nau;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * E2E-Тест логина и логаута
 */
public class LoginTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeAll
    static void setUp() {
        WebDriverManager.edgedriver().setup();
    }

    @BeforeEach
    void setUpTests() {
        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    /**
     * Тест логина
     */
    @Test
    void testLogin() {
        driver.get("http://localhost:8080/login");

        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("lada");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("vesta");

        WebElement loginButton = driver.findElement(By.xpath("//button[text()='Sign in']"));
        loginButton.click();

        WebElement successHeading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.tagName("h1"))
        );

        Assertions.assertEquals("Вы успешно вошли", successHeading.getText());
    }

    /**
     * Тест логаута
     */
    @Test
    void testLogout() {
        driver.get("http://localhost:8080/login");

        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("lada");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("vesta");

        WebElement loginButton = driver.findElement(By.xpath("//button[text()='Sign in']"));
        loginButton.click();

        WebElement logoutButton = driver.findElement(By.xpath("//button[text()='Выйти']"));
        logoutButton.click();

        WebElement loginFormHeading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.tagName("h2"))
        );

        Assertions.assertEquals("Please sign in", loginFormHeading.getText());
    }
}
