import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddressBookTest {
  

    @Test
    public void givenAddressBookPersonsDetailsDB_WhenRetrieved_ShouldMatchCount()
    {
        AddressBookService addressBookService = new AddressBookService();
        List<Contacts> contactList = addressBookService.readPersonDetailsDataDB(AddressBookService.IOService.DB_IO);
        System.out.println(contactList.toString());
        Assertions.assertEquals(7,contactList.size());
    }

}
