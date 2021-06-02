package com.addressbookjdbc;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDBService {
    AddressBookDBService() {
    }
//create connection with database in mysql
    private Connection getConnection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/address_book_service?useSSL=false";
        String username = "root";
        String password = "Ajinkya@99";
        Connection con;
        System.out.println("Connecting to database:" + jdbcURL);
        con = DriverManager.getConnection(jdbcURL, username, password);
        System.out.println("Connection is successful:" + con);
        return con;

    }
//pass the address book datato read in the list
    public List<AddressBookData> readData() throws AddressBookException {
        String query="select * from addressbook";
        return getAddressBookDataUsingDB(query);
    }

    private List<AddressBookData> getAddressBookDataUsingDB(String sql) throws AddressBookException {
        List<AddressBookData> addressBookData;
        try (Connection connection = this.getConnection()) {
            
            Statement statement = connection.createStatement();// Statement interface to provide methods to execute queries with the database
            ResultSet resultSet = statement.executeQuery(sql); //returns the object of ResultSet.
            addressBookData = this.getAddressBookDetails(resultSet);
        } catch (SQLException e) {
            throw new AddressBookException(e.getMessage(), AddressBookException.ExceptionType.DATABASE_EXCEPTION);
        }
        return addressBookData;
    }

    private List<AddressBookData> getAddressBookDetails(ResultSet resultSet) throws AddressBookException {
        List<AddressBookData> addressBookData = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String First_Name = resultSet.getString("First_Name");
                String Last_Name = resultSet.getString("Last_Name");
                String Address = resultSet.getString("Address");
                String City = resultSet.getString("City");
                String State = resultSet.getString("State");
                BigDecimal Zip = resultSet.getBigDecimal("Zip");
                BigDecimal Phone_Number = resultSet.getBigDecimal("Phone_Number");
                String Email = resultSet.getString("Email");
                addressBookData.add(new AddressBookData(First_Name, Last_Name, Address, City, State, Zip, Phone_Number, Email));
            }
        } catch (SQLException e) {
            throw new AddressBookException(e.getMessage(), AddressBookException.ExceptionType.DATABASE_EXCEPTION);
        }
        return addressBookData;
    }
}
