package testcases;
import pojo.Cart;
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
public class CartTests extends BaseClass {
//@Test
public void getAllCarts() {
given()

.when()
       .get(Routes.GET_ALL_CARTS)
.then()
       .statusCode(200)
       .body("size()", greaterThan(1))
       .log().body();
}

//@Test
public void getCartById() {
given()
       .pathParam("id", configReader.getProperty("cartId"))
.when()
       .get(Routes.GET_CART_BY_ID)
.then()
       .statusCode(200)
       .body("size()", greaterThan(0))
       .log().body();
}

@Test
public void testGetCartsByDateRange() {

    String startDate = configReader.getProperty("startdate");
    String endDate = configReader.getProperty("enddate");

    Response response = given()
            .pathParam("startdate", startDate)
            .pathParam("enddate", endDate)
    .when()
            .get(Routes.GET_CARTS_BY_DATE_RANGE)
    .then()
            .statusCode(200)
            .log().body()
            .extract().response();

    // Extract the list of cart dates
    List<String> cartDates = response.jsonPath().getList("date");

    // Validate cart dates with helper methods which return boolean in range with config
    validateCartDatesWithinRange(cartDates, startDate, endDate);

    assertThat(validateCartDatesWithinRange(cartDates, startDate, endDate), is(true));
} 
	
	
	
	
}
