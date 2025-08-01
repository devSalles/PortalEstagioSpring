package VagaEstagio.core.exception;

public class EstagiarioDuplicadoException extends RuntimeException {
    public EstagiarioDuplicadoException(String message) {
        super(message);
    }
    public EstagiarioDuplicadoException() {
        super("Esse estágiario já está cadastrado em uma vaga");
    }
}
