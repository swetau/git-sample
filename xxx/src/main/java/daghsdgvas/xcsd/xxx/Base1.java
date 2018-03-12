package daghsdgvas.xcsd.xxx;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;

public class Base1 {
	
	public static WebDriver driver;
	
	public static WebDriver browser() throws Exception {
		Reporter.log("in browser loop",true);
		
		Properties p = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\nitin\\New folder\\xxx\\src\\main\\java\\daghsdgvas\\xcsd\\xxx\\abc");
		
		p.load(fis);
		String Browser = p.getProperty("browser");
		
		if (Browser.equalsIgnoreCase("firefox")) {
			if(driver==null){
				String filepath = "src/test/resources/geckodriver.exe";
				filepath = System.getProperty("user.dir") + "/" + filepath;
				File file = new File(filepath);
				System.setProperty("webdriver.gecko.driver", filepath);
				driver = new FirefoxDriver();
				}
				else 
					return driver;
			
		}
		else if (Browser.equalsIgnoreCase("ie")) {
			if(driver==null){
			String filepath = "src/test/resources/IEDriverServer.exe";
			filepath = System.getProperty("user.dir") + "/" + filepath;
			File file = new File(filepath);
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			//driver = new InternetExplorerDriver();
			}
			else 
				return driver;

			
		}
		else if (Browser.equalsIgnoreCase("chrome")) {
			if(driver==null){
			String filepath = "src/test/resources/chromedriver.exe";
			filepath = System.getProperty("user.dir") + "/" + filepath;
			File file = new File(filepath);
			System.setProperty("webdriver.chrome.driver",
					file.getAbsolutePath());

			//driver = new ChromeDriver();
			}
			else 
				return driver;
		}
		else if (Browser.equalsIgnoreCase("safari")) {
			if(driver==null)
			//driver = new SafariDriver();
			return driver;
		}
		return driver;
	

	}


}
