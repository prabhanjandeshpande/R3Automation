package com.r3.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

public class TestBase {
	public static final Logger logger = LoggerFactory.getLogger(TestBase.class);
	public SoftAssert softAssert = new SoftAssert();
}
