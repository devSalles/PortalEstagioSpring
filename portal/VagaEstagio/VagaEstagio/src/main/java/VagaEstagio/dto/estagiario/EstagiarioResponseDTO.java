package VagaEstagio.dto.estagiario;

import VagaEstagio.model.EstagiarioModel;

public record EstagiarioResponseDTO(
        Long id,
        String nome,
        String curso,
        Integer periodo
){
    //Metodo responsável por exibição de dados
    public static EstagiarioResponseDTO fromEstagiarioResponseDTO(EstagiarioModel model)
    {
        return new EstagiarioResponseDTO(model.getId(),model.getNome(), model.getCurso(), model.getPeriodo());
    }
}
