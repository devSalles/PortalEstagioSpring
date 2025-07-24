package VagaEstagio.dto.vaga;

import VagaEstagio.enums.VagaStatus;
import VagaEstagio.model.EmpresaModel;
import VagaEstagio.model.EstagiarioModel;
import VagaEstagio.model.VagaModel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VagaDTO {

    @NotNull(message = "Descrição obrigatório")
    private String descricao;

    @NotNull(message = "Bolsa obrigatória")
    @DecimalMin(value = "0.0", inclusive = false, message = "A bolsa deve ser maior que zero")
    private BigDecimal bolsa;

    @NotNull(message = "Nome obrigatório") @Enumerated(EnumType.STRING)
    private VagaStatus vaga;

    private EmpresaModel empresaModel;

    private EstagiarioModel estagiarioModel;


    public VagaModel toVaga()
    {
        VagaModel vagaModel = new VagaModel();

        vagaModel.setDescricao(this.descricao);
        vagaModel.setBolsa(this.bolsa);
        vagaModel.setVaga(this.vaga);
        vagaModel.setEmpresaModel(this.empresaModel);
        vagaModel.setEstagiarioModel(this.estagiarioModel);

        return vagaModel;
    }

    public VagaModel updateVaga(VagaModel vagaModel)
    {
        vagaModel.setDescricao(this.getDescricao());
        vagaModel.setBolsa(this.getBolsa());
        vagaModel.setVaga(this.getVaga());
        vagaModel.setEmpresaModel(this.getEmpresaModel());
        vagaModel.setEstagiarioModel(this.getEstagiarioModel());

        return vagaModel;
    }
}
