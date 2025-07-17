package VagaEstagio.dto;

import VagaEstagio.model.EmpresaModel;
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

    public EmpresaModel toEmpresa()
    {
        EmpresaModel empresaModel = new EmpresaModel();

        empresaModel.setNome(this.nome);
        empresaModel.setArea(this.area);
        empresaModel.setCnpj(this.cnpj);

        return empresaModel;
    }

    public void fromEmpresa(EmpresaModel empresaModel)
    {
        this.nome= empresaModel.getNome();
        this.area= empresaModel.getArea();
        this.cnpj= empresaModel.getCnpj();
    }

    public EmpresaModel updateEmpresa(EmpresaModel empresaModel)
    {
        empresaModel.setNome(this.getNome());
        empresaModel.setArea(this.getArea());
        empresaModel.setCnpj(this.getCnpj());

        return empresaModel;
    }
}
