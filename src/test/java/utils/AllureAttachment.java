package utils;

//import java.nio.charset.StandardCharsets;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.commons.lang3.StringUtils;
import java.util.stream.Collectors;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import io.qameta.allure.Attachment;

public class AllureAttachment {

	@Attachment(value = "{0}", type = "text/plain", fileExtension = ".txt")
	public static String attachText(String message) {
		return message;
	}

	@Attachment(value = "Page Screenshot", type = "image/png", fileExtension = ".png")
	static byte[] attachScreenshot(WebDriver webDriverAttribute) {
		return ((TakesScreenshot) webDriverAttribute).getScreenshotAs(OutputType.BYTES);
	}

	//@Attachment(value = "Html source", type = "text/html", fileExtension = ".html")
	//public static byte[] getPageSource(WebDriver driver) {
		//return driver.getPageSource().getBytes(StandardCharsets.UTF_8);
	//}

	@Attachment(value = "Console Logs", type = "text/plain", fileExtension = ".txt")
	public static String attachConsoleLogs(WebDriver driver) {
		String consoleLogs = driver.manage().logs().get(LogType.BROWSER).getAll().stream().map(LogEntry::toString)
				.collect(Collectors.joining(System.lineSeparator()));
		return StringUtils.isBlank(consoleLogs) ? "No Console Logs Found" : consoleLogs;
	}
	
	@Attachment(value = "Element Screenshot", type = "image/png", fileExtension = ".png")
	public static byte[] attachElementScreenshot(WebElement element) {
		return element.getScreenshotAs(OutputType.BYTES);
	}

}
