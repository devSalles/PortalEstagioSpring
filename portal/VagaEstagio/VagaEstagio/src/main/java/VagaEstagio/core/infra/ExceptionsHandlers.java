package VagaEstagio.core.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandlers {

    //Tratamento de exceções globais
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageRestError> globalHandlersException(Exception exception)
    {
        MessageRestError messageRestError= new MessageRestError(HttpStatus.INTERNAL_SERVER_ERROR,exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageRestError);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MessageRestError> illegalArgumentsHandlers(IllegalArgumentException ex)
    {
        MessageRestError messageRestError = new MessageRestError(HttpStatus.BAD_REQUEST,ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageRestError);
    }
}
