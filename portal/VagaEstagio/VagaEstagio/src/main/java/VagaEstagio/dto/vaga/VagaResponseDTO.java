package VagaEstagio.dto;

import VagaEstagio.enums.VagaStatus;
import VagaEstagio.model.EmpresaModel;
import VagaEstagio.model.EstagiarioModel;
import VagaEstagio.model.VagaModel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public record VagaResponseDTO(
        Long id,
        String descricao,
        BigDecimal bolsa,
        VagaStatus vagaStatus,
        EmpresaResponseDTO empresaModel,
        EstagiarioResponseDTO estagiarioModel
) {

    public static VagaResponseDTO toVaga(VagaModel vagaModel)
    {
        return new VagaResponseDTO(vagaModel.getId(), vagaModel.getDescricao(), vagaModel.getBolsa(), vagaModel.getVaga(),
                vagaModel.getEmpresaModel() != null ? EmpresaResponseDTO.toEmpresaResponseDTO(vagaModel.getEmpresaModel()) : null)
    }
}
