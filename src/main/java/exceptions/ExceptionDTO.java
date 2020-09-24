package exceptions;

public class ExceptionDTO extends Exception {
    
    private int code;
    private String message;

    public ExceptionDTO(int code, String description) {
        this.code = code;
        this.message = description;
    }   
}
