package VagaEstagio.service;

import VagaEstagio.core.exception.EmptyListException;
import VagaEstagio.core.exception.IdNotFoundException;
import VagaEstagio.dto.vaga.VagaDTO;
import VagaEstagio.dto.vaga.VagaResponseDTO;
import VagaEstagio.model.EmpresaModel;
import VagaEstagio.model.EstagiarioModel;
import VagaEstagio.model.VagaModel;
import VagaEstagio.repository.EmpresaRepository;
import VagaEstagio.repository.EstagiarioRepository;
import VagaEstagio.repository.VagaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class VagaServiceCRUD {

    private final EmpresaRepository empresaRepository;
    private final EstagiarioRepository estagiarioRepository;
    private final VagaRepository vagaRepository;

    public VagaServiceCRUD(EmpresaRepository empresaRepository, EstagiarioRepository estagiarioRepository, VagaRepository vagaRepository) {
        this.empresaRepository = empresaRepository;
        this.estagiarioRepository = estagiarioRepository;
        this.vagaRepository = vagaRepository;
    }


    public VagaModel addNew(VagaDTO vagaDTO) throws IllegalArgumentException
    {
        if(vagaDTO.getDescricao() == null || vagaDTO.getDescricao().isBlank())
        {
            throw new IllegalArgumentException("Campo descrição inválido");
        }

        if(vagaDTO.getVaga() == null)
        {
            throw new IllegalArgumentException("Campo vaga inválido");
        }

        if(vagaDTO.getBolsa() == null )
        {
            throw new IllegalArgumentException("Campo bolsa inválido");
        }

        //Verifição de campo valor valido
        if(vagaDTO.getBolsa().compareTo(BigDecimal.ZERO)<=0)
        {
            throw new IllegalArgumentException("Valor deve ser maior que 0");
        }

        //Verificação de campo nulo ID de estagiário
        if(vagaDTO.getEstagiarioModel() == null || vagaDTO.getEstagiarioModel().getId() == null)
        {
            throw new IllegalArgumentException("Campo ID de estagiario não pode ser nulo");
        }
        //Procura de ID de estagiário
        EstagiarioModel estagiarioID=this.estagiarioRepository.findById(vagaDTO.getEstagiarioModel().getId()).orElseThrow(() -> new IdNotFoundException("ID de estagiario não encontrado"));


        //Verificação de campo nulo ID de empresa
        if(vagaDTO.getEmpresaModel() == null || vagaDTO.getEmpresaModel().getId() == null)
        {
            throw new IllegalArgumentException("Campo ID da empresa não pode ser nulo");
        }
        //Procura de ID de empresa
        EmpresaModel empresaID=this.empresaRepository.findById(vagaDTO.getEmpresaModel().getId()).orElseThrow(() -> new IdNotFoundException("ID de empresa não encontrado"));

        //Settar os ID correspondentes
        vagaDTO.setEstagiarioModel(estagiarioID);
        vagaDTO.setEmpresaModel(empresaID);

        VagaModel vagaSave=vagaDTO.toVaga();


        return this.vagaRepository.save(vagaSave);
    }

    public VagaModel updateById(Long id, VagaDTO vagaDTO)
    {
        VagaModel vagaID = this.vagaRepository.findById(id).orElseThrow(IdNotFoundException::new);

        if(vagaDTO.getVaga() == null)
        {
            throw new IllegalArgumentException("Campo vaga inválido");
        }

        if(vagaDTO.getBolsa() == null )
        {
            throw new IllegalArgumentException("Campo bolsa inválido");
        }

        //Verifição de campo valor valido
        if(vagaDTO.getBolsa().compareTo(BigDecimal.ZERO)<=0)
        {
            throw new IllegalArgumentException("Valor deve ser maior que 0");
        }

        //Verifição de campo nulo de ID de estagiário
        if(vagaDTO.getEstagiarioModel() == null || vagaDTO.getEstagiarioModel().getId() == null)
        {
            throw new IllegalArgumentException("Campo ID de estagiario obrigatorio");
        }
        EstagiarioModel estagiarioID = this.estagiarioRepository.findById(vagaDTO.getEstagiarioModel().getId()).orElseThrow(() -> new IdNotFoundException("ID de estagiario não encontrado"));

        //Verifição de campo nulo de ID de empresa
        if(vagaDTO.getEmpresaModel() == null || vagaDTO.getEmpresaModel().getId() == null)
        {
            throw new IllegalArgumentException("Campo ID da empresa obrigatorio");
        }
        EmpresaModel empresaID = this.empresaRepository.findById(vagaDTO.getEmpresaModel().getId()).orElseThrow(() -> new IdNotFoundException("ID de empresa não encontrado"));

        VagaModel vagaUpdate=vagaDTO.updateVaga(vagaID);
        vagaDTO.setEmpresaModel(empresaID);
        vagaDTO.setEstagiarioModel(estagiarioID);

        this.vagaRepository.save(vagaUpdate);
        return  vagaUpdate;
    }

    public List<VagaResponseDTO> getAll()
    {
        List<VagaModel>vaga=this.vagaRepository.findAll();
        if(vaga.isEmpty())
        {
            throw new EmptyListException();
        }

        return vaga.stream().map(VagaResponseDTO::fromVaga).toList();
    }

    public VagaResponseDTO getById(Long id)
    {
        VagaModel vaga =this.vagaRepository.findById(id).orElseThrow(() -> new IdNotFoundException());
        return VagaResponseDTO.fromVaga(vaga);
    }

    @Transactional
    public Boolean deleteById(Long id)
    {
        VagaModel vagaModel = this.vagaRepository.findById(id).orElseThrow(IdNotFoundException::new);

        if(vagaModel.getEstagiarioModel()!= null)
        {
            EstagiarioModel estagiarioModel = vagaModel.getEstagiarioModel();
            estagiarioModel.setVagaModel(null);
            this.estagiarioRepository.save(estagiarioModel);
        }

        EmpresaModel empresa=vagaModel.getEmpresaModel();
        if(empresa != null && empresa.getVagaModel() != null)
        {
            empresa.getVagaModel().removeIf(vaga -> vaga.getId().equals(id));
            this.empresaRepository.save(empresa);
        }

        this.vagaRepository.delete(vagaModel);
        return true;

    }

    public void deleteAll()
    {
        List<VagaModel>vagaDel=this.vagaRepository.findAll();
        if(vagaDel.isEmpty())
        {
            throw new EmptyListException();
        }

        for(VagaModel vaga:vagaDel)
        {
            if(vaga.getEstagiarioModel() != null)
            {
                EstagiarioModel estagiarioModel=vaga.getEstagiarioModel();
                estagiarioModel.setVagaModel(null);
                this.estagiarioRepository.save(estagiarioModel);
            }

            EmpresaModel empresa=vaga.getEmpresaModel();
            if(empresa != null && empresa.getVagaModel() != null)
            {
                empresa.getVagaModel().removeIf(vagas -> vagas.getId().equals(vagas.getId()));
                this.empresaRepository.save(empresa);

            }
        }

        this.vagaRepository.deleteAll();
    }
}