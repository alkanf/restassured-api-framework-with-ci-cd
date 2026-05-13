package pojo; /*
{
	  "id": Number,
	  "userId": Number,
	  "date": Date,
	  "products": [
	    {
	      "productId": Number,
	      "quantity": Number
	    }
	  ]
	} */

import java.util.Date;
import java.util.List;

public class Cart {
//Variables
private int userId;
private Date date;
private List<CartProduct> products;

//Constructor
public Cart(int userId, Date date, List<CartProduct> products) {
this.userId = userId;
this.date = date;
this.products = products;
}
//Getters and setters methods
public int getUserId() {
	return userId;
}

public void setUserId(int userId) {
	this.userId = userId;
}

public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}

public List<CartProduct> getProducts() {
	return products;
}

public void setProducts(List<CartProduct> products) {
	this.products = products;
}

}