package Exceptions;

public class InvalidEmailException extends Exception{
    public InvalidEmailException(){
    }
    @Override
    public String getMessage(){
        return "Invalid email address. Only gmail, yahoo and hotmail are supported!";
    }
}
