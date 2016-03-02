package com.cxb.cw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


import io.appium.java_client.android.AndroidDriver;

public class fangfa {
	public AndroidDriver driver;
	
	public  fangfa(AndroidDriver driver){
		this.driver=driver;
	}
	
	public void denglu() throws InterruptedException{
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
	}
	//封装滑动方法
	//获取应用占屏幕大小
	public int[] appScreen(){
		int width=driver.manage().window().getSize().getWidth();
		int HeightScreen=driver.manage().window().getSize().getHeight();
		int[] appWidthAndHight={width,HeightScreen};
		return appWidthAndHight;
	}
	
	//封装swipe方法，分别向上、向下、向左、向右滑动
	//向左滑动
	public void swipeToLeft(int duration){
		int startx=appScreen()[0]*4/5;
		int endx=appScreen()[0]*1/5;
		int y=appScreen()[1]*1/2;
		try{
			driver.swipe(startx, y, endx, y, duration);
		}catch(Exception e){
			//TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//向右滑动
	public void swipeToRight(int duration){
		int startx=appScreen()[0]*1/5;
		int endx=appScreen()[0]*4/5;
		int y=appScreen()[1]*1/2;
		try{
			driver.swipe(startx, y, endx, y, duration);
		}catch(Exception e){
			//TODO:handle exceptiuon
			e.printStackTrace();
		}
	}
	
	//向上滑动
	public void swipeToUp(int duration){
		int x=appScreen()[0]*1/2;
		int starty=appScreen()[1]*4/5;
		int endy=appScreen()[1]*1/5;
		try{
			driver.swipe(x, starty, x, endy, duration);
		}catch(Exception e){
			//TODO:handle exception
			e.printStackTrace();
		}
	}
	
	//向下滑动
	public void swipeToDown(int duration){
		int x=appScreen()[0]*1/2;
		int starty=appScreen()[1]*1/5;
		int endy=appScreen()[1]*4/5;
		try{
			driver.swipe(x, starty, x, endy, duration);
		}catch(Exception e){
			//TODO:handle exception
			e.printStackTrace();
		}
	}
	
	//封装滑动方法，通过各参数实现各方向滑动
	public void swipe(String direction,int duration){
		switch(direction){
		case "Up" :
			swipeToUp(duration);
			break;
			
		case "Down" :
			swipeToDown(duration);
			break;
			
		case "Left" :
			swipeToLeft(duration);
			break;
			
		case "Right" :
			swipeToRight(duration);
			break;
			
		default :
			break;
		}
	}
	
	//在某方向上滑动，直至期望的元素出现
	public void swipeUntilElementAppear(By by,String direction, int duration){
		boolean flag=true;
		while(flag){
			try{
				driver.findElement(by);
				flag=false;
			}catch(Exception e){
				//TODO:handle exception
				swipe(direction,duration);
			}
		}
	}
	
	//在某元素上滑动
	public void swipeOnElement(WebElement element, String direction, int duration){
		//x，y分别为元素的起始坐标点
		int x=element.getLocation().getX();
		int y=element.getLocation().getY();
		int elementWidth=element.getSize().getWidth();
		int elementHight=element.getSize().getHeight();
		
		switch(direction){
		case "Up" :
			int startx=x+elementWidth/2;
			int starty=y+elementHight*4/5;
			int endy=y+elementHight*1/5;
			driver.swipe(startx, starty, startx, endy, duration);
			break;
			
		case "Down" :
			int startx1=x+elementWidth/2;
			int starty1=y+elementHight*1/5;
			int endy1=y+elementHight*4/5;
			driver.swipe(startx1, starty1, startx1, endy1, duration);
			break;
			
		case "Left" :
			int startx2=x+elementWidth*4/5;
			int endx2=x+elementWidth*1/5;
			int y2=y+elementHight/2;
			driver.swipe(startx2, y2, endx2, y2, duration);
			break;
			
		case "Right" :
			int startx3=x+elementWidth*1/5;
			int endx3=x+elementWidth*4/5;
			int y3=y+elementHight/2;
			driver.swipe(startx3, y3, endx3, y3, duration);
			break;
			
		default :
			break;
			
		}
		
	}
	
	//在某元素上滑动，直至某元素出现
	public void swipeOnElementUntilElementAppear(By by, WebElement element, String direction, int duration){
		boolean flag=true;
		while(flag){
			try{
				driver.findElement(by);
				flag=false;
			}catch(Exception e){
				//TODO:handle exception
				this.swipeOnElement(element, direction, duration);
			}
		}
	}

}








