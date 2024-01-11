package Exceptions;

public class InvalidPhoneNumberException extends Exception{
    public InvalidPhoneNumberException(){

    }
    @Override
    public String getMessage(){
        return "Invalid phone number!";
    }
}
