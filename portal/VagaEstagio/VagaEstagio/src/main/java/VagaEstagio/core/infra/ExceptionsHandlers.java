package VagaEstagio.core.infra;

import VagaEstagio.core.exception.CnpjDuplicadoException;
import VagaEstagio.core.exception.EmptyListException;
import VagaEstagio.core.exception.IdNotFoundException;
import VagaEstagio.core.exception.EstagiarioDuplicadoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandlers {

    //Exceção para tentativa de cadastrar o estagiario em mais de uma vaga
    @ExceptionHandler(EstagiarioDuplicadoException.class)
    public ResponseEntity<MessageRestError> vagaHandlerException(EstagiarioDuplicadoException ex)
    {
        MessageRestError messageRestError = new MessageRestError(HttpStatus.CONFLICT, ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(messageRestError);
    }

    //Exceção para CNPJ já cadastrado
    @ExceptionHandler(CnpjDuplicadoException.class)
    public ResponseEntity<MessageRestError> cnpjHandlerException(CnpjDuplicadoException ex)
    {
        MessageRestError messageRestError = new MessageRestError(HttpStatus.CONFLICT, ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(messageRestError);
    }

    //Tratamento de exceção para validações de dados
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MessageRestError> illegalArgumentsHandlers(IllegalArgumentException ex)
    {
        MessageRestError messageRestError = new MessageRestError(HttpStatus.BAD_REQUEST,ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageRestError);
    }

    //Tratamento de exceção para ID não encontrado
    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<MessageRestError> IdNotFoundHandlerException(IdNotFoundException ex)
    {
        MessageRestError messageRestError = new MessageRestError(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageRestError);
    }

    //Tratamento de exceção para nenhum registro cadastrado
    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<MessageRestError> EmptyListHandlerException(EmptyListException ex)
    {
        MessageRestError messageRestError = new MessageRestError(HttpStatus.NOT_FOUND,ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageRestError);
    }
    //Tratamento de exceção globais
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageRestError> globalHandlersException()
    {
        MessageRestError messageRestError= new MessageRestError(HttpStatus.INTERNAL_SERVER_ERROR,"Erro interno, tente novamente mais tarde");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageRestError);
    }
}
