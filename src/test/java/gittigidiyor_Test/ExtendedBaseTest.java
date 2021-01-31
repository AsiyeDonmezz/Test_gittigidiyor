package gittigidiyor_Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import java.util.Random;

public class ExtendedBaseTest extends BaseTest
{
    public int index;
    public void signIn(String mail, String sifre)
    {
        driver.get("https://www.gittigidiyor.com/uye-girisi");
        sendById("L-UserNameField", mail);
        sendById("L-PasswordField", sifre);
        clickById("gg-login-enter");
    }
    public void SearchAndCartOperations(String keyword) throws InterruptedException
    {
        sendByDataCy("header-search-input", keyword);
        clickByDataCy("search-find-button");
        driver.get("https://www.gittigidiyor.com/arama/?k=" + keyword);
        int productCount = Integer.parseInt(driver.findElement(By.xpath("//*[@class='result-count']")).getText());
        Random rnd = new Random();
        if (productCount > 48)
        {
            index = rnd.nextInt(49);
        }
        else
        {
            index = rnd.nextInt(productCount);
        }
        Thread.sleep(2000L);

        //Ürün detaylarını görüntüleme
        clickByXpath("//*[@product-index='" + index + "']");
        String strUrl = driver.getCurrentUrl();
        driver.get(strUrl);

        //Ürün detaylarındaki fiyat
        WebElement price= driver.findElement(By.id("sp-price-highPrice"));
        String priceText= price.getText();
        System.out.println(priceText);

        //Ürünü sepete atma
        WebElement element = driver.findElement(By.id("add-to-basket"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView()", new Object[]{element});
        Thread.sleep(1000L);
        element.click();
        Thread.sleep(1000L);
        clickByXpath("//*[text()='Sepet']");
        Thread.sleep(5000L);



        //Sepetteki Ürün Fiyatı
        WebElement priceCart= driver.findElement(By.className("total-price"));
        String priceCartText=priceCart.getText();


        // Ürün sayfasındaki fiyat ile sepetteki fiyatın karşılaştırılması aynıysa ürünü silme
        if(priceText.compareTo(priceCartText)>0)
        {
            System.out.println("Fiyatlar Birbirine Eşit" );
            //Sepete eklenen ürünü silme
            driver.findElement(By.linkText("Sil")).click();
            Thread.sleep(4000L);
            System.out.println("Ürün silme işlemi gerçekleştirildi...");
        }

        //Sepetteki ürün adetinin artırılması
        /*WebElement quantityBasket = driver.findElement(By.className("amount"));
        quantityBasket.click();
        quantityBasket.sendKeys("1");
        driver.findElement(By.className("plus icon-plus gg-icon gg-icon-plus")).click();*/

    }
}
