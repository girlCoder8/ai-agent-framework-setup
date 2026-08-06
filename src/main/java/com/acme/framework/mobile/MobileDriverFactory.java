package com.acme.framework.mobile;
import io.appium.java_client.AppiumDriver; import io.appium.java_client.android.AndroidDriver; import io.appium.java_client.ios.IOSDriver; import org.openqa.selenium.remote.DesiredCapabilities; import java.net.*;
public final class MobileDriverFactory { private MobileDriverFactory(){} public static AppiumDriver create(String platform, URL server, DesiredCapabilities caps){ return platform.equalsIgnoreCase("ios")?new IOSDriver(server,caps):new AndroidDriver(server,caps); } }
