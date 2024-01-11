import Exceptions.IncorrectPasswordException;
import Exceptions.InvalidEmailException;
import Exceptions.InvalidPhoneNumberException;
import Model.Role;
import Model.User;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws Exception {

        try{
           User user = new User();
           user.setPhone("+355692407540");

           user.setPassword("12dj");
        }catch(InvalidPhoneNumberException ex) {
            System.out.println(ex.getMessage());
        }catch(InvalidEmailException ex1){
            System.out.println(ex1.getMessage());
        }catch(IncorrectPasswordException ex){
            System.out.println(ex.getMessage());
        }
    }
    }
