package VagaEstagio.core.exception;

public class CnpjDuplicateException extends RuntimeException {
    public CnpjDuplicateException(String message) {
        super(message);
    }
    public CnpjDuplicateException() {
        super("Já existe uma empresa cadastrada com esse CNPJ");
    }
}
