package addressBook;

import java.io.IOException;
import java.util.Scanner;
import java.io.IOException;
import java.util.Scanner;

class AddressBook {
  Person[] contacts = new Person[100];
  int index = 0;
  SaveAndLoad databaseFile = new SaveAndLoad("Database.txt");

  public void addContact() throws IOException {
    Person person = new Person();
    Scanner input = new Scanner(System.in);

    // set firstName
    System.out.print("Enter contact's first name: ");
    person.setFirstName(input.nextLine());

    // set lastName
    System.out.print("Enter contact's last name: ");
    person.setLastName(input.nextLine());

    // set phoneNumber
    System.out.print("Enter contact's phone number: ");
    person.setPhoneNumber(input.nextLine());

    // set Address
    System.out.println("Enter contact's address (one field at a time - starting with street number, street name, city, state, zipcode): ");
    person.setAddress(input.nextLine(), input.nextLine(), input.nextLine(), input.nextLine(), input.nextLine());

    // add contact to the list
    contacts[index++] = person;
    System.out.println("Contact added to the list!");
    System.out.println();
  }

  // Function to display the contacts list in Address Book
  public void printAddressBook() throws IOException {
    System.out.println("---------ADDRESS BOOK----------");
    sortAddressBook(contacts);
    // Print out contacts' information on console
    for (int i = 0; i < index; i++) {
      if (contacts[i] == null) {
        System.out.println("Contacts currenty unavailable.");
      } else {
        System.out.print("Contact Name: " + contacts[i].getFirstName() + " ");
        System.out.print(contacts[i].getLastName() + " \n");
        System.out.println("Contact Phone number: " + contacts[i].getPhoneNumber() + " ");
        System.out.println();

        System.out.print("Contact Address: " + contacts[i].getAddress());

      }

      System.out.println("--------------------------------");
    }
  }

  // Function to search for a specific contact
  public void searchContact() {

    Scanner input = new Scanner(System.in);
    
    System.out.println("What's the first name of the contact you are trying to look up?: ");
    String firstName = input.nextLine();

    sortAddressBook(contacts); // sorting for the binarySearch to work
    int searchResult = binarySearch(contacts, firstName); // look for that firstName in your contact list

    if (searchResult == -1) {
      System.out.println("The person you are trying to update is currently not in the Address Book.\n");
    } else {
      System.out.print("Contact's Name: " + contacts[searchResult].getFirstName() + " ");
      System.out.print(contacts[searchResult].getLastName() + " \n");
      System.out.println("Contact's Phone Number: " + contacts[searchResult].getPhoneNumber() + " ");
      System.out.print("Contact Address: " + contacts[searchResult].getAddress());
      System.out.println();
    }

  }

  // Function to update contact
  public Person[] updateContact() {
    String lastName;
    String firstName;
    Scanner input = new Scanner(System.in);
    System.out.println("Who would you like to update (enter first name only): ");
    firstName = input.nextLine();

    sortAddressBook(contacts); // sorting for the binarySearch to work
    int searchResult = binarySearch(contacts, firstName); // look for that firstName in your contact list

    if (searchResult == -1) {
      System.out.println("The person you are trying to update is currently not in the Address Book.\n");
    } else {
      System.out.println("\nEnter the updated information below. . .\n");
      System.out.print("Enter contact's first name: ");
      contacts[searchResult].setFirstName(input.nextLine()); // update firstName

      System.out.print("Enter contact's last name: ");
      contacts[searchResult].setLastName(input.nextLine()); // set lastName

      System.out.print("Enter contact's phone number: ");
      contacts[searchResult].setPhoneNumber(input.nextLine()); // set phoneNumber)

      System.out.println("Enter contact's address (one field at a time - starting with street number, street name, city, state, zipcode): ");
      contacts[searchResult].setAddress(input.nextLine(), input.nextLine(), input.nextLine(), input.nextLine(), input.nextLine()); // set address
      System.out.println();

      firstName = contacts[searchResult].getFirstName();
      lastName = contacts[searchResult].getLastName();

      System.out.println("Updating " + firstName + " " + lastName + ". . .");

      System.arraycopy(contacts, 0, contacts, 0, searchResult);
    }
    return contacts;

  }
  
  // Function to delete contact
  public Person[] deleteContact() {
    String firstName;
    Scanner input = new Scanner(System.in);
    System.out.println("Who would you like to delete (enter first name only): ");
    firstName = input.nextLine();

    sortAddressBook(contacts); // sorting for the binarySearch to work
    int searchResult = binarySearch(contacts, firstName); // look for that firstName in your contact list

    if (searchResult == -1) {
      System.out.println("The person you are trying to delete is currently not in the Address Book.\n");
    } else {
      System.out.println("Deleting " + firstName + ". . .");
      // Using library to copy array but to itself and deleting the specified contact
      // at the specified index (searchResult)
      System.arraycopy(contacts, 0, contacts, 0, searchResult);
      System.arraycopy(contacts, searchResult + 1, contacts, searchResult, contacts.length - searchResult - 1);
      contacts[index] = contacts[index--];
    }
    return contacts;
  }

  // Function to sort Address Book alphabetically
  public void sortAddressBook(Person[] contact) {
    // Using a simple Bubble Sort function to sort out the contacts' first names in
    // the Address Book alphabetically
    for (int i = 0; i < index; i++) {
      for (int j = 0; j < index - i - 1; j++) {
        // if the contacts's firstName's first letter comes after the next contact's
        // firstName's first letter in the alphabet table, then swap contacts.
        if (contact[j].getFirstName().charAt(0) > contact[j + 1].getFirstName().charAt(0)) {
          Person temp = contact[j];
          contact[j] = contact[j + 1];
          contact[j + 1] = temp;
        }
      }
    }
  }

  // Function to search for the contact to delete and update contact
  public int binarySearch(Person[] contact, String firstName) {
    int first = 0, last = index;

    while (first <= last) {
      int middle = (first + last) / 2;
      int itsHere = firstName.compareTo((contact[middle].getFirstName()));

      if (itsHere == 0) // if the first index we look at is where the key-index we're looking for is,
                        // then,
        return middle; // return that index
      if (itsHere > 0) // if the key-index lies somewhere beyond the first index, then,
        first = middle + 1; // ignore the rest of the indices before the middle + 1 index
      else // else it's safe to assume that the key-index is somewhere before the middle
        last = middle - 1; // ignore the rest of the indices after mid - 1

      middle = (first + last) / 2; // update the mid point
    }
    return -1;
  }

  // Function to save the AddressBook before exiting
  public void saveAndExit() {
    try {
      databaseFile.save(contacts); // save the Address Book before exiting
      System.out.println("Saving contacts and changes into database. . .");
      System.out.println("Closing Address Book");
    } catch (Exception ie) {
      System.out.println("Saved file. Goodbye!");
    }
    System.exit(0);
  }

  // Function to load the AddressBook after saving if user desires
  public void loadAddressBook() {
    try {
      System.out.println("Loading Address Book. . . ");
      System.out.println("---------ADDRESS BOOK----------");
      databaseFile.loadAll();
      System.out.println("-------------------------------");
    } catch (IOException ie) {
      System.out.println("Address Book did not load up " + ie);
    }

  }
}
