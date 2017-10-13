package com.hotmail.thang.ui.view;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.vaadin.testbench.TestBenchElement;

public class LoginViewElement extends TestBenchElement {

	public void login(String username, String password) {
		WebElement loginElement = getLogin();
		WebElement passwordElement = getPassword();
		loginElement.clear();
		loginElement.sendKeys(username);
		passwordElement.clear();
		passwordElement.sendKeys(password);

		getSubmit().click();

		waitUntilElementPresent("Login failed", By.className("navigation-bar"));
	}

	protected void waitUntilElementPresent(String errorMessage, By by) {
		new WebDriverWait(getDriver(), 30).until(driver -> !driver.findElements(by).isEmpty());

	}

	private WebElement getSubmit() {
		return findElement(By.id("button-submit"));
	}

	private WebElement getPassword() {
		return findElement(By.id("password"));
	}

	private WebElement getLogin() {
		return findElement(By.id("login"));
	}

}
