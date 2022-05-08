package tests;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import pageobjects.*;

public class Tests extends BaseTest {

	@Test
	@Description("switch to infinite scroll and month view")
	public void tc01_SwitchToInfiniteScrollAndMonthView() {
		CalanderFunctions f = new CalanderFunctions(driver);
		f.SwitchToMonthView();
		f.SwitchToInfiniteScrollView();
	}

	@Test
	@Description("create 15 events, check that the DOM elements count increased.")
	public void tc02_CreateNewEventThanCheckDOMElementCountIncreased() {
		CalanderFunctions f = new CalanderFunctions(driver);
		//f.SwitchToMonthView();
		int count = f.GetCountDomElement();
		for (int i = 0; i < 15; i++) {
			f.CreateNewEvent(i);
		}
		int countAfter = f.GetCountDomElement();
		System.out.println(count);
		System.out.println(countAfter);
		Assert.assertTrue(countAfter > count );

	}

	@Test
	@Description("go to next month, check that the DOM elements count decreased.")
	public void tc03_GoToNextMonthThanCheckDOMElementCountDecreased() {
		CalanderFunctions f = new CalanderFunctions(driver);
		//f.SwitchToMonthView();
		int count = f.GetCountDomElement();
		f.GoToNextMonth();
		int countAfter = f.GetCountDomElement();
		System.out.println(count);
		System.out.println(countAfter);
		Assert.assertTrue(countAfter < count);

	}

	@Test
	@Description("create new event, go to next month and to previous month, check events that have created still exist.")
	public void tc04_CreateEventsOnTodayDateThanCheckItStillExsist() {
		CalanderFunctions f = new CalanderFunctions(driver);
		//f.SwitchToMonthView();
		f.GoToTodayDate();
		for (int i = 0; i < 40; i++) {
			f.CreateNewEventOnToday(i);
		}
		f.GoToNextMonth();
		f.GoToPreviousMonth();
		Assert.assertFalse(f.CheckEventCreated(),"events that have created not exist");

	}

}
