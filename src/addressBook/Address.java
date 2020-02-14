package addressBook;

public class Address {

	private String streetNumber;
	private String streetName;
	private String city;
	private String state;
	private String zipcode;

	Address(){
		streetNumber = "123";
		streetName = "Main St.";
		city = "San Francisco";
		state = "CA";
		zipcode = "94941";
	};

	// setter for Address
	public void setAddress(String streetNumber, String streetName, String city, String state, String zipcode){
		this.streetNumber = streetNumber;
		this.streetName = streetName;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}
	
	// getter for Address
	public String getAddress(){
		return (" \n\t\t " + streetNumber + " " + streetName + " \n\t\t " + city + ", " + state + "\n\t\t " + zipcode + "\n");
	}

}
