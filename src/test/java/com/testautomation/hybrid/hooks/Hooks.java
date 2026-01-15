package com.testautomation.hybrid.hooks;

import com.testautomation.hybrid.drivers.DriverFactory;
import com.testautomation.hybrid.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

	@Before
	public void setUp() {
	    DriverManager.setDriver(new DriverFactory().initDriver());
	}
    @After
    public void tearDown() {
        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }
}
