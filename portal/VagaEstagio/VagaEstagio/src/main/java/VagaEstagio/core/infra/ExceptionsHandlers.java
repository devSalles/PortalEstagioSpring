package VagaEstagio.core.infra;

import VagaEstagio.core.exception.EmptyListException;
import VagaEstagio.core.exception.IdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandlers {

    //Tratamento de exceções globais
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageRestError> globalHandlersException()
    {
        MessageRestError messageRestError= new MessageRestError(HttpStatus.INTERNAL_SERVER_ERROR,"Erro interno, tente novamente mais tarde");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageRestError);
    }

    //Tratamento de exceções para validação de dados
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MessageRestError> illegalArgumentsHandlers(IllegalArgumentException ex)
    {
        MessageRestError messageRestError = new MessageRestError(HttpStatus.BAD_REQUEST,ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageRestError);
    }

    //Tratamento de exceções para ID não encontrado
    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<MessageRestError> IdNotFoundHandlerException(IdNotFoundException ex)
    {
        MessageRestError messageRestError = new MessageRestError(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageRestError);
    }

    //Tratamento de exceções para nenhum registro cadastrado
    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<MessageRestError> EmptyListHandlerException(EmptyListException ex)
    {
        MessageRestError messageRestError = new MessageRestError(HttpStatus.NOT_FOUND,ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageRestError);
    }
}
