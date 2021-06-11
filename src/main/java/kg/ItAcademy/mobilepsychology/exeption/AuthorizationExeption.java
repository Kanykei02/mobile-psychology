package kg.ItAcademy.mobilepsychology.exeption;

public class AuthorizationExeption extends RuntimeException{
    private String message;

    public AuthorizationExeption(String message){
        super("К сожалению, это не допустимо: " + message);
    }
}
