public class Contacts {
    public boolean equals(Contacts check){
        return (check.firstName.equalsIgnoreCase(this.firstName)&&check.lastName.equalsIgnoreCase(this.lastName));
    }
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String mobileNo;
    private String email;

    public Contacts(String firstName, String lastName, String address, String city, String state, String zip,String mobileNo, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.mobileNo = mobileNo;
        this.email = email;
    }

    //Getter and setter Method
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getMobNo() {
        return mobileNo;
    }

    public void setMobNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //toString method returns objects
    @Override
    public String toString() {
        return "Contacts{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip=" + zip +
                ", mobNo='" + mobileNo + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
    public Contacts(String ajinkya, String narale, String dhantoli, String bhandara, String katol, int i, String s) {

    }
    public void showContactsDetails(){
        System.out.println("First name => "+ this.getFirstName());
        System.out.println("Last  name => "+ this.getLastName());
        System.out.println("Address => "+ this.getAddress());
        System.out.println("City => " + this.getCity());
        System.out.println("State => "+ this.getState());
        System.out.println("Zip Code => "+ this.getZip());
        System.out.println("Mobile Number => "+ this.getMobNo());
        System.out.println("Address => "+ this.getEmail());
    }
}
