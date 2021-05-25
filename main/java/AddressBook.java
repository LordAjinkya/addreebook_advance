import java.util.ArrayList;
import java.util.Scanner;

public class AddressBook {
    public static ArrayList<Contacts> data = new ArrayList<Contacts>();

    static Scanner sc = new Scanner(System.in);


    //get list of Contacts object in addressBook
    public static int Contactslist() {
        System.out.println("Enter first name:");
        String name = sc.nextLine();
        int index = 0;
        for (Contacts p : data) {
            if (p.getFirstName().equals(name)) {
                return index;
            }
            ++index;
        }
        return -1;
    }

    //created contact in address book
    public static void addContacts() {
        System.out.println("Enter first name");

        String firstName = sc.next();
        System.out.println("Enter Last Name : ");
        String lastName = sc.next();
        System.out.println("Enter Address : ");
        String address = sc.next();
        System.out.println("Enter City Name : ");
        String city = sc.next();
        System.out.println("Enter State: ");
        String state = sc.next();
        System.out.println("Enter Zip Code : ");
        String zipCode = sc.next();
        System.out.println("Enter Mobile number: ");
        String mobileNo = sc.next();
        System.out.println("Enter email : ");
        String email = sc.next();
        Contacts newEntry = new Contacts(firstName, lastName, city, state, address, email, zipCode, mobileNo);
        data.add(newEntry);
        for (Contacts Entry : data) {
            System.out.println(Entry);

        }
        System.out.println(firstName + " The Data Added Successfully To The  AddressBook.");
    }

    //edit contacts
    public static void editContacts() {
        int index = Contactslist();
        if (index != -1) {
            System.out.println("What Do You Want To Change.\n 1.FirstName  2.LastName 3.Address  4.city  5.State  6.Zip  7.Email  8.MobileNumber");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter new First name Code: ");
                    data.get(index).setFirstName(sc.nextLine());
                    break;
                case 2:
                    System.out.println("Enter new Last name Code: ");
                    data.get(index).setLastName(sc.nextLine());
                    break;
                case 3:
                    System.out.println("Enter new City: ");
                    data.get(index).setCity(sc.nextLine());
                    break;
                case 4:
                    System.out.println("Enter new State: ");
                    data.get(index).setState(sc.nextLine());
                    break;
                case 5:
                    System.out.println("Enter new Address: ");
                    data.get(index).setAddress(sc.nextLine());
                    break;
                case 6:
                    System.out.println("Enter new Zip Code: ");
                    data.get(index).setZip(sc.nextLine());
                    break;
                case 7:
                    System.out.println("Enter new email : ");
                    data.get(index).setEmail(sc.nextLine());
                    break;
                case 8:
                    System.out.println("Enter new Mobile Number: ");
                    data.get(index).setMobNo(sc.nextLine());
                    break;
                default:
                    System.out.println("No data found!");
            }
        } else {

            System.out.println("No such Contact found!");
        }
    }

}
