package VagaEstagio.dto.estagiario;

import VagaEstagio.model.EstagiarioModel;
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

    public EstagiarioModel toEstagio()
    {
        EstagiarioModel estagiarioModel = new EstagiarioModel();

        estagiarioModel.setNome(this.nome);
        estagiarioModel.setCurso(this.curso);
        estagiarioModel.setPeriodo(this.periodo);

        return estagiarioModel;
    }


    public EstagiarioModel updateEstagio(EstagiarioModel estagiarioModel)
    {
        estagiarioModel.setNome(this.getNome());
        estagiarioModel.setCurso(this.getCurso());
        estagiarioModel.setPeriodo(this.getPeriodo());

        return estagiarioModel;
    }
}
