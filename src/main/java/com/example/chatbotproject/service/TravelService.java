package com.example.chatbotproject.service;

import com.example.chatbotproject.controller.EmailController;
import com.example.chatbotproject.domain.TravelPackage;
import com.example.chatbotproject.repository.TravelPackageRepository;
import com.example.chatbotproject.domain.Travel;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TravelService {

    private final TravelPackageRepository travelPackageRepository;
    private final EmailController emailController;

    public void getPackage(Travel travel) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        // Open URL
        driver.get("https://www.tripstore.kr/");
        Thread.sleep(8000);

        // Close Advertisement
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/button")).click();
        Thread.sleep(1000);

        // Select Destination
        driver.findElement(By.xpath("//*[@id='__next']/div[1]/div[1]/div/div[3]/a")).click();
        Thread.sleep(1000);

        // Enter Destination
//        String city = travel.getDestination();
        String city = "오사카";
        driver.findElement(By.xpath("//*[@id='__next']/div[1]/div[2]/div/div[1]/div[2]/div/label/input")).sendKeys(city);
        Thread.sleep(1000);

        // Click Departure Date
//        String a = travel.getTravelMonth();
//        String b = travel.getTravelDay();
//        String[] arr = b.split("~");

//        String startDay = String.format("%02d%02d", Integer.parseInt(a.substring(0, 1)), Integer.parseInt(arr[0]));
//        String endDay = String.format("%02d%02d", Integer.parseInt(a.substring(0, 1)), Integer.parseInt(arr[1].substring(0, arr[1].length())));
        String startDay = "240708";
        String endDay = "240714";

        driver.findElement(By.xpath("//*[@id='__next']/div[1]/div[3]/div/div/div[1]/div/div/button")).click(); //출발 날짜
        // 요소가 클릭 가능할 때까지 기다림
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Retry logic for clicking start date element
        boolean startDateClicked = false;
        int retryCount = 0;
        while (!startDateClicked && retryCount < 5) {
            try {
                WebElement startDateElement = wait.until(ExpectedConditions.elementToBeClickable(By.id(startDay)));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", startDateElement);
                startDateElement.click();
                startDateClicked = true;
            } catch (Exception e) {
                retryCount++;
                Thread.sleep(1000);
            }
        }
        // Retry logic for clicking end date element
        boolean endDateClicked = false;
        retryCount = 0;
        while (!endDateClicked && retryCount < 5) {
            try {
                WebElement endDateElement = wait.until(ExpectedConditions.elementToBeClickable(By.id(endDay)));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", endDateElement);
                endDateElement.click();
                endDateClicked = true;
            } catch (Exception e) {
                retryCount++;
                Thread.sleep(1000);
            }
        }

        driver.findElement(By.xpath("//*[@id='bottom-sheet-calendar']/div[3]/div/button")).click(); //출발하기 클릭

        // Choose Package Type
        driver.findElement(By.xpath("//*[@id='__next']/div[1]/div[3]/div/div/div[2]/div/button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='bottom-sheet-kind-condition']/section/section/div/button[2]")).click();

        // Choose Airport
//        String airport = travel.getAirport();
//        int airportKey = switch (airport) {
//            case "인천, 김포" -> 1;
//            case "부산" -> 2;
//            case "대구" -> 3;
//            case "청주" -> 4;
//            default -> 6;
//        };
        int airportKey = 1;
        driver.findElement(By.xpath("//*[@id='__next']/div[1]/div[3]/div/div/div[4]/div/button")).click();
        driver.findElement(By.xpath("//*[@id='bottom-sheet-airports-condition']/section/section/div/button[" + airportKey + "]")).click();

        // Perform Search
        driver.findElement(By.xpath("//*[@id='__next']/div[1]/div[5]/ul[1]/a")).click();
        Thread.sleep(4000);

        // Close Advertisement
//        driver.findElement(By.cssSelector("body > div.ab-iam-root.v3.ab-animate-in.ab-animate-out.ab-effect-modal.ab-show > div.ab-in-app-message.ab-background.ab-modal-interactions.graphic.ab-clickable.ab-modal.ab-centered > button")).click();

        //duration
//        String duration = travel.getDuration();
//        String[] temp = duration.split(" ");
//        String real = temp[0] + temp[1];
        String duration = "3박4일";
        driver.findElement(By.xpath("//p[text()=\"" + duration + "\"]")).click();
        Thread.sleep(500);

        //expense
//        String expense = travel.getExpense();
        String expense = "200-250만원";
        driver.findElement(By.xpath("//p[text()=\"" + expense + "\"]")).click();


        //중간점검
        WebElement count = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("TripTotal_leftSection__EgjH1")));
        String countNum = count.getText();
        int realCountNum = Integer.parseInt(countNum.substring(0, 1));

//        if(realCountNum > 3){
//            driver.findElement(By.xpath("//*[@id='__next']/div[1]/section[1]/div[1]/button")).click(); //필터
//            Thread.sleep(1000);
//
////            String accommodation = travel.getAccommodation();
//            String accommodation = "4성급";
//
//            try {
//                WebElement element = driver.findElement(By.xpath("//p[text()=\"" + accommodation + "\"]"));
//                if (element.isDisplayed()) {
//                    element.click();
//                }
//            } catch (org.openqa.selenium.NoSuchElementException ignored) {
//            }
//            Thread.sleep(500);
//
//            driver.findElement(By.xpath("//*[@id='portal']/div[3]/section[1]/div[1]/div/div//button[2]")).click();
//        }

        // Extract Results
        WebElement tripListSection = driver.findElement(By.className("TripList_TripList__Pjpvv"));
        List<WebElement> results = tripListSection.findElements(By.tagName("a")); // 각 패키지의 a 태그

        List<TravelPackage> travelPackageList = new ArrayList<>();
        for (WebElement result : results) {
            try {
                String href = result.getAttribute("href");

                WebElement infoElement = result.findElement(By.className("CardPlp_rightSection__M4Af4"));
                String info = infoElement.getText();
                String[] packageContent = info.split("\\n");

//                for (String thing : packageContent) {
//                    System.out.println(thing);
//                }
//                System.out.println("URL: " + href);
//                System.out.println("---------------------------------------------------끝");

                TravelPackage travelPackage = new TravelPackage();

                travelPackage.setTitle(packageContent[4]);
                travelPackage.setPrice(packageContent[packageContent.length - 2]);
                travelPackage.setLink(href);

                travelPackageRepository.save(travelPackage);
                travelPackageList.add(travelPackage);
            } catch (Exception e) { // Handle exception
                e.printStackTrace();
            }
        }
        emailController.sendPasswordMail(travelPackageList);

        // Close the browser
        driver.quit();
    }
}