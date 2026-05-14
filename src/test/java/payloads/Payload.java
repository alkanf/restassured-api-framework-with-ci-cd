package payloads;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.github.javafaker.Faker;

import pojo.Address;
import pojo.Cart;
import pojo.CartProduct;
import pojo.Geolocation;
import pojo.Login;
import pojo.Name;
import pojo.Product;
import pojo.User;

public class Payload {
private static final Faker faker = new Faker();
private static final Random random = new Random();
private static final String categories[] = {"electronics","furniture","clothing","books","beauty"};

//Product Payload
public static Product productPayload() {
String name = faker.commerce().productName();
double price = Double.parseDouble(faker.commerce().price().replace(",", ""));
String description = faker.lorem().sentence();
String imageUrl = "https:i.pravatar.cc/100";
String category = categories[random.nextInt(categories.length)];
return new Product(name, price, description, imageUrl, category);}




//User Payload, as it has nested json we need to create seperate object and store variables
public static User userPayload() {
//name
String firstname = faker.name().firstName();
String lastname = faker.name().lastName();
Name name = new Name(firstname,lastname);
//location
String lat = faker.address().latitude();
String lng = faker.address().longitude();
Geolocation location = new Geolocation(lat,lng);
//address
String city = faker.address().city();
String street = faker.address().streetName();
int number = random.nextInt(100); //just use random java class
String zipcode = faker.address().zipCode();
Address address = new Address(city,street,number,zipcode,location);
//User
String email =faker.internet().emailAddress();
String username = faker.name().username();
String password = faker.internet().password();
String phoneNumber = faker.phoneNumber().cellPhone();
User user = new User(email,username,password,name,address,phoneNumber); //all data with pojo data and variables
return user;
}



//Cart Payload
public static Cart cartPayload(int userId) { //as we need userId as external variable 
List<CartProduct> products = new ArrayList<>();
//Each cart product contains 
int productId = random.nextInt(100);
int quantity = random.nextInt(10);
products.add(new CartProduct(productId,quantity)); //short version
return new Cart(userId,new Date(),products); //you can create Date date = new Date(); as it will be anonymous object
}






//Login 
public static Login loginPayload() {
String username = faker.name().username();
String password = faker.internet().password();
Login login = new Login(username,password);
return login;
}



}
