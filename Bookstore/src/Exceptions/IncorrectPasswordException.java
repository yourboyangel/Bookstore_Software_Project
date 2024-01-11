package Exceptions;

public class IncorrectPasswordException extends Exception{
    public IncorrectPasswordException() {
    }
    @Override
    public String getMessage(){
        return "The password must be at least 8 characters long and it must contain an Uppercase letter, Lowercase letter and Digit!";
    }
}
