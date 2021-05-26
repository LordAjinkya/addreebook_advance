import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Addressbookmain {
    public static final String ADDRESS_BOOK_FILES="C:\\Users\\ajink\\IdeaProjects\\Addressbooksystem";
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

    public static void deleteContacts() {

        int index = Contactslist();

        if (index != -1) {

            Contacts p = data.remove(index);
            System.out.println("Contacts " + p.getFirstName() + " removed successfully.");
        } else {

            System.out.println("No one with these details found!");
        }
    }
    //check duplicate entry
    public void duplicateCheck(String first)
    {
        for (int k = 0; k < data.size(); k++)
        {
            String contactName = data.get(k).getFirstName();
            if (first.equals(contactName))
                System.out.println("This Person is Already Present");
            else {
                System.out.println("You can Add this Person");
                break;
            }
        }
    }
    //search person by state
    public static void searchPersonByState()
    {
        System.out.println("Enter the state name to search Person by state name");
        String userState = sc.nextLine();
        Dictionary Statewisedict = new Hashtable();
        data.stream().filter(map -> map.getState().contains(userState)).forEach(data -> Statewisedict.put(data.getFirstName(),userState));
        System.out.println("State Name: " + userState);
        for (Enumeration i = Statewisedict.keys(); i.hasMoreElements();)
        {
            System.out.println("Name : " + i.nextElement());
        }
    }
    //search person by city
    public static void searchPersonByCity()
    {
        System.out.println("Enter City name to search Person by city name");
        String userCity = sc.nextLine();
        Dictionary Citywisedict = new Hashtable();
        data.stream().filter(map -> map.getCity().contains(userCity)).forEach(data -> Citywisedict.put(data.getFirstName(),userCity));
        System.out.println("City Name: " + userCity);
        for (Enumeration i = Citywisedict.keys(); i.hasMoreElements();)
        {
            System.out.println("Name : " + i.nextElement());
        }
    }
    //count persons by city
    public static void countByCity(){
        System.out.println(data.stream().collect(Collectors.groupingBy((Contacts C) -> C.getCity())));
        System.out.println((data.stream().collect(Collectors.groupingBy((Contacts C) -> C.getCity(),Collectors.counting()))));
    }
    //search by state
    public static void countByState(){
        System.out.println(data.stream().collect(Collectors.groupingBy((Contacts C) ->C.getState(),Collectors.counting())));
    }

    //sort the persons in address book alphabetically
    public static void sortPerson()
    {
        System.out.println("------Names are sorted alphabetical order----");
        data.stream().sorted(Comparator.comparing(contactInfo -> contactInfo.getFirstName())).forEach(contactInfo -> System.out.println(contactInfo));
    }
    //create new file to read and write
    public static void CreateFile(){
        File myfile =new File("Persondetails.txt");
        try {
            myfile.createNewFile();
        } catch (IOException e) {
            System.out.println("Unable to create the file");
            e.printStackTrace();
        }
    }


    ///write data in the file
    public static void writeData() {
        try {
            FileWriter filewriter = new FileWriter("Persondetails.txt");//filewriter method used to write
            filewriter.write("Name:Ajinkya Narale \nAddress:Dhantoli \nCity:Katol \nState:MH \nZip:441302 \nPhoneNum:9172695674 \nEmail:ajinkyanarale007@gmail.com");
            filewriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /////Read data from the file
    public static void readData() {
        File myfile= new File("Persondetails.txt");
        try{
            Scanner sc = new Scanner(myfile);
            while(sc.hasNextLine()){
                String line= sc.nextLine();
                System.out.println(line);
            }
            sc.close();

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
///write data to csv
    public static void writeDataToCSV() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException{
        try (Writer writer = Files.newBufferedWriter(Paths.get("C:\\Users\\ajink\\IdeaProjects\\system_of_addressbook\\src\\main\\resources\\person_details.csv"));) {
            StatefulBeanToCsvBuilder<Contacts> builder = new StatefulBeanToCsvBuilder<>(writer);
            StatefulBeanToCsv<Contacts> beanWriter = builder.build();
            beanWriter.write(data);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    ///read to csv
    public static void readDataFromCSV() throws IOException {
        try (Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\ajink\\IdeaProjects\\system_of_addressbook\\src\\main\\resources\\person_details.csv"));
             CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();){
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                System.out.println("First Name = " + nextRecord[3]);
                System.out.println("Last Name = " + nextRecord[4]);
                System.out.println("Address = " + nextRecord[0]);
                System.out.println("City = " + nextRecord[1]);
                System.out.println("State = " + nextRecord[6]);
                System.out.println("Email = " + nextRecord[2]);
                System.out.println("Phone Number = " + nextRecord[5]);
                System.out.println("Zip Code = " + nextRecord[7]);
            }
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
    }



//main menu to operate all the functions performed in differeent methods
    public static void Menu() {
        int choice;
        while (true) {
            System.out.println("Enter your choice:");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addContacts();
                    break;
                case 2:
                    editContacts();
                    break;
                case 3:
                    deleteContacts();
                    break;
                case 4:
                    searchPersonByState();
                    break;
                case 5:
                    searchPersonByCity();
                    break;
                case 6:
                    countByCity();
                    break;
                case 7:
                    countByState();
                    break;
                case 8:
                    sortPerson();
                    break;
                case 9:
                    CreateFile();
                    break;
                case 10:
                    writeData();
                    break;
                case 11:
                    readData();
                    break;

                case 12:
                    try {
                        writeDataToCSV();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (CsvRequiredFieldEmptyException e) {
                        e.printStackTrace();
                    } catch (CsvDataTypeMismatchException e) {
                        e.printStackTrace();
                    }
                    break;
                case 13:
                    try {
                        readDataFromCSV();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 14:
                    System.exit(0);
                    break;


                default:
                    System.out.println("wrong input!");
            }
        }
    }

    public static void main(String[] args) throws Exception{
        //hashmap used for adding multiple address books
        HashMap<String,AddressBook> a = new HashMap<String,AddressBook>();
        System.out.println("Welcome to Address Book program");

        Scanner sc=new Scanner(System.in);
        int x=0;
        System.out.println("Write the number of the Address Books ");
        int z= sc.nextInt();
        for (int i=0; i< z; i++)
        {
            System.out.println("Enter name of Addressbook");
            String name = sc.next();
            AddressBook b = new AddressBook();
            a.put(name, b);
        }
        Addressbookmain addressBookMain = new Addressbookmain();
        addressBookMain.Menu();



    }

}
