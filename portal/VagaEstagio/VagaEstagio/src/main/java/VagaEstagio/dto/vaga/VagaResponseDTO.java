package VagaEstagio.dto.vaga;

import VagaEstagio.dto.empresa.EmpresaResponseDTO;
import VagaEstagio.dto.estagiario.EstagiarioResponseDTO;
import VagaEstagio.enums.VagaStatus;
import VagaEstagio.model.VagaModel;

import java.math.BigDecimal;

public record VagaResponseDTO(
        Long id,
        String descricao,
        BigDecimal bolsa,
        VagaStatus vagaStatus,
        EmpresaResponseDTO empresaModel,
        EstagiarioResponseDTO estagiarioModel
) {

    public static VagaResponseDTO fromVaga(VagaModel vagaModel)
    {
        return new VagaResponseDTO(vagaModel.getId(), vagaModel.getDescricao(), vagaModel.getBolsa(), vagaModel.getVaga(),
                vagaModel.getEmpresaModel() != null ? EmpresaResponseDTO.fromEmpresaResponseDTO(vagaModel.getEmpresaModel()) : null,
                vagaModel.getEstagiarioModel()!=null ? EstagiarioResponseDTO.fromEstagiarioResponseDTO(vagaModel.getEstagiarioModel()): null);
    }
}
