package testcases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Map;

import org.testng.annotations.Test;

import payloads.Payload;
import pojo.Product;
import routesandendpoints.Routes;

public class ProductDataDrivenTests extends BaseClass {
    @Test(dataProvider="jsonDataProvider", dataProviderClass=utils.DataProviders.class)
	public void addNewProduct(Map<String,String> data) {
    	
    	String title = data.get("title");
    	double price = Double.parseDouble(data.get("price"));
    	String category = data.get("category");
    	String description = data.get("description");
    	String image = data.get("image");
    	
    	Product newProduct = new Product(title,price,category,image,description);
    	
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
          
    	//Delete after create
    	given()
    	       .pathParam("id", productId)
    	.when()
    	       .delete(Routes.DELETE_PRODUCT)
    	.then()
    	        .statusCode(200);
    	  
    }
}
