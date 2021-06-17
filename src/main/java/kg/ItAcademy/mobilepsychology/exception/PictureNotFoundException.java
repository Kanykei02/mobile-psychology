package kg.ItAcademy.mobilepsychology.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.OK)
public class PictureNotFoundException extends RuntimeException{
    private String message;

    public PictureNotFoundException(){}

    public PictureNotFoundException(String message, Long id){
        super(message + id);
    }

    public PictureNotFoundException(String message){
        super(message);
    }
}
