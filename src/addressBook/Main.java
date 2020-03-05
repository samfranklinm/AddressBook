package addressBook;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

class Main {

  public static int choice;

  public static boolean getContinue() {
    System.out.print("Continue y/n? ");
    Scanner scan = new Scanner(System.in);
    // this is a boolean check
    return (scan.next().charAt(0) == 'y');
  }

  public static int menu() {

    System.out.println("\nAddress Book");
    System.out.println("-------------");
    System.out.println("1. Add a new contact");
    System.out.println("2. Update an existing contact");
    System.out.println("3. Delete a contact");
    System.out.println("4. List added contacts A-Z");
    System.out.println("5. Load up Address Book (Loads up saved Address Book only)");
    System.out.println("6. Look up a contact");
    Scanner scan = new Scanner(System.in);
    return scan.nextInt();
  }

  public static void main(String[] args) throws IOException {
	  
	  try {
		  Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressdb", "root", "samfranklinm");
		  Statement myStatement = myConn.createStatement();
		  ResultSet myRS = myStatement.executeQuery("SELECT * from persons");
		  
		  while (myRS.next()) {
			  System.out.println(myRS.getString("lastName") + ", " + myRS.getString("firstName"));
		  }
	  }catch(Exception exc){
		 exc.printStackTrace();
	  }

    AddressBook ab = new AddressBook();
    
    do {
      choice = menu();
      switch (choice) {
      case 1:
        ab.addContact();
       // ab.saveAddressBook();
        break;
      case 2:
        ab.updateContact();
       // ab.saveAddressBook();
        break;
      case 3:
        ab.deleteContact();
       // ab.saveAddressBook();
        break;
      case 4:
        ab.printAddressBook();
        break;
      case 5:
        ab.loadAddressBook();
        break;
      case 6:
        ab.searchContact();
        break;
      }

    } while (getContinue());

  }
}
