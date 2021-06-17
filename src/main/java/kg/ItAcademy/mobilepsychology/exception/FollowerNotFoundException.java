package kg.ItAcademy.mobilepsychology.exception;


public class FollowerNotFoundException extends RuntimeException{
    private String message;

    public FollowerNotFoundException(){}

    public FollowerNotFoundException(String message, Long id){
        super(message + id);
    }

    public FollowerNotFoundException(String message){
        super(message);
    }
}
