package addressBook;


class Person {
  private String firstName;
  private String lastName;
  private String phoneNumber;
  private String streetNumber;
  private String streetName;
  private String city;
  private String state;
  private String zipcode;

  // Initializer
  public Person() {
    firstName = "Jane";
    lastName = "Doe";
    phoneNumber = "xxx-xxx-xxxx";
    streetNumber = "123";
    streetName = "Main St.";
    city = "San Francisco";
    state = "CA";
    zipcode = "94941";
    
  }

  // setter for Address
  public void setAddress(String streetNumber, String streetName, String city, String state, String zipcode){
    this.streetNumber = streetNumber;
    this.streetName = streetName;
    this.city = city;
    this.state = state;
    this.zipcode = zipcode;
  }
  // getter for Address
  public String getAddress(){
    return (" \n\t\t " + streetNumber + " " + streetName + " \n\t\t " + city + ", " + state + "\n\t\t " + zipcode + "\n");
  }

  // setter for First Name
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  // getter for First Name
  public String getFirstName() {
    return firstName;
  }

  // setter for Last Name
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  // getter for Last Name
  public String getLastName() {
    return lastName;
  }

  // setter for Phone Number
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
  // getter for Phone Number
  public String getPhoneNumber() {
    return phoneNumber;
  }

  // Overriding the pre-written toString function for our own use
  @Override
  public String toString() {
    return ("Name: " + this.firstName + " " + this.lastName);
  }

}