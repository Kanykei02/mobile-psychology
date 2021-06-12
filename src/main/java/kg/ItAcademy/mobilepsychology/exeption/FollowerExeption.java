package kg.ItAcademy.mobilepsychology.exeption;

public class FollowerExeption extends RuntimeException{
    private String message;

    public FollowerExeption(String message){
        super("К сожалению, это не допустимо: " + message);
    }
}
