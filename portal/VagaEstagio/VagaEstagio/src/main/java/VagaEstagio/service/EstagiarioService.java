package VagaEstagio.service;

import VagaEstagio.dto.EstagiarioDTO;
import VagaEstagio.model.EstagiarioModel;
import VagaEstagio.repository.EstagiarioRepository;
import org.springframework.stereotype.Service;

@Service
public class EstagiarioService {

    private final EstagiarioRepository estagiarioRepository;

    public EstagiarioService(EstagiarioRepository estagiarioRepository) {
        this.estagiarioRepository = estagiarioRepository;
    }

    public EstagiarioModel addNew(EstagiarioDTO estagiarioDTO) throws IllegalArgumentException
    {
        if(estagiarioDTO.getNome()==null || estagiarioDTO.getNome().isBlank())
        {
            throw new IllegalArgumentException();
        }

        if(estagiarioDTO.getCurso() == null || estagiarioDTO.getCurso().isBlank())
        {
            throw new IllegalArgumentException();
        }

        if()
        {
            throw new IllegalArgumentException();
        }
    }
}
