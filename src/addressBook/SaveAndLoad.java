package addressBook;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaveAndLoad {
	private File file;

	// Initializer
	public SaveAndLoad(String fileName) {
		this.file = new File(fileName);
	}
	public SaveAndLoad(File file) {
		this.file = file;
	}

	// Function to save Address Book
	public void save(ArrayList<Person> contacts) {

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
			for (int i = 0; i < contacts.size(); i++) {
				writer.write("First Name: " + contacts.get(i).getFirstName() + "\n" + "Last Name: " + contacts.get(i).getLastName() + "\n"
						+ "Phone Number: " + contacts.get(i).getPhoneNumber() + "\nAddress: " + contacts.get(i).getAddress().toString() + "\n\n");
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	// Function to load Address Book
	public void loadAll() throws IOException {

		String csv = "Database.csv";
		BufferedReader reader = null;
		String line = "";
		String splitToken = ",";

		try {
			reader = new BufferedReader(new FileReader(csv));
			while((line = reader.readLine()) != null) {
				AddressBook ab = new AddressBook();
				ArrayList<Person> peeps = new ArrayList<Person>();
				ab.sortAddressBook(peeps);
				
				System.out.println(line);
			}

		}catch(FileNotFoundException fe) {
			fe.printStackTrace();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		} finally{
			if(reader != null) {
				try {
					reader.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
