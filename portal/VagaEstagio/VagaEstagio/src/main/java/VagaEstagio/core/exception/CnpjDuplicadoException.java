package VagaEstagio.core.exception;

public class CnpjDuplicadoException extends RuntimeException {
    public CnpjDuplicadoException(String message) {
        super(message);
    }
    public CnpjDuplicadoException() {
        super("Já existe uma empresa cadastrada com esse CNPJ");
    }
}
