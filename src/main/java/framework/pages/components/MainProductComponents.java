package framework.pages.components;

import lombok.Data;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

@Getter
public class MainProductComponents {
    private String name;
    private String regularPrice;
    private String price;

    public MainProductComponents(WebElement container){
        try {
            this.name = container.findElement(By.xpath(".//h3[@class='h3 product-title']/a")).getText();
        } catch (NoSuchElementException e) {
            this.name = null;
        }
        try {
            this.regularPrice = container.findElement(By.xpath(".//div[@class='product-price-and-shipping']/span[@class='regular-price']")).getText();
        } catch (NoSuchElementException e) {
            this.regularPrice = null;
        }
        try {
            this.price = container.findElement(By.xpath(".//div[@class='product-price-and-shipping']/span[@class='price']")).getText();
        } catch (NoSuchElementException e) {
            this.price = null;
        }



    }

}
