package stepDefinations;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

public class OAuth2 {
    @Test
    public void test1(){

       Response resp= RestAssured.given().auth().oauth2("").post();
        System.out.println("Code " +resp.getStatusCode());
        System.out.println("Code " +resp.jsonPath().prettify());


    }
}
