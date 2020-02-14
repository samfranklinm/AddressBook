package addressBook;

public class LocalContacts extends Person {

	private String status;

	public LocalContacts(){};

	public LocalContacts(String firstName, String lastName, String phoneNumber, String status) {
		super(firstName, lastName, phoneNumber);
		this.status = status;
	}

	// status setter
	public void setStatus (String status) {
		this.status = status;	
	}

	// status getter
	public String getStatus() {
		status = "(Local)";
		return status;
	}
}
