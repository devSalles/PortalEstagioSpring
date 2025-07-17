package VagaEstagio.core.exception;

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(String message) {
        super(message);
    }
    public IdNotFoundException() {
        super("Id não encontrado");
    }
}
