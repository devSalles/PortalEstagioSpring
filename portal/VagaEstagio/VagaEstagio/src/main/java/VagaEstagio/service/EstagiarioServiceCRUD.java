package VagaEstagio.service;

import VagaEstagio.core.exception.EmptyListException;
import VagaEstagio.core.exception.IdNotFoundException;
import VagaEstagio.dto.estagiario.EstagiarioDTO;
import VagaEstagio.dto.estagiario.EstagiarioResponseDTO;
import VagaEstagio.model.EstagiarioModel;
import VagaEstagio.model.VagaModel;
import VagaEstagio.repository.EstagiarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EstagiarioServiceCRUD {

    private final EstagiarioRepository estagiarioRepository;

    public EstagiarioServiceCRUD(EstagiarioRepository estagiarioRepository) {
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

    public EstagiarioModel updateById(Long id, EstagiarioDTO estagiarioDTO) throws IllegalArgumentException
    {
        EstagiarioModel estagiarioID=this.estagiarioRepository.findById(id).orElseThrow(IdNotFoundException::new);

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

        estagiarioDTO.updateEstagio(estagiarioID);
        this.estagiarioRepository.save(estagiarioID);
        return estagiarioID;
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
        EstagiarioModel estagiarioID = this.estagiarioRepository.findById(id).orElseThrow(IdNotFoundException::new);
        return EstagiarioResponseDTO.fromEstagiarioResponseDTO(estagiarioID);
    }

    public Boolean deleteById(Long id)
    {
        EstagiarioModel estagiarioID=this.estagiarioRepository.findById(id).orElseThrow(IdNotFoundException::new);

        if(estagiarioID.getVagaModel() != null)
        {
            VagaModel vaga=estagiarioID.getVagaModel();

            estagiarioID.setVagaModel(null);
            vaga.setEstagiarioModel(null);

            this.estagiarioRepository.save(estagiarioID);
        }

        this.estagiarioRepository.delete(estagiarioID);
        return true;
    }

    public void deleteAll()
    {
        this.estagiarioRepository.deleteAll();
    }
}