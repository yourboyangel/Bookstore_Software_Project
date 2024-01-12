package Exceptions;

public class IncorrectPasswordException extends Exception{
    public IncorrectPasswordException() {
    }
    @Override
    public String getMessage(){
        return "Incorrect Username or Password!";
    }
}
