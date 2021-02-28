package Errors;

public class SquareNotAvailableException extends Exception{
    public SquareNotAvailableException(String errorMessage) {
        super(errorMessage);
    }
}