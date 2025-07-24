package VagaEstagio.dto;

import VagaEstagio.model.EstagiarioModel;

public record EstagiarioResponseDTO(
        String nome,
        String curso,
        Integer periodo
){
    public static EstagiarioResponseDTO toEstagiarioResponseDTO(EstagiarioModel model)
    {
        return new EstagiarioResponseDTO(model.getNome(), model.getCurso(), model.getPeriodo());
    }
}
