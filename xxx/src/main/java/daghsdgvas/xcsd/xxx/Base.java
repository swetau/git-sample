package daghsdgvas.xcsd.xxx;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {
	
	public WebDriver driver;
	
		public WebDriver initbrow() throws Exception
		{
		Properties p = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\nitin\\New folder\\xxx\\src\\main\\java\\daghsdgvas\\xcsd\\xxx\\abc");
		
		p.load(fis);
		String brow = p.getProperty("browser");
		
		/*if(brow.equals("chrome"))
		{
			
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\nitin\\Downloads\\chromedriver.exe");
			   
			//driver = new ChromeDriver();
		}
		else */
		
		if (brow.equalsIgnoreCase("firefox")) {
			if(driver.equals(null)){
				String filepath = "src/test/resources/geckodriver.exe";
				filepath = System.getProperty("user.dir") + "/" + filepath;
				File file = new File(filepath);
				System.setProperty("webdriver.gecko.driver", filepath);
				driver = new FirefoxDriver();
				}
				else 
					return driver;
		
		}
		return driver;
		
	}

}
