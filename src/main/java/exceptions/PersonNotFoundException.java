package exceptions;

public class PersonNotFoundException extends Exception {
    public PersonNotFoundException(String msg) {
        super(msg);
    }
}
