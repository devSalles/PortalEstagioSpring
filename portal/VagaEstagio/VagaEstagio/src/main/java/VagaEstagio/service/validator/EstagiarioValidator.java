package VagaEstagio.service.validator;

import VagaEstagio.dto.estagiario.EstagiarioDTO;

public class EstagiarioValidator {

    public static void validatorCamps(EstagiarioDTO estagiarioDTO)
    {
        if(estagiarioDTO.getNome()==null || estagiarioDTO.getNome().isBlank())
        {
            throw new IllegalArgumentException("Campo nome inválido");
        }

        if(estagiarioDTO.getCurso() == null || estagiarioDTO.getCurso().isBlank())
        {
            throw new IllegalArgumentException("Campo curso inválido");
        }

        if(estagiarioDTO.getPeriodo()<=0)
        {
            throw new IllegalArgumentException("Periodo inválido");
        }
    }

}
