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
@Test
public void getAllCarts() {
given()

.when()
       .get(Routes.GET_ALL_CARTS)
.then()
       .statusCode(200)
       .body("size()", greaterThan(1))
       .log().body();
}

@Test
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
@Test
public void getUserCart() {
given()
       .pathParam("userId", configReader.getIntProperty("userId"))
.when()
       .get(Routes.GET_USER_CART)
.then()
       .statusCode(200)
       .body("userId", everyItem(equalTo(configReader.getIntProperty("userId"))));
}

@Test
public void getCartsWithLimit() {
given()
       .pathParam("limit", configReader.getIntProperty("limit"))
.when()
       .get(Routes.GET_CARTS_WITH_LIMIT)
.then()
       .statusCode(200)
       .body("size()", lessThanOrEqualTo(configReader.getIntProperty("limit")));
}

@Test
public void getCartsSortedDesc() {
Response response = given()
        .pathParam("order", "desc")
.when()
       .get(Routes.GET_CARTS_SORTED)
.then()
       .statusCode(200)
       .body("size()", greaterThan(1))
       .log().body()
       .extract().response();

List<Integer> cartIds= response.jsonPath().getList("id", Integer.class);
assertThat(isSortedDescending(cartIds), is(true));
}

@Test
public void getCartsSortedAsc() {
Response response = given()
        .pathParam("order", "asc")
.when()
       .get(Routes.GET_CARTS_SORTED)
.then()
       .statusCode(200)
       .body("size()", greaterThan(1))
       .log().body()
       .extract().response();

List<Integer> cartIds= response.jsonPath().getList("id", Integer.class);
assertThat(isSortedAscending(cartIds), is(true));
}
@Test
public void createCart() {
Cart cart = Payload.cartPayload(1); // this is userId which api needs it, you can pass it from config.properties also
   id = given()
        .contentType(ContentType.JSON)
        .body(cart)
.when()
       .post(Routes.CREATE_CART)
.then()
       .statusCode(201)
       .log().body()
       .body("id", notNullValue())
       .body("userId", notNullValue())
       .body("products.size()", greaterThan(0))
       .extract().response().jsonPath().getInt("id");    
}

int id;
@Test
public void updateCart() {
Cart updatedCart = Payload.cartPayload(1); // this is userId which api needs it, you can pass it from config.properties also
         given()
        .pathParam("id", id)
        .contentType(ContentType.JSON)
        .body(updatedCart)
.when()
       .put(Routes.UPDATE_CART)
.then()
       .statusCode(200)
       .log().body()
       .body("id", notNullValue())
       .body("userId", notNullValue())
       .body("products.size()", greaterThan(0))
       .extract().response().jsonPath().getInt("id");    
}

@Test
public void deleteCart() {
         given()
        .pathParam("id", id)
       
.when()
       .delete(Routes.DELETE_CART)
.then()
       .statusCode(200)
       .log().body();
      
}









}
