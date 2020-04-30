package com.qiqi.main;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HelloWorld {

  public static void main(String[] args) throws InterruptedException {
//设置驱动路径,windows环境请将mac/chromedriver改成windows/chromedriver.exe
//    options.setBinary(
//        "E:/ChromePortable/Chrome65x64/GoogleChrome_65.0.3325.146/ChromePortable/chromedriver.exe");

    Calendar cal = Calendar.getInstance();
    Date date = cal.getTime();
    String a = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(date);
    System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(date));


    System.setProperty("webdriver.chrome.driver",
        HelloWorld.class.getClassLoader().getResource("windows/chromedriver.exe").getPath());
    //创建谷歌浏览器驱动
    final ChromeOptions options = new ChromeOptions();

//    options.setBinary(
//        "E:/ChromePortable/Chrome65x64/GoogleChrome_65.0.3325.146/ChromePortable/ChromePortable.exe");

    String path = HelloWorld.class.getClassLoader().getResource("ChromePortable/ChromePortable.exe")
        .getPath();
    path = path.substring(1);
    options.setBinary(path);

//访问百度
//    for (int i = 0; i < 10; i++) {
//      System.out.println(i);
//      WebDriver webDriver = new ChromeDriver(options);
//      webDriver.get("https://www.baidu.com");
//    }


    final int poolSize = 5;

    final CountDownLatch count = new CountDownLatch(poolSize);
    ExecutorService service = Executors.newFixedThreadPool(poolSize);
    for (int i = 0; i < poolSize; i++) {
      service.submit(new Runnable() {
        public void run() {
          WebDriver webDriver = new ChromeDriver(options);
          webDriver.get("https://www.baidu.com");
//          webDriver.quit(); //关闭打开的浏览器
          webDriver.close(); //关闭打开的浏览器
          count.countDown();
        }
      });
    }


    try {
    Thread.sleep(10000);
      count.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      Thread.sleep(2000);
      Calendar cal2 = Calendar.getInstance();
      Date date2 = cal2.getTime();
      String b = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(date2);
      System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(date2));

//      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//      System.out.println(df.format(new Date()));// new Date()为获取当前系统时间

      long firstDateMilliSeconds = date.getTime();
      long secondDateMilliSeconds = date2.getTime();
      long MinusSecond = secondDateMilliSeconds - firstDateMilliSeconds;
      System.out.println(MinusSecond+" 毫秒");
      System.out.println((int) MinusSecond / 1000+" 秒");
      service.shutdown();
    }

//    webDriver.findElement(By.id("kw")).sendKeys("hello world");//找到搜索框，填写hello world
//    webDriver.quit(); //关闭打开的浏览器
  }


}
