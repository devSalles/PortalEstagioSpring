package VagaEstagio.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstagiarioDTO {

    @NotNull(message = "Nome obrigatório")
    private String nome;

    @NotNull(message = "Curso obrigatório")
    private String curso;

    @NotNull(message = "Periodo obrigatório")
    private int periodo;
}
