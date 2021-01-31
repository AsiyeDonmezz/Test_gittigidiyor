package gittigidiyor_Test;
import org.junit.Test;

public class TestClass extends ExtendedBaseTest
{
    @Test
    public void signInTest()
    {
        clickByXpath("//*[@title = 'Giriş Yap']");
        signIn("asiyedonmeztest@gmail.com", "test.123456");
    }
    @Test
    public void SearchAndCartOperations() throws InterruptedException {
        signInTest();
        SearchAndCartOperations("bilgisayar");
    }


}
