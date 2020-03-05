package addressBook;

public class LocalContacts extends Person implements LocalOrInternational{

	private String status;

	public LocalContacts(){};

	public LocalContacts(String firstName, String lastName, String phoneNumber, String status) {
		super(firstName, lastName, phoneNumber);
		this.status = status;
	}


	@Override
	public String getStatus() {
		status = "(Local)";
		return status;
	}

	@Override
	public void setStatus() {
		String status = null;
		this.status = status;
	}
}
