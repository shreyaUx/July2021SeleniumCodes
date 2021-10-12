package com.qa.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public WebDriver driver;
	public Properties prop;
	 public static ThreadLocal<WebDriver>tldriver = new ThreadLocal<WebDriver>();
     public OptionsManager OptionsManager;
	/*******
	 * @author shreya
	 */
    /**
     * This method is used to initialize broiwser on the basis of browsername
     * @param browser
     * @return will driver
     */
	public WebDriver initDriver(Properties prop) {
		OptionsManager=new OptionsManager(prop);
		String browser =prop.getProperty("browser").trim();
		System.out.println("browser nasme is  "+browser);
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver();
			tldriver.set(new ChromeDriver(OptionsManager.getChromeOptions()));
		} 
		else if (browser.equalsIgnoreCase("safari")) {
			//driver = new ChromeDriver();
			tldriver.set(new ChromeDriver(OptionsManager.getChromeOptions()));
		}
		else {
			System.out.println("Please pass correct driver..." +browser);
		}
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));

		return getDriver();
	}
	public synchronized WebDriver getDriver() {
		return tldriver.get();//LOCAL COPY OF DRIVER WILL GIVE BY GET METHOD
	}
/**
 * read config propoerties from resource folder on the basis of given environment
 * return this method return props
 * @return 
 * @throws IOException 
 */
	public Properties ininProp() throws IOException {
		prop=new Properties();
		try {
			FileInputStream ip= new FileInputStream("C:\\Users\\shreya\\JulyPOMSeries\\src\\test\\resources\\config\\config.properties");
			prop.load(ip);
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		return prop;
	}
	public String getScreenshot() {
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	
}
