package com.addressbookjdbc;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDBService {
    private PreparedStatement addressBookPreparedStatement;
    private static AddressBookDBService addressBookDBService;
    private List<AddressBookData> addressBookData = new ArrayList<>();



     AddressBookDBService() {
    }


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
    public static AddressBookDBService getInstance() {
        if (addressBookDBService == null)
            addressBookDBService = new AddressBookDBService();
        return addressBookDBService;
    }


    public List<AddressBookData> readData() throws AddressBookException {
        String query="select * from addressbook";
        return getAddressBookDataUsingDB(query);
    }

    private List<AddressBookData> getAddressBookDataUsingDB(String sql) throws AddressBookException {
        List<AddressBookData> addressBookData;
        String query;
        query = "select * from addressbook";
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            addressBookData = this.getAddressBookDetails(resultSet);
        } catch (SQLException e) {
            throw new AddressBookException(e.getMessage(), AddressBookException.ExceptionType.DATABASE_EXCEPTION);
        }
        return addressBookData;
    }

    private List<AddressBookData> getAddressBookDetails(ResultSet resultSet) throws AddressBookException {
   //     List<AddressBookData> addressBookData = new ArrayList<>();
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

    private void prepareAddressBookStatement() throws AddressBookException {
        try {
            Connection connection = this.getConnection();
            String query = "select * from addressbook where first_name = ?";
            addressBookPreparedStatement = connection.prepareStatement(query);
        } catch (SQLException e) {
            throw new AddressBookException(e.getMessage(), AddressBookException.ExceptionType.DATABASE_EXCEPTION);
        }
    }
    public int updateAddressBookData(String firstname, String address) throws AddressBookException {
        String query = String.format("update addressbook set address = '%s' where first_name = '%s';", address, firstname);
        try (Connection connection = this.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            return preparedStatement.executeUpdate(query);
        } catch (SQLException e) {
            throw new AddressBookException(e.getMessage(), AddressBookException.ExceptionType.CONNECTION_FAILED);
        }
    }

    public List<AddressBookData> getAddressBookData(String First_Name) throws AddressBookException {
        if (this.addressBookPreparedStatement == null)
           this.prepareAddressBookStatement();
        try {
            addressBookPreparedStatement.setString(1, First_Name);
            ResultSet resultSet = addressBookPreparedStatement.executeQuery();
            addressBookData = this.getAddressBookDetails(resultSet);
        } catch (SQLException e) {
            throw new AddressBookException(e.getMessage(), AddressBookException.ExceptionType.CONNECTION_FAILED);
        }
        System.out.println(addressBookData);
        return addressBookData;
    }
//using localdate class
    public List<AddressBookData> getEmployeePayrollDataForDateRange(LocalDate startDate, LocalDate endDate) throws AddressBookException {
        String sql = String.format("SELECT * FROM  addressbook WHERE date_added BETWEEN '%s' AND '%s';", Date.valueOf(startDate), Date.valueOf(endDate));//passing the query in string format
        return this.getAddressBookDataUsingDB(sql);
    }

    public int getCountByCity(String city) throws AddressBookException {
        int count = 0;
        String sql = String.format("select * from addressbook where city = '%s' ;", city);//passing the query in string format
        addressBookData=this.getAddressBookDataUsingDB(sql);
        count = (int) addressBookData.stream().count();

        return count;
    }
}
