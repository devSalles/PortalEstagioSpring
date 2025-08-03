package VagaEstagio.dto.empresa;

import VagaEstagio.model.EmpresaModel;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaUpdateDTO {

    @NotNull(message = "Nome obrigatório")
    private String nome;

    @NotNull(message = "Área obrigatória")
    private String area;

    public EmpresaModel updateEmpresa(EmpresaModel empresaModel)
    {
        empresaModel.setNome(this.getNome());
        empresaModel.setArea(this.getArea());

        return empresaModel;
    }
}
