package VagaEstagio.dto.empresa;

import VagaEstagio.model.EmpresaModel;

public record EmpresaResponseDTO(
        String nome,
        String cnpj,
        String area
) {
    public static EmpresaResponseDTO fromEmpresaResponseDTO(EmpresaModel model)
    {
        return new EmpresaResponseDTO(model.getNome(), model.getCnpj(), model.getArea());
    }
}
