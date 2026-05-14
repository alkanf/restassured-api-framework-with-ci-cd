package testcases;
import pojo.Cart;
import pojo.Login;
import pojo.Product;
import pojo.User;
import routesandendpoints.Routes;
import utils.ConfigReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.Payload;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.ArrayList;
import java.util.List;
public class LoginTests extends BaseClass {
	
@Test
public void invalidUserLogin() {
Login data = Payload.loginPayload();
given()
       .contentType(ContentType.JSON)
       .body(data)
.when()
       .post(Routes.AUTH_LOGIN)
.then()
       .statusCode(401)
       .body(equalTo("username or password is incorrect")) //Can write directly equal to if only text available
       .log().body();
}
@Test
public void ValidUserLogin() {
    Login data = new Login(configReader.getProperty("authUsername"), configReader.getProperty("authPassword")); //can get from config.properties also
given()
       .contentType(ContentType.JSON)
       .body(data)
.when()
       .post(Routes.AUTH_LOGIN)
.then()
       .statusCode(201)
       .body("token", notNullValue())
       .log().body();
}
}
