package VagaEstagio.dto;

import VagaEstagio.enums.VagaStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VagaDTO {

    @NotNull(message = "Descrição obrigatório")
    private String descricao;

    @NotNull(message = "Bolsa obrigatória")
    private Integer bolsa;

    @NotNull(message = "Nome obrigatório") @Enumerated(EnumType.STRING)
    private VagaStatus vaga;
}
