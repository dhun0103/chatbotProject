package com.example.chatbotproject;

import com.example.chatbotproject.domain.Travel;
import com.example.chatbotproject.repository.TravelRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TripstoreCrawler {

    @Autowired
    private static TravelRepository travelRepository;

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        // Open URL
        driver.get("https://www.tripstore.kr/");
        Thread.sleep(5000);

        // Close Advertisement
//        driver.findElement(By.xpath("/html/body/div[4]/div[2]/button")).click();
//        Thread.sleep(1000);

        // Select Destination
        driver.findElement(By.xpath("//*[@id='__next']/div[1]/div[1]/div/div[3]/a")).click();
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("body > div.ab-iam-root.v3.ab-animate-in.ab-animate-out.ab-effect-modal.ab-show > div.ab-in-app-message.ab-background.ab-modal-interactions.graphic.ab-clickable.ab-modal.ab-centered > button")).click();
        Thread.sleep(1000);

        // Enter Destination
        driver.findElement(By.xpath("//*[@id='__next']/div[1]/div[2]/div/div[1]/div[2]/div/label/input")).sendKeys("도쿄");
//        Thread.sleep(1000);

//        driver.findElement(By.cssSelector("body > div.ab-iam-root.v3.ab-animate-in.ab-animate-out.ab-effect-modal.ab-show > div.ab-in-app-message.ab-background.ab-modal-interactions.graphic.ab-clickable.ab-modal.ab-centered > button")).click();
//        Thread.sleep(1000);

        // Click Departure Date
        driver.findElement(By.xpath("//*[@id='__next']/div[1]/div[3]/div/div/div[1]/div/div/button")).click();

        // Select Date
        driver.findElements(By.xpath("//p[text()='24']")).get(0).click();

        // Confirm Departure
        driver.findElement(By.xpath("//*[@id='bottom-sheet-calendar']/div[3]/div/button")).click();

        // Choose Package Type
        driver.findElement(By.xpath("//*[@id='__next']/div[1]/div[3]/div/div/div[2]/div/button")).click();
        driver.findElement(By.xpath("//*[@id='bottom-sheet-kind-condition']/section/section/div/button[2]")).click();

        // Choose Airport
        driver.findElement(By.xpath("//*[@id='__next']/div[1]/div[3]/div/div/div[3]/div/button")).click();
        driver.findElement(By.xpath("//*[@id='bottom-sheet-airports-condition']/section/section/div/button[1]")).click();

        // Perform Search
        driver.findElement(By.xpath("//*[@id='__next']/div[1]/div[5]/ul[1]/a")).click();
        Thread.sleep(1000);

        // Close Advertisement
//        driver.findElement(By.cssSelector("body > div.ab-iam-root.v3.ab-animate-in.ab-animate-out.ab-effect-modal.ab-show > div.ab-in-app-message.ab-background.ab-modal-interactions.graphic.ab-clickable.ab-modal.ab-centered > button")).click();

        // Extract Results
        List<WebElement> results = driver.findElements(By.className("TripList_TripList__Pjpvv"));
        for (WebElement result : results) {
            try {
                List<WebElement> infoElements = result.findElements(By.className("CardPlp_rightSection__M4Af4"));
                int cnt=0;
                for (WebElement infoElement : infoElements) {
                    Travel atravel = new Travel();

                    String info = infoElement.getText();
                    String[] arr = info.split("\\n");
                    System.out.println(arr.length);
                    if(arr.length>8){
//                        atravel.setPackageName(arr[0]);
//                        atravel.setDuration(arr[1]);
//                        atravel.setDepartureFrom(arr[2]);
//                        atravel.setAirline(arr[3]);
//                        atravel.setDetails(arr[4]);
//                        atravel.setPriceNew(arr[arr.length-2]);
//                        atravel.setPriceOld(arr[arr.length-1]);

                        System.out.println(arr[1]);

//                        travelRepository.save(atravel);
                    }else{
//                        atravel.setPackageName(arr[0]);
//                        atravel.setDuration(arr[1]);
//                        atravel.setDepartureFrom(arr[2]);
//                        atravel.setAirline(arr[3]);
//                        atravel.setDetails(arr[4]);
//                        atravel.setPriceNew(arr[5]);
//                        atravel.setPriceOld(arr[6]);

                        System.out.println(arr[1]);

//                        travelRepository.save(atravel);
                    }


                }
            } catch (Exception e) {
                // Handle exception
            }
        }
//        driver.findElement(By.cssSelector("body > div.ab-iam-root.v3.ab-animate-in.ab-animate-out.ab-effect-modal.ab-show > div.ab-in-app-message.ab-background.ab-modal-interactions.graphic.ab-clickable.ab-modal.ab-centered > button")).click();

        // Close the browser
        driver.quit();

    }
}
