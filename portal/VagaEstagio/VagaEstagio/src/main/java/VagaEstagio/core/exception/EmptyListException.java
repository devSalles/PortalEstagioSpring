package VagaEstagio.core.exception;

public class EmptyListException extends RuntimeException {
    public EmptyListException(String message) {
        super(message);
    }

    public EmptyListException() {
        super("Nenhum registro cadastrado");
    }
}
