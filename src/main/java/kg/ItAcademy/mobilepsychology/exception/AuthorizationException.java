package kg.ItAcademy.mobilepsychology.exception;

public class AuthorizationException extends RuntimeException{
    private String message;

    public AuthorizationException(){}

    public AuthorizationException(String message, Long id){
        super(message + id);
    }

    public AuthorizationException(String message){
        super(message);
    }
}
