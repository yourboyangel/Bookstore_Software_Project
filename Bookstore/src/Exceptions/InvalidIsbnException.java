package Exceptions;

public class InvalidIsbnException extends Exception{
    public InvalidIsbnException(){
    }
    @Override
    public String getMessage(){
        return "Invalid ISBN format!";
    }
}
