package testcases;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import routesandendpoints.Routes;
import utils.ConfigReader;

public class BaseClass {
ConfigReader configReader;
@BeforeClass
public void setup() {
RestAssured.baseURI = Routes.baseURL; //you dont have to write routes each time
configReader = new ConfigReader();
}
}
