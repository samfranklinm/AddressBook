package addressBook;

public class Address {

	private String address;

	Address(){
		address = "123 Main St, Mill Valley, CA 94941";
	};

	// setter for Address
	public void setAddress(String address){
		this.address = address;
	}
	
	// getter for Address
	public String getAddress(){
		return (address);
	}

}
