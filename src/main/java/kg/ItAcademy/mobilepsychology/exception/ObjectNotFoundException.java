package kg.ItAcademy.mobilepsychology.exception;


public class ObjectNotFoundException extends RuntimeException{
    private String message;

    public ObjectNotFoundException(){}

    public ObjectNotFoundException(String message, Long id){
        super(message + id);
    }
    public ObjectNotFoundException(String message, String username){
        super(message + username);
    }

    public ObjectNotFoundException(String message){
        super(message);
    }
}
