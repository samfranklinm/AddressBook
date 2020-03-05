package addressBook;

public class InternationalContacts extends Person implements LocalOrInternational{

	private String status;

	public InternationalContacts() {};
	
	public InternationalContacts(String firstName, String lastName, String phoneNumber, String status){
		super(firstName, lastName, phoneNumber);
		this.status = status;
	}

	@Override
	public String getStatus() {
		status = "International";
		return status;
	}

	@Override
	public void setStatus() {
		String status = null;
		this.status = status;
	}
}
