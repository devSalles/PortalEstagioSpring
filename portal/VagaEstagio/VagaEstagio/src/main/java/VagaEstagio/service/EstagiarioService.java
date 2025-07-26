package VagaEstagio.service;

import VagaEstagio.core.exception.EmptyListException;
import VagaEstagio.core.exception.IdNotFoundException;
import VagaEstagio.dto.estagiario.EstagiarioDTO;
import VagaEstagio.dto.estagiario.EstagiarioResponseDTO;
import VagaEstagio.model.EstagiarioModel;
import VagaEstagio.repository.EstagiarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        EstagiarioModel estagiarioSalvar=estagiarioDTO.toEstagio();
        this.estagiarioRepository.save(estagiarioSalvar);
        return estagiarioSalvar;
    }

    public EstagiarioModel updateById(Long id, EstagiarioDTO estagiarioDTO)
    {
        Optional<EstagiarioModel>estagiarioID=this.estagiarioRepository.findById(id);

        if(estagiarioID.isEmpty())
        {
            throw new IdNotFoundException();
        }

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

        EstagiarioModel estagiarioModel=estagiarioID.get();
        estagiarioDTO.updateEstagio(estagiarioModel);
        this.estagiarioRepository.save(estagiarioModel);
        return estagiarioModel;
    }

    public List<EstagiarioResponseDTO> getAll()
    {
        List<EstagiarioModel>estagiarioAll=this.estagiarioRepository.findAll();
        if(estagiarioAll.isEmpty())
        {
            throw new EmptyListException();
        }
        return estagiarioAll.stream().map(EstagiarioResponseDTO::fromEstagiarioResponseDTO).toList();
    }

    public EstagiarioResponseDTO getById(Long id)
    {
        EstagiarioModel estagiarioID = this.estagiarioRepository.findById(id).orElseThrow(() -> new IdNotFoundException());
        return EstagiarioResponseDTO.fromEstagiarioResponseDTO(estagiarioID);
    }

    public Boolean deleteById(Long id)
    {
        Optional<EstagiarioModel>estagiarioID=this.estagiarioRepository.findById(id);

        if(estagiarioID.isEmpty())
        {
            throw new IdNotFoundException();
        }

        EstagiarioModel estagiarioModel=estagiarioID.get();
        this.estagiarioRepository.delete(estagiarioModel);
        return true;
    }

    public void deleteAll()
    {
        this.estagiarioRepository.deleteAll();
    }
}