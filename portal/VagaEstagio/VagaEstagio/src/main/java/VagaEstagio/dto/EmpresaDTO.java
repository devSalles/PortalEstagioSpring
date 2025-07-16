package VagaEstagio.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaDTO {

    @NotNull(message = "Nome obrigatório")
    private String nome;

    @CNPJ @NotNull(message = "CNPJ obrigatório")
    private String cnpj;

    @NotNull(message = "Área obrigatória")
    private String area;
}
