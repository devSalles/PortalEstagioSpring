package VagaEstagio.dto.empresa;

import VagaEstagio.model.EmpresaModel;

public record EmpresaResponseDTO(
        Long id,
        String nome,
        String cnpj,
        String area
) {
    public static EmpresaResponseDTO fromEmpresaResponseDTO(EmpresaModel model)
    {
        return new EmpresaResponseDTO(model.getId(),model.getNome(), model.getCnpj(), model.getArea());
    }
}
