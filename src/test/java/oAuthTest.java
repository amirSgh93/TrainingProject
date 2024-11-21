import io.cucumber.cienvironment.internal.com.eclipsesource.json.Json;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
public class oAuthTest {
    public static void main(String[] args) throws InterruptedException {
    String authorizationCode ="4%2F0AeanS0azxCVAYbMn2pMsSkyA-zktoD8G4uECJi88pFxMU22HPzsZpEL89P6-6ie1NqpPtw";
        // urlEncoding is to not encode any special caracter
        String tokenResponse = given().urlEncodingEnabled(false)
                .queryParams("code",authorizationCode)
                .queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
                .queryParam("grant_type","authorization_code")
                .when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
        System.out.println("Token Response: " + tokenResponse);

        // Extraction des tokens d'accès et de rafraîchissement
        JsonPath jsonPath = new JsonPath(tokenResponse);
        String accessToken = jsonPath.getString("access_token");
        String refreshToken = jsonPath.getString("refresh_token");

        // Utilisation du token d'accès pour accéder à une API protégée
        String response = RestAssured.given()
                .queryParam("access_token", accessToken)
                .when().get("https://rahulshettyacademy.com/getCourse.php")
                .asString();

        System.out.println("API Response: " + response);
        System.out.println("Test");
    }
}
