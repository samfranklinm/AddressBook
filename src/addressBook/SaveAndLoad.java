package addressBook;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
  public void save(Person[] contacts) {

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
      for (int i = 0; i < contacts.length; i++) {
        writer.write("First Name: " + contacts[i].getFirstName() + "\n" + "Last Name: " + contacts[i].getLastName() + "\n"
            + "Phone Number: " + contacts[i].getPhoneNumber() + "\nAddress: " + contacts[i].getAddress() + "\n\n");
      }

    } catch (IOException e) {
      System.out.println(e);
    }
  }
  
  // Function to load Address Book
  public Person loadAll() throws IOException {

    Person people = new Person();
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line = "";
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
      }
    } catch (IOException ie) {
      System.out.println(ie);
    }
    return people;
  }
}
