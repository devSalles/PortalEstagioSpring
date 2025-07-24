package VagaEstagio.dto.estagiario;

import VagaEstagio.model.EstagiarioModel;

public record EstagiarioResponseDTO(
        String nome,
        String curso,
        Integer periodo
){
    public static EstagiarioResponseDTO fromEstagiarioResponseDTO(EstagiarioModel model)
    {
        return new EstagiarioResponseDTO(model.getNome(), model.getCurso(), model.getPeriodo());
    }
}
