package opendata.scholia.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import opendata.scholia.Pages.Country;
import opendata.scholia.Pages.Abstract.ScholiaContentPage;

import org.openqa.selenium.support.ui.ExpectedConditions;

public class Series extends ScholiaContentPage{



    public Series(WebDriver driver) {
    	super(driver);
        init();
    }
    
    public Series() {
    	super();
    	init();
    }

    private void init() {
        this.addDataTable("in-series");
        this.addDataTable("published-works");
        this.addDataTable("authors");
    }
}
