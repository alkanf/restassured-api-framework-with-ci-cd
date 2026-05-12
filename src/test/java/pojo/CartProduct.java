package pojo;
/*
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
public class CartProduct {
//Variables
private int productId;
private int quantity;
//Constructor
public CartProduct(int productId, int quantity) {
this.productId = productId;
this.quantity = quantity;
}
//Getters and setters methods
public int getProductId() {
	return productId;
}
public void setProductId(int productId) {
	this.productId = productId;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}

}
