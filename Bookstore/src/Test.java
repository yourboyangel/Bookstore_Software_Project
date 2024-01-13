import Exceptions.InvalidIsbnException;
import Model.*;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws Exception{
        BinaryFileHandler<ArrayList<User>> bookBinaryFileHandler = new BinaryFileHandler<>();
        User book1=new User("admin1","Admin12345",Role.ADMINISTRATOR);

        ArrayList<User> list= new ArrayList<>();
        list.add(book1);

        bookBinaryFileHandler.writeObjectToFile(list,"user_data.dat",true);
    }
}
