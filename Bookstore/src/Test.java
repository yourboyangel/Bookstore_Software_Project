import Exceptions.InvalidIsbnException;
import Model.Book;

public class Test {
    public static void main(String[] args) throws Exception{
        Book book = new Book();
        try{
            book.setIsbn("123-45-67890-12");
        }catch(InvalidIsbnException ex){
            System.out.println(ex.getMessage());
        }
    }
}
