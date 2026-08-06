package com.acme.framework.driver;
import org.openqa.selenium.WebDriver; import org.openqa.selenium.chrome.ChromeDriver; import org.openqa.selenium.chrome.ChromeOptions;
public final class WebDriverFactory { private WebDriverFactory(){} public static WebDriver create(){ ChromeOptions o=new ChromeOptions(); if(Boolean.parseBoolean(System.getenv().getOrDefault("HEADLESS","true"))) o.addArguments("--headless=new"); o.addArguments("--window-size=1920,1080"); return new ChromeDriver(o); } }
