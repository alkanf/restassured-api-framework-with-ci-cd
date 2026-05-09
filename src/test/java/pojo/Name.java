package pojo;

public class Name {
//Variables
private String firstname;
private String lastname;
//Constructor
public Name(String firstname, String lastname) {
this.firstname = firstname;
this.lastname = lastname;
}

//Getters and setters, methods
public String getFirstname() {
	return firstname;
}
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
public String getLastname() {
	return lastname;
}
public void setLastname(String lastname) {
	this.lastname = lastname;
}

}
