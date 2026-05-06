package testcases;
import pojo.Product;
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
public class ProductTests extends BaseClass { 
	@Test
	public void getAllProducts() {
	given()

	.when()
	       .get(Routes.GET_ALL_PRODUCTS)
	.then()
	       .statusCode(200)
	       .body("size()", greaterThan(0));
	}
	
	@Test
	public void getProductByID() {
	given()
	       .pathParam("id", configReader.getIntProperty("productId"))
	.when()
	       .get(Routes.GET_PRODUCT_BY_ID)
	.then()
	       .statusCode(200);
	}

    @Test
    public void getLimitedProduct() {
    given()
           .pathParam("limit", 4) //as we have already ? in routes so we pass param
    .when()  
           .get(Routes.GET_PRODUCTS_WITH_LIMIT)
    .then()
           .statusCode(200)
	       .body("size()", equalTo(4))
           .log().body();
    }
	@Test
	public void GetDescendingSortProducts() {
	Response response = given()
		   .pathParam("order", "desc")
	
	.when()
	       .get(Routes.GET_PRODUCTS_SORTED)
	
	.then()
	       .statusCode(200)
	       .extract().response();
	List<Integer>productIds = response.jsonPath().getList("id", Integer.class);
	Assert.assertTrue(isSortedDescending(productIds));
	}

	@Test
	public void GetAscendingSortProducts() {
	Response response = given()
		   .pathParam("order", "asc")
	
	.when()
	       .get(Routes.GET_PRODUCTS_SORTED)
	
	.then()
	       .statusCode(200)
	       .extract().response();
	List<Integer>productIds = response.jsonPath().getList("id", Integer.class);
	Assert.assertTrue(isSortedAscending(productIds));
	}
		    
   @Test
   public void getAllCategories() {
   given()
   
   .when()
	         .get(Routes.GET_ALL_CATEGORIES)
   .then()
	         .statusCode(200)
		     .body("size()", greaterThan(0))
	         .log().body();
	   }
   @Test
   public void getProductsByCategory() {
   given()
          .pathParam("category", "electronics")
   .when()
          .get(Routes.GET_PRODUCTS_BY_CATEGORY)
   .then()
         .statusCode(200)
	     .body("size()", greaterThan(0))
	     .body("category", everyItem(notNullValue()))
	     .body("category", everyItem(equalTo("electronics")))
         .log().body();
   }
	@Test
	public void addProduct() {
	Product newProduct = Payload.productPayload();
	int productId = given()
	       .contentType("application/json")
	       .body(newProduct)
	.when()
	       .post(Routes.CREATE_PRODUCT)
	.then()
	       .log().body()
	       .statusCode(201) 
	       .body("id", notNullValue())
	       .body("title", equalTo(newProduct.getTitle()))
	       .extract().jsonPath().getInt("id");
	}
	
	@Test
	public void updateProduct() {
	int id = configReader.getIntProperty("productId");
    Product newProduct = Payload.productPayload();
    given()
			 .contentType("application/json")
			 .body(newProduct)
			 .pathParam("id", id) //its hardcoded because mock api
    .when()
			 .put(Routes.UPDATE_PRODUCT)
	.then()
			  .log().body()
			  .statusCode(200) 
			  .body("id", notNullValue())
			  .body("title", equalTo(newProduct.getTitle()));
			}

	@Test
	public void deleteProduct() {
	int id = configReader.getIntProperty("productId");
    given()
			 .pathParam("id", id) //its hardcoded because mock api
    .when()
			 .delete(Routes.DELETE_PRODUCT)
	.then()
			  .log().body()
			  .statusCode(200); 
			}
	       
	}

