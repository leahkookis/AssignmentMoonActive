package pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;

public class BasePage {
	WebDriver driver;
	JavascriptExecutor js;
	Actions action;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;
		action = new Actions(driver);
	}
	
	
	public WebDriver getDriver() {
		return driver;
	}


	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}


	@Step("fill text: {1}")
	public void fillText(WebElement el, String text) {
		highlightElement(el, "green");
		el.clear();
		el.sendKeys(text);
	}
	@Step("click on element {0}")
	public void click(WebElement el) {
		highlightElement(el, "blue");
		el.click();
	}
	@Step("get text from element {0}")
	public String getText(WebElement el) {
		highlightElement(el, "green");
		return el.getText();
	}
	@Step("sleep {0}")
	public void sleep(long mills) {
		try {
			Thread.sleep(mills);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Step("over to element {0}")
	public void moveto(WebElement el) {
		highlightElement(el, "orange");
		action.moveToElement(el).build().perform();
	}
	
	@Step("over to element {0}")
	public void MoveToAndClick(WebElement el) {
		highlightElement(el, "orange");
		action.moveToElement(el).click().build().perform();
	}

	public void highlightElement(WebElement element, String color) {
		// keep the old style to change it back
		String originalStyle = element.getAttribute("style");
		String newStyle = "background-color:yellow;border: 1px solid " + color + ";" + originalStyle;
		js = (JavascriptExecutor) driver;

		// Change the style
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
				+ newStyle + "');},0);", element);

		// Change the style back after few miliseconds
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
				+ originalStyle + "');},400);", element);

	}



	
	public void DoubleClick(WebElement el) {
		
		action.doubleClick(el).build().perform();
	}

}
