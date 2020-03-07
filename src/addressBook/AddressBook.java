package addressBook;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;



class AddressBook  {
	ArrayList<Person> contacts = new ArrayList<Person>();
	int index = 0;
	Person person = new Person();
	Scanner input = new Scanner(System.in);

	String url = "jdbc:mysql://localhost:3306/addressdb"; // url for sql server
	String username = "root"; // localhost username
	String password = "samfranklinm"; // localhost password

	//Function to add contact to the Address Book DB
	public void addContact(){

		try {
			// Get a connection to database
			Connection myConnection = DriverManager.getConnection(url, username, password);

			// Execute SQL query
			String sql = "INSERT into persons"
					+ "(firstName, lastName, phoneNumber, address, resStatus)" 
					+ "VALUES(?, ?, ?, ?, ?)";

			// Crate a PreparedStatement (so we can take user input for select columns in our db, first name, last name, etc.)
			PreparedStatement myStatement = myConnection.prepareStatement(sql);

			// set firstName - take input and store it in DB
			System.out.print("Enter contact's first name: ");
			person.setFirstName(input.nextLine());
			myStatement.setString(1, person.getFirstName());

			// set lastName - take input and store it in DB
			System.out.print("Enter contact's last name: ");
			person.setLastName(input.nextLine());
			myStatement.setString(2, person.getLastName());

			// set phoneNumber - take input and store it in DB
			System.out.print("Enter contact's phone number: ");
			person.setPhoneNumber(input.nextLine());
			myStatement.setString(3, person.getPhoneNumber());

			// set address - take input and store it in DB
			System.out.println("Enter contact's address (in this format: '123' 'Main St.' 'Mill Valley, CA' '94941'): ");
			person.setAddress(input.nextLine());
			myStatement.setString(4, person.getAddress());

			// set resStatus - - take input and store it in DB (local or international)
			System.out.print("Is the contact being added a local contact(type 'Local') or an international contact (type 'International')? ");

			person.setStatus(input.nextLine());
			//String localOrInt = person.getStatus();
			myStatement.setString(5, person.getStatus());


			// Execute query
			myStatement.execute();

			// Successful addition
			System.out.println(person.getFirstName() + " Added!");
			myConnection.close();



			/* ------------------ FOR ARRAYLIST PURPOSES -------------------- */

			//    			// If contact is locally located - create a local contact
			//    			switch (localOrInt) {
			//    			case "L":  
			//    				Person localPerson = new LocalContacts();
			//
			//    				// set firstName
			//    				localPerson.setFirstName(person.getFirstName());
			//
			//    				// set lastName
			//    				localPerson.setLastName(person.getLastName());
			//
			//    				// set phoneNumber
			//    				localPerson.setPhoneNumber(person.getLastName());
			//
			//    				// set Address
			//    				localPerson.setAddress(person.getAddress());
			//
			//    				// add contact to the Array List
			//    				contacts.add(person);
			//    				System.out.println("Contact added " + person.getFirstName() + " to the list!" );
			//    				System.out.println();
			//    				break;
			//
			//    			case "I":  // If contact is internationally located - create an international contact
			//    				Person interPerson = new InternationalContacts();
			//    				// set firstName
			//    				interPerson.setFirstName(person.getFirstName());
			//
			//    				// set lastName
			//    				interPerson.setLastName(person.getLastName());
			//
			//    				// set phoneNumber
			//    				interPerson.setPhoneNumber(person.getLastName());
			//
			//    				// set Address
			//    				interPerson.setAddress(person.getAddress());
			//
			//    				// add contact to the Array List
			//    				contacts.add(person);
			//    				System.out.println("Contact added " + person.getFirstName() + " to the list!" );
			//    				System.out.println();
			//    				break;
			//
			//    			default: 
			//    				System.out.println("Not a valid option, try again");
			//    				break;
			//    			}


		}catch(SQLException e) { // catch the error and do not let program crash
			e.printStackTrace();
		}	
	}

	// Function to update contact
	public void updateContact(){
		String changeSelection, newChange = null;

		try {
			// Get a connection to database
			Connection myConnection = DriverManager.getConnection(url, username, password);

			// Create SQL query
			String sqlUpdate = "UPDATE persons SET columnName=? WHERE firstName=?";

			// Crate a PreparedStatement (so we can take user input for select columns in our db, first name, last name, etc.)
			PreparedStatement myStatement = myConnection.prepareStatement(sqlUpdate);

			// set firstName - take input and search for the contact in DB
			System.out.print("Enter contact's first name to update the contact: ");
			String contactSelection = input.nextLine();

			// what does the user want to update?
			System.out.print("What would you like to update ('firstName', 'lastName', 'phoneNumber', 'address', 'resStatus')? ");
			changeSelection = input.nextLine();


			switch (changeSelection) { // depending on what the user wants to update for that specific contact do the below

			case "firstName":
				sqlUpdate = "UPDATE persons SET firstName=? WHERE firstName=?";
				System.out.print("What do you want the " + changeSelection + " be changed to? ");
				newChange = input.nextLine();
				myStatement = myConnection.prepareStatement(sqlUpdate);
				myStatement.setString(1, newChange);
				break;
			case "lastName":
				sqlUpdate = "UPDATE persons SET lastName=? WHERE firstName=?";
				System.out.print("What do you want the " + changeSelection + " be changed to? ");
				newChange = input.nextLine();
				myStatement = myConnection.prepareStatement(sqlUpdate);
				myStatement.setString(1, newChange);
				break;
			case "phoneNumber":
				sqlUpdate = "UPDATE persons SET phoneNumber=? WHERE firstName=?";
				System.out.print("What do you want the " + changeSelection + " be changed to? ");
				newChange = input.nextLine();
				myStatement = myConnection.prepareStatement(sqlUpdate);
				myStatement.setString(1, newChange);
				break;
			case "resStatus":
				sqlUpdate = "UPDATE persons SET resStatus=? WHERE firstName=?";
				System.out.print("What do you want the " + changeSelection + " to be changed to ('Local' or 'International')? ");
				newChange = input.nextLine();
				myStatement = myConnection.prepareStatement(sqlUpdate);
				myStatement.setString(1, newChange);
				break;
			case "address":
				sqlUpdate = "UPDATE persons SET address=? WHERE firstName=?";
				System.out.println("What do you want the " + changeSelection + " be changed to? ");
				newChange = input.nextLine();
				myStatement = myConnection.prepareStatement(sqlUpdate);
				myStatement.setString(1, newChange);
				break;
			default:
				System.out.println("/Nothing was updated.");
				break;
			}		

			myStatement.setString(2, contactSelection);

			// Execute query
			int rowsUpdated = myStatement.executeUpdate();

			if (rowsUpdated > 0) { // if a contact was updated
				System.out.println(contactSelection + " was updated successfully!");
			}else {
				System.out.println("Nothing was updated."); // if nothing was updated
			}

		}catch(SQLException e) { // catch the error and do not let program crash
			e.printStackTrace();
		}

		/* ------------------ FOR ARRAYLIST PURPOSES -------------------- */

		//      Note: If you change/uncomment this block, you would need to change the function to an arraylist return instead of void.

		//		String lastName;
		//		String firstName;
		//		Scanner input = new Scanner(System.in);
		//		System.out.println("Who would you like to update (enter first name only): ");
		//		firstName = input.nextLine();
		//
		//		sortAddressBook(contacts); // sorting for the binarySearch to work
		//		int searchResult = binarySearch(contacts, firstName); // look for that firstName in your contact list
		//
		//		if (searchResult == -1) {
		//			System.out.println("The person you are trying to update is currently not in the Address Book.\n");
		//		} else {
		//			System.out.println("\nEnter the updated information below. . .\n");
		//			System.out.print("Enter contact's first name: ");
		//			contacts.get(searchResult).setFirstName(input.nextLine()); // update firstName
		//
		//			System.out.print("Enter contact's last name: ");
		//			contacts.get(searchResult).setLastName(input.nextLine()); // set lastName
		//
		//			System.out.print("Enter contact's phone number: ");
		//			contacts.get(searchResult).setPhoneNumber(input.nextLine()); // set phoneNumber)
		//
		//			System.out.println("Enter contact's address (in this format: '123' 'Main St.' 'Mill Valley, CA' '94941'): ");
		//			contacts.get(searchResult).setAddress(input.nextLine()); // set address
		//			System.out.println();
		//
		//			firstName = contacts.get(searchResult).getFirstName();
		//			lastName = contacts.get(searchResult).getLastName();
		//
		//			System.out.println("Updating " + firstName + " " + lastName + ". . .");
		//
		//			//contacts = (ArrayList<Person>) contacts.clone();
		//			//System.arraycopy(contacts, 0, contacts, 0, searchResult);
		//		}
		//		return contacts;

	}

	// Function to delete contact
	public void deleteContact(){

		// delete contact from database
		try {
			Connection myConnection = DriverManager.getConnection(url, username, password);

			String sqlDelete = "Delete from persons WHERE firstName = ? "; // Delete Statement for MySQL

			PreparedStatement myStatement = myConnection.prepareStatement(sqlDelete); // Prepared Statement allows user input

			// set firstName - take input and search for the contact in DB
			System.out.print("Enter contact's first name to delete the contact: ");
			String contactSelection = input.next();
			myStatement.setString(1, contactSelection); // set the input as the firstName to search.

			// Execute SQL statement
			myStatement.execute();

			System.out.println("Contact deleted!");
			myConnection.close();

		}catch(SQLException exc){ // catch the error and do not let program crash
			exc.printStackTrace();
		}


		/* ------------------ FOR ARRAYLIST PURPOSES -------------------- */

		//      Note: If you change/uncomment this block, you would need to change the function to an arraylist return instead of void.


		//		String firstName;
		//		Scanner input = new Scanner(System.in);
		//		System.out.println("Who would you like to delete (enter first name only): ");
		//		firstName = input.nextLine();
		//
		//		sortAddressBook(contacts); // sorting for the binarySearch to work
		//
		//		int searchResult = binarySearch(contacts,firstName);// look for that firstName in your contact list
		//
		//		if (searchResult == -1) {
		//			System.out.println("The person you are trying to delete is currently not in the Address Book.\n");
		//		} else {
		//			System.out.println("Deleting " + firstName + ". . .");
		//			// Using library to copy array but to itself and deleting the specified contact
		//			// at the specified index (searchResult)
		//			contacts.remove(searchResult);
		//		}
		//		return contacts;
	}

	// Function to list added contacts in a sorted order
	// Function to display the contacts list in Address Book
	public void printAddressBook(){
		System.out.println("---------ADDRESS BOOK----------"); 

		// Print out contacts' information on console from database
		try {
			Connection myConnection = DriverManager.getConnection(url, username, password);

			// Create a sql Statement
			Statement myStatement = myConnection.createStatement();

			// Set ResultSet to execution of the statement to retrieve information requested
			ResultSet myResultSet = myStatement.executeQuery("SELECT * from persons ORDER BY firstName ASC");

			while (myResultSet.next()) { // As long as there are contacts to retrieve in the db, retrieve them
				System.out.println(myResultSet.getString("firstName") 
						+ " "
						+ myResultSet.getString("lastName")   
						+ "\n");
			}

			myConnection.close();
		}catch(SQLException exc){
			exc.printStackTrace();
		}

		/* ------------------ FOR ARRAYLIST PURPOSES -------------------- */

		//sortAddressBook(contacts);
		// for(int i = 0; i < contacts.size(); i++) {
		//System.out.println(contacts.get(i).toString() + " " + contacts.get(i).getStatus() + "\n");
		//}

		System.out.println("--------------------------------");
	}

	// Function to search for a specific contact

	// Function to search for a specific contact by 3 different methods and retrieve all of their information stored in database
	public void searchContact(){

		try {
			Connection myConnection = DriverManager.getConnection(url, username, password);

			// How does the user want to search for a contact?
			System.out.println("Enter (1) to search by firstName, (2) by lastName, (3) by phoneNumber: ");
			String search = input.nextLine();

			switch (search) { // depending on user's preference, we search for the user and get their information allocated in the database
			case "1":
				String sqlSearchByFirstName = "SELECT * FROM persons WHERE firstName=? ";
				PreparedStatement myStatementF = myConnection.prepareStatement(sqlSearchByFirstName);
				System.out.print("Enter firstName of the contact you'd like to search: ");
				myStatementF.setString(1, input.nextLine());
				ResultSet myRSF = myStatementF.executeQuery();
				while (myRSF.next()) {
					System.out.println(myRSF.getString("firstName") 
							+ " "
							+ myRSF.getString("lastName")   
							+ "\n"
							+ myRSF.getString("phoneNumber")
							+ "\n"
							+ myRSF.getString("address")
							+ "\n"
							+ myRSF.getString("resStatus")
							+ " Contact"
							+ "\n");
				}
				break;
			case "2":
				String sqlSearchByLastName = "SELECT * FROM persons WHERE lastName=? ";
				PreparedStatement myStatementL = myConnection.prepareStatement(sqlSearchByLastName);
				sqlSearchByLastName = "SELECT * FROM persons WHERE lastName=?";
				System.out.print("Enter lastName of the contact you'd like to search: ");
				myStatementL.setString(1, input.nextLine());
				ResultSet myRSL = myStatementL.executeQuery();
				while (myRSL.next()) {
					System.out.println(myRSL.getString("firstName") 
							+ " "
							+ myRSL.getString("lastName")   
							+ "\n"
							+ myRSL.getString("phoneNumber")
							+ "\n"
							+ myRSL.getString("address")
							+ "\n"
							+ myRSL.getString("resStatus")
							+ " Contact"
							+ "\n");
				}
				break;
			case "3":
				String sqlSearchByPhoneNumber = "SELECT * FROM persons WHERE phoneNumber=?";
				PreparedStatement myStatementP = myConnection.prepareStatement(sqlSearchByPhoneNumber);
				System.out.print("Enter phoneNumber of the contact you'd like to search: ");
				myStatementP.setString(1, input.nextLine());
				ResultSet myRSP = myStatementP.executeQuery();
				while (myRSP.next()) {
					System.out.println(myRSP.getString("firstName") 
							+ " "
							+ myRSP.getString("lastName")   
							+ "\n"
							+ myRSP.getString("phoneNumber")
							+ "\n"
							+ myRSP.getString("address")
							+ "\n"
							+ myRSP.getString("resStatus")
							+ " Contact"
							+ "\n");
				}
				break;
			default: 
				System.out.print("Contact not found.\n");
			}

			myConnection.close();
		}catch(SQLException exc){
			exc.printStackTrace();
		}


		/* ------------------ FOR ARRAYLIST PURPOSES -------------------- */


		//
		//		Scanner input = new Scanner(System.in);
		//
		//		System.out.println("What's the first name of the contact you are trying to look up?: ");
		//		String firstName = input.nextLine();
		//
		//		sortAddressBook(contacts); // sorting for the binarySearch to work
		//		int searchResult = binarySearch(contacts, firstName);// look for that firstName in your contact list
		//
		//		if (searchResult == -1) {
		//			System.out.println("The person you are trying to update is currently not in the Address Book.\n");
		//		} else {
		//			System.out.print("Contact's Name: " + contacts.get(searchResult).getFirstName() + " ");
		//			System.out.print(contacts.get(searchResult).getLastName() + " \n");
		//			System.out.println("Contact's Phone Number: " + contacts.get(searchResult).getPhoneNumber() + " ");
		//			System.out.print("Contact Address: " + contacts.get(searchResult).getAddress().toString());
		//			System.out.println();
		//		}

	}

	// Function to exit the program

	// Function to exit the program
	public void closeAddressBook(){
		try {
			System.out.println("Address book closed.");
			System.exit(0);
		}catch(Exception e) {
			System.out.println("Address book did not close properly..");
			e.printStackTrace();
		}
	}


	/* ------------------ FOR ARRAYLIST PURPOSES -------------------- */

	//	// Function to sort Address Book alphabetically
	//	public void sortAddressBook(ArrayList<Person> contacts) {
	//		// Using a simple Bubble Sort function to sort out the contacts' first names in
	//		// the Address Book alphabetically
	//		for (int i = 0; i < contacts.size(); i++) {
	//			for (int j = 0; j < contacts.size() - i - 1; j++) {
	//				// if the contacts's firstName's first letter comes after the next contact's
	//				// firstName's first letter in the alphabet table, then swap contacts.
	//				if (contacts.get(j).getFirstName().charAt(0) > contacts.get(j+1).getFirstName().charAt(0)) {
	//					Person temp = contacts.get(j);
	//					contacts.set(j, contacts.get(j+1));
	//					contacts.set(j+1, temp);
	//				}
	//			}
	//		}
	//	}

	/* ------------------ FOR ARRAYLIST PURPOSES -------------------- */

	//	// Function to search for the contact to delete and update contact
	//	public int binarySearch(ArrayList<Person> contacts, String firstName) {
	//		int first = 0, last = contacts.size() - 1;
	//
	//		while (first <= last) {
	//			int middle = (first + last) / 2;
	//			int itsHere = firstName.compareTo((contacts.get(middle).getFirstName()));
	//
	//			if (itsHere == 0) // if the first index we look at is where the key-index we're looking for is,
	//				// then,
	//				return middle; // return that index
	//			if (itsHere > 0) // if the key-index lies somewhere beyond the first index, then,
	//				first = middle + 1; // ignore the rest of the indices before the middle + 1 index
	//			else // else it's safe to assume that the key-index is somewhere before the middle
	//				last = middle - 1; // ignore the rest of the indices after mid - 1
	//
	//			middle = (first + last) / 2; // update the mid point
	//		}
	//		return -1;
	//	}

	/* ------------------ FOR ARRAYLIST PURPOSES -------------------- */

	//	// Function to save the AddressBook before exiting
	//	public void saveAddressBook() {
	//		try {
	//			//databaseFile.save(contacts); // save the Address Book before exiting
	//			System.out.println("Saving contacts and changes into database. . .");
	//			System.out.println("Closing Address Book");
	//		} catch (Exception ie) {
	//			System.out.println("Saved file. Goodbye!");
	//		}
	//		//System.exit(0); do not exit
	//	}
	//
	//	// Function to load the AddressBook after saving if user desires
	//	public void loadAddressBook() {
	//		try {
	//			System.out.println("Loading Address Book. . . ");
	//			System.out.println("---------ADDRESS BOOK----------");
	//			//databaseFile.loadAll();
	//			System.out.println("-------------------------------");
	//		} catch (IOException ie) {
	//			System.out.println("Address Book did not load up " + ie);
	//		}
	//
	//	}
}
