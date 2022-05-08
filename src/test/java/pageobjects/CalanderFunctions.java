package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class CalanderFunctions extends BasePage {
	@FindBy(css = ".header2-text-label")
	WebElement DateBTN;
	@FindBy(className = "ant-fullcalendar-today")
	WebElement TodayDateBTN;
	@FindBy(linkText = "Infinite scroll")
	WebElement InfiniteScroll;
	@FindBy(className = "anticon-right")
	WebElement ArrowRightDateBTN;
	@FindBy(className = "anticon-left")
	WebElement ArrowLeftDateBTN;
	@FindBy(css = ".ant-radio-group-default label:nth-child(3)")
	WebElement MonthBtn;
	@FindBy(css = ".scheduler-bg tr:nth-child(2) td")
	List<WebElement> CreateNewEvent;
	@FindBy(css = ".event-item")
	List<WebElement> Event;

	public CalanderFunctions(WebDriver driver) {
		super(driver);
	}

	@Step("click on month button than view change")
	public void SwitchToMonthView() {
		click(MonthBtn);
	}

	@Step("click on Infinite scroll button than view change")
	public void SwitchToInfiniteScrollView() {
		click(InfiniteScroll);
	}

	@Step("create new event")
	public void CreateNewEvent(int index) {
		MoveToAndClick(CreateNewEvent.get(index));
		driver.switchTo().alert().accept();

	}

	@Step("get Dom element count")
	public int GetCountDomElement() {
		return driver.findElements(By.xpath("//*")).size();
	}

	@Step("go to next month")
	public void GoToNextMonth() {
		click(ArrowRightDateBTN);
	}

	@Step("go to previous month")
	public void GoToPreviousMonth() {
		click(ArrowLeftDateBTN);
	}

	@Step("go to today date")
	public void GoToTodayDate() {
		MoveToAndClick(DateBTN);
		sleep(20);
		click(TodayDateBTN);
	}

	@Step("create event on today")
	public void CreateNewEventOnToday(int index) {
		String style = CreateNewEvent.get(index).getAttribute("style");
		if (style.contains("rgb(255, 240, 246)")) {
			CreateNewEvent(index);
		}

	}

	@Step("check event created")
	public boolean CheckEventCreated() {
		for (WebElement el : Event) {
			if (el.getText().contains("New event you just created")) {
				return true;
			} else {
				return false;
			}
		}
		return false;

	}

}
