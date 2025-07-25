package VagaEstagio.dto.estagiario;

import VagaEstagio.model.EstagiarioModel;

public record EstagiarioResponseDTO(
        Long id,
        String nome,
        String curso,
        Integer periodo
){
    public static EstagiarioResponseDTO fromEstagiarioResponseDTO(EstagiarioModel model)
    {
        return new EstagiarioResponseDTO(model.getId(),model.getNome(), model.getCurso(), model.getPeriodo());
    }
}
