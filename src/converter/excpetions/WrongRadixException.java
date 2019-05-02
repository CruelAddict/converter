package converter.excpetions;

public class WrongRadixException extends RuntimeException{
    public WrongRadixException(String s) {
        super(s);
    }
}