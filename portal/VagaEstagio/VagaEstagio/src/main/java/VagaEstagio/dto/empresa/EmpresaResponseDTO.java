package VagaEstagio.dto;

import VagaEstagio.model.EmpresaModel;

public record EmpresaResponseDTO(
        String nome,
        String cnpj,
        String area
) {
    public static EmpresaResponseDTO toEmpresaResponseDTO(EmpresaModel model)
    {
        return new EmpresaResponseDTO(model.getNome(), model.getCnpj(), model.getArea());
    }
}
