package com.cxb.cw;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class cxbTest {
	
	private static AndroidDriver driver;
	fangfa fangfas=new fangfa(driver);
	
	@BeforeTest
	public void setUp() throws Exception{
		//set up appium
		File classpathRoot=new File(System.getProperty("user.dir"));
		File appDir=new File(classpathRoot,"apps/cw");
		File app=new File(appDir,"cw.apk");
		DesiredCapabilities capabilities=new DesiredCapabilities();
		//capabilities.setCapability("app",app.getAbsolutePath());
		capabilities.setCapability("deviceName","635dd402");
		capabilities.setCapability("platformName", 4.3);
		capabilities.setCapability("appPackage", "com.cxb.cw");
		capabilities.setCapability("appActivity",".activity.LoginActivity");
		//capabilities.setCapability("appWaitActivity",".activity.HomePageActivity");
		//capabilities.setCapability("appActivity",".activity.WelcomeActivity");
		//设置appium输入法
		capabilities.setCapability("unicodeKeyboard", "True");
		capabilities.setCapability("resetKeyboard", "True");
		driver=new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@AfterTest
	public void tearDown(){
		driver.quit();
	}
	
	@Test(priority=1,enabled=false)
	//测试登录退出
	public void testDenglu() throws InterruptedException{
		Thread.sleep(2000);
		//driver.currentActivity();
		//driver.startActivity("com.cxb.cw",".activity.HomePageActivity");
		fangfa fangfa1=new fangfa(driver);
		fangfa1.denglu();
		//退出
		driver.findElementById("com.cxb.cw:id/left_menu").click();
		driver.findElementById("com.cxb.cw:id/iv_side_header").click();
		driver.findElementByName("退出当前账号").click();
		Thread.sleep(2000);
	}
	
	@Test(priority=0,enabled=false)
	//测试忘记密码(需要获取验证码，验证码不知如何得到)
	public void testwangjimm() throws InterruptedException{
		driver.findElementById("com.cxb.cw:id/forget_pwd").click();
		driver.findElementById("com.cxb.cw:id/your_phone").sendKeys("18801148360");
		//driver.findElementById("com.cxb.cw:id/get_code").click();
		driver.findElementById("com.cxb.cw:id/input_code").sendKeys("568695");
		driver.findElementById("com.cxb.cw:id/set_pwd").sendKeys("123456");
		driver.findElementById("com.cxb.cw:id/submit").click();
		Thread.sleep(2000);
		
	}
	
	@Test(priority=2,enabled=false)
	//测试新用户(需要获取验证码，验证码不知如何得到)
	public void testxinyy(){
		
	}
	
	@Test(priority=3)
	//测试添加账套
	public void testtianjiazt() throws InterruptedException{
		//登录
		fangfa fangfa2=new fangfa(driver);
		fangfa2.denglu();
		
		//弹出账套下拉框
		driver.findElementById("com.cxb.cw:id/title").click();
		driver.findElementByName("增加账套").click();
		driver.findElementById("com.cxb.cw:id/basicinfo_email_editor").sendKeys("704818942@qq.com");
		driver.findElementByName("请输入您公司的全称").sendKeys("自动化测试");
		driver.findElementByName("请输入企业法人姓名").sendKeys("测试");
		driver.findElementByName("请输入公司电话或手机号码").sendKeys("18801148360");
		
		
		fangfa2.swipeToUp(3000);
		//选择行业(上滑选择)
		driver.findElementById("com.cxb.cw:id/basicinfo_trade_editor").click();
		fangfa2.swipeUntilElementAppear(By.name("商业服务"),"Up",4000);
		driver.findElementByName("商业服务").click();
		//选择城市(搜索选择)
		driver.findElementById("com.cxb.cw:id/basicinfo_city_editor").click();
		driver.findElementByName("搜索").sendKeys("衡水");
		driver.findElementByName("衡水市").click();
		driver.findElementById("com.cxb.cw:id/basicinfo_taxpayer_type").click();
		driver.findElementByName("小规模纳税人").click();
		driver.findElementById("com.cxb.cw:id/tv_select_time").click();
		driver.findElementByName("设定").click();
		driver.findElementByName("保存信息").click();
		//切换到刚刚新增的账套
		driver.findElementById("com.cxb.cw:id/title").click();
		WebElement zhangtaoliebiao=driver.findElementById("com.cxb.cw:id/home_top_menu_zt_lv");
		fangfa2.swipeOnElementUntilElementAppear(By.name("自动化测试"), zhangtaoliebiao, "Up", 3000);
		driver.findElementByName("自动化测试").click();
		Assert.assertEquals(driver.findElementById("com.cxb.cw:id/title").getText(), "自动化测试");
		
		//编辑刚新增的账套
		driver.findElementById("com.cxb.cw:id/title").click();
		driver.findElementByXPath("//android.widget.RelativeLayout[2]").click();
		driver.findElementByName("保存信息").click();
		
	}
	
	@Test
	//测试科目设置
	public void kemusz(){
		
		//点击进入到科目设置界面
		driver.findElementById("com.cxb.cw:id/left_menu").click();
		driver.findElementByName("科目设置").click();
		
		
		
	}
	
	
	
	/*
	//测试新增凭证
	@Test
	public void xinzengpz() throws InterruptedException{
		//登录
		driver.findElementByName("请输入您的手机号").sendKeys("18801148360");
		driver.findElementById("com.cxb.cw:id/password").sendKeys("123456");
		Thread.sleep(1000);
		driver.findElementById("com.cxb.cw:id/submit").click();
		//显性等待，等待登录成功
		WebElement el=(new WebDriverWait(driver,10)).until(new ExpectedCondition<WebElement>(){
			public WebElement apply(WebDriver d){
				return d.findElement(By.id("com.cxb.cw:id/left_menu"));
			}
		});
		
		//点击新增凭证
		driver.findElementByName("新增凭证").click();
		driver.findElementByName("资金往来").click();
		driver.findElementByName("借入现金").click();
		driver.findElementById("com.cxb.cw:id/accountvoucher_journal_list_j_item_money_editor").click();
		driver.findElementById("com.cxb.cw:id/pop_calculator_3").click();
		driver.findElementById("com.cxb.cw:id/pop_calculator_confirm").click();
		fangfa fangfas=new fangfa(driver);
		fangfas.swipeToUp(3000);
		driver.findElementById("com.cxb.cw:id/accountvoucher_journal_list_d_item_2_subject").click();
		driver.findElementByName("224101  养老保险").click();
		driver.findElementByName("凭证预览").click();
		driver.sendKeyEvent(66);
		driver.findElementByName("保存并新增凭证").click();
		driver.findElementById("com.cxb.cw:id/home_bottom_menu_close_btn").click();
		
	}
	*/
	/*
	//测试凭证管理
	@Test
	public void pingzhenggl() throws InterruptedException{
		//登录
		driver.findElementByName("请输入您的手机号").sendKeys("18801148360");
		driver.findElementById("com.cxb.cw:id/password").sendKeys("123456");
		Thread.sleep(1000);
		driver.findElementById("com.cxb.cw:id/submit").click();
		//显性等待，等待登录成功
		WebElement el=(new WebDriverWait(driver,10)).until(new ExpectedCondition<WebElement>(){
			public WebElement apply(WebDriver d){
				return d.findElement(By.id("com.cxb.cw:id/left_menu"));
			}
		});
		
		driver.findElementById("com.cxb.cw:id/left_menu").click();
		driver.findElementByName("凭证管理").click();
	//	driver.findElementById("com.cxb.cw:id/right_btn").click();
	//	driver.findElementById("com.cxb.cw:id/btn_date_start").click();
	//	driver.findElementByName("设定").click();
	//	driver.findElementById("com.cxb.cw:id/btn_date_end").click();
	//	driver.findElementByName("设定").click();
	//	driver.findElementById("com.cxb.cw:id/edt_money_min").sendKeys("0");
	//	driver.findElementById("com.cxb.cw:id/edt_money_max").sendKeys("10");
	//	driver.findElementById("com.cxb.cw:id/btn_filter").click();
		driver.findElementByName("第001号").click(); 
		driver.findElementByName("预 览").click();
		driver.sendKeyEvent(4);
		driver.findElementByName("编 辑").click();
		driver.sendKeyEvent(4);
		Thread.sleep(1000);
		driver.sendKeyEvent(4);
		
		
	}
	*/
	/*
	//测试账簿查询
	@Test
	public void zhangbucx() throws InterruptedException{
		//登录
		driver.findElementByName("请输入您的手机号").sendKeys("18801148360");
		driver.findElementById("com.cxb.cw:id/password").sendKeys("123456");
		Thread.sleep(1000);
		driver.findElementById("com.cxb.cw:id/submit").click();
		//显性等待，等待登录成功
		WebElement el=(new WebDriverWait(driver,10)).until(new ExpectedCondition<WebElement>(){
			public WebElement apply(WebDriver d){
				return d.findElement(By.id("com.cxb.cw:id/left_menu"));
			}
		});
		
		//点击账簿管理
		driver.findElementById("com.cxb.cw:id/left_menu").click();
		driver.findElementByName("账簿查询").click();
		driver.findElementById("com.cxb.cw:id/btn_date_start").click();
		WebElement riqi=driver.findElementByXPath("//android.widget.NumberPicker[contains(@index,1)]");
		fangfa fangfas=new fangfa(driver);
		fangfas.swipeOnElementUntilElementAppear(By.name("02"), riqi, "Down", 500);
		driver.findElementByXPath("//android.widget.NumberPicker[2]/android.widget.Button[contains(@text,'02')]").click();
		driver.findElementByName("设定").click();
		driver.findElementByName("查询").click();
		driver.findElementById("com.cxb.cw:id/title").click();
		driver.findElementByName("银行存款日记帐").click();
		driver.findElementById("com.cxb.cw:id/title").click();
		driver.findElementByName("总账").click();
		driver.findElementByName("成本类").click();
		driver.findElementByName("4001  生产成本").click();
		driver.findElementById("com.cxb.cw:id/menu_text").click();
		driver.findElementById("com.cxb.cw:id/go_back").click();
		driver.findElementById("com.cxb.cw:id/go_back").click();
			
		
	}
	*/
	/*
	//测试报表查询
	@Test
	public void baobiaocx() throws InterruptedException{
		//登录
		driver.findElementByName("请输入您的手机号").sendKeys("18801148360");
		driver.findElementById("com.cxb.cw:id/password").sendKeys("123456");
		Thread.sleep(1000);
		driver.findElementById("com.cxb.cw:id/submit").click();
		//显性等待，等待登录成功
		WebElement el=(new WebDriverWait(driver,10)).until(new ExpectedCondition<WebElement>(){
			public WebElement apply(WebDriver d){
				return d.findElement(By.id("com.cxb.cw:id/left_menu"));
				}
			});
		
		//点击报表查询
		driver.findElementById("com.cxb.cw:id/left_menu").click();
		driver.findElementByName("报表查询").click();
	//	driver.findElementById("com.cxb.cw:id/ampm_bottom").click();
	//	driver.findElementById("com.cxb.cw:id/right_btn_l").click();
	//	driver.sendKeyEvent(4);
	//	driver.findElementById("com.cxb.cw:id/right_btn").click();
	//	Assert.assertEquals(driver.findElementById("com.cxb.cw:id/title").getText(), "报表查询");  
	//	driver.sendKeyEvent(4);
	//	Thread.sleep(1000);
	//	driver.sendKeyEvent(4);
		this.clickOnElementUntilElementAppear();
		
		
		
	}
	*/
		//点击某个元素，直到某元素出现
		public void clickOnElementUntilElementAppear(){
			/*
			boolean flag=true;
			while(flag){
				
					
					if(driver.findElementById("com.cxb.cw:id/tv_next_month").getText().equals("四季")){
						flag=false;
						//break;
					}else{
						driver.findElementById("com.cxb.cw:id/tv_next_month").click();	
					}
			}
			*/
			/*
			while(true){
				
				
				if(driver.findElementById("com.cxb.cw:id/tv_next_month").getText().equals("四季")){
					break;
				}else{
					driver.findElementById("com.cxb.cw:id/tv_next_month").click();	
				}
			}
			*/
			//这种方法比较慢
			boolean flag=true;
			while(flag){
				try{
					driver.findElementByName("12月").click();
					flag=false;
				}catch(Exception e){
					//TODO: handle exception
					driver.findElementById("com.cxb.cw:id/tv_next_month").click();
				}
			}
		}
		/*
		//事由管理
		@Test
		public void shiyougl() throws InterruptedException{
			//登录
			driver.findElementByName("请输入您的手机号").sendKeys("18801148360");
			driver.findElementById("com.cxb.cw:id/password").sendKeys("123456");
			Thread.sleep(1000);
			driver.findElementById("com.cxb.cw:id/submit").click();
			//显性等待，等待登录成功
			WebElement el=(new WebDriverWait(driver,10)).until(new ExpectedCondition<WebElement>(){
				public WebElement apply(WebDriver d){
					return d.findElement(By.id("com.cxb.cw:id/left_menu"));
				}
			});
			
			//点击事由管理
			driver.findElementById("com.cxb.cw:id/left_menu").click();
			WebElement cedaohanglan=driver.findElementById("com.cxb.cw:id/menu_frame");
			fangfa fangfa1=new fangfa(driver);
			fangfa1.swipeOnElement(cedaohanglan, "Up", 2000);
			driver.findElementByName("事由管理").click();
			Assert.assertEquals(driver.findElementById("com.cxb.cw:id/title").getText(), "事由管理");
			driver.findElementByName("添加").click();
			driver.findElementById("com.cxb.cw:id/et_add_reason").sendKeys("自动化测试");
			driver.findElementById("com.cxb.cw:id/btn_save_reason").click();
			this.longPress(By.name("自动化测试"));
			driver.findElementById("com.cxb.cw:id/btn_delete").click();
			
			
			
		}
		public void longPress(By by){
			try{
				WebElement element=driver.findElement(by);
				TouchAction ta=new TouchAction(driver);
				ta.longPress(element).release().perform();
			}catch(Exception e){
				//TODO: handle exception
				e.printStackTrace();
			}
		}
		*/
		//测试意见反馈
		@Test
		public void yijianfk() throws InterruptedException{
			//登录
			driver.findElementByName("请输入您的手机号").sendKeys("18801148360");
			driver.findElementById("com.cxb.cw:id/password").sendKeys("123456");
			Thread.sleep(1000);
			driver.findElementById("com.cxb.cw:id/submit").click();
			//显性等待，等待登录成功
			WebElement el=(new WebDriverWait(driver,10)).until(new ExpectedCondition<WebElement>(){
				public WebElement apply(WebDriver d){
					return d.findElement(By.id("com.cxb.cw:id/left_menu"));
				}
			});
			
			driver.findElementById("com.cxb.cw:id/left_menu").click();
			WebElement cedaohang=driver.findElementById("com.cxb.cw:id/menu_frame");
			fangfa fangfa2=new fangfa(driver);
			fangfa2.swipeOnElement(cedaohang, "Up", 2000);
			driver.findElementByName("意见反馈").click();
			driver.findElementById("com.cxb.cw:id/add_opinion").sendKeys("测试发送的虚假反馈信息");
			driver.findElementByName("提交").click();
			this.isElementExist(By.xpath("//android.widget.TextView[contains(@text,'测试发送的虚假反馈信息')]"));
			
			
		}
		
		//判断元素是否存在
		public boolean isElementExist(By by){
			try{
				driver.findElement(by);
				return true;
			}catch(Exception e){
				//TODO: handle exception
				e.printStackTrace();
				return false;
			}
		}
		
		
	
}



