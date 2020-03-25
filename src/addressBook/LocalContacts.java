package addressBook;

import addressBook.Address;
import com.addressBook.rest.LocalOrInternational;
import addressBook.Person;

public class LocalContacts extends Person implements LocalOrInternational{
	
	Address ad = new Address();
	
	private String status;
	private String address;

	public LocalContacts(){};

	public LocalContacts(String firstName, String lastName, String phoneNumber, String address, String status) {
		super(firstName, lastName, phoneNumber);
		this.status = status;
		this.address = ad.getAddress();
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

