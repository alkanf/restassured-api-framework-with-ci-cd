package testcases;
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
public class UserTests extends BaseClass { 
@Test
public void getAllUsers() {
given()

.when()
       .get(Routes.GET_ALL_USERS)
.then()
       .statusCode(200)
       .contentType(ContentType.JSON)
       .body("size()", greaterThan(0))
       .log().body();
}

@Test
public void getUserByID() {
given()
       .pathParam("id", configReader.getIntProperty("userId"))
.when()
       .get(Routes.GET_USER_BY_ID)
.then()
       .statusCode(200)
       .contentType(ContentType.JSON)
       .log().body();
}

@Test
public void getUsersWithLimit() {
given()
       .pathParam("limit", 4)
.when()
       .get(Routes.GET_USERS_WITH_LIMIT)
.then()
       .statusCode(200)
       .contentType(ContentType.JSON)
       .body("size()", equalTo(4))
       .log().body();
}

@Test
void getUsersSortedDes() {
Response response = given()
       .pathParam("order", "desc")
.when()
       .get(Routes.GET_USERS_SORTED)
.then()
        .statusCode(200)
        .extract().response();
List<Integer> userIds = response.jsonPath().getList("id", Integer.class);
assertThat(isSortedDescending(userIds), is(true));
}
@Test
void getUsersSortedAsc() {
Response response = given()
       .pathParam("order", "asc")
.when()
       .get(Routes.GET_USERS_SORTED)
.then()
        .statusCode(200)
        .extract().response();
List<Integer> userIds = response.jsonPath().getList("id", Integer.class);
assertThat(isSortedAscending(userIds), is(true));
}

@Test
public void createUser() {
User newUser = Payload.userPayload(); //static

int id =given()
       .contentType(ContentType.JSON)
       .body(newUser)
.when()
      .post(Routes.CREATE_USER)
.then()
      .log().body()
      .statusCode(201)
      .body("id", notNullValue())
      .extract().jsonPath().getInt("id");
      
System.out.println("Generated User ID ===>"  + id );
}

@Test
public void updateUser() {
	User updatedUser = Payload.userPayload(); //static

	given()
	       .contentType(ContentType.JSON)
	       .body(updatedUser)
	       .pathParam("id", configReader.getIntProperty("userId"))
	.when()
	      .put(Routes.UPDATE_USER)
	.then()
	      .log().body()
	      .statusCode(200)
	      .body("username", equalTo(updatedUser.getUsername()));
	    	      
}

	@Test
	public void deleteUser() {

		given()
		       .contentType(ContentType.JSON)
		       .pathParam("id", configReader.getIntProperty("userId"))
		.when()
		      .delete(Routes.DELETE_USER)
		.then()
		      .log().body()
		      .statusCode(200);
		    	      
	}
















}
