package VagaEstagio.dto.empresa;

import VagaEstagio.model.EmpresaModel;

public record EmpresaResponseDTO(
        Long id,
        String nome,
        String cnpj,
        String area
) {
    //Metodo responsável por exibição de dados
    public static EmpresaResponseDTO fromEmpresaResponseDTO(EmpresaModel model)
    {
        return new EmpresaResponseDTO(model.getId(),model.getNome(), model.getCnpj(), model.getArea());
    }
}
