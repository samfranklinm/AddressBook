package addressBook;

public class InternationalContacts extends Person {

	private String status;

	public InternationalContacts() {};
	
	public InternationalContacts(String firstName, String lastName, String phoneNumber, String status){
		super(firstName, lastName, phoneNumber);
		this.status = status;
	}

	// status setter
	public void setStatus (String status){
		this.status = status;
	}

	// status getter
	public String getStatus() {
		status = "(International)";
		return status;
	}
}
