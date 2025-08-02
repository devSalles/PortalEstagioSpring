package VagaEstagio.service;

import VagaEstagio.core.exception.EmptyListException;
import VagaEstagio.core.exception.IdNotFoundException;
import VagaEstagio.core.exception.EstagiarioDuplicadoException;
import VagaEstagio.dto.vaga.VagaDTO;
import VagaEstagio.dto.vaga.VagaResponseDTO;
import VagaEstagio.model.EmpresaModel;
import VagaEstagio.model.EstagiarioModel;
import VagaEstagio.model.VagaModel;
import VagaEstagio.repository.EmpresaRepository;
import VagaEstagio.repository.EstagiarioRepository;
import VagaEstagio.repository.VagaRepository;
import VagaEstagio.service.validator.VagaValidator;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
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
        //Metodo para validação de campos
        VagaValidator.validatorCamps(vagaDTO);

        //Verificação de campo nulo ID de estagiário
        if(vagaDTO.getEstagiarioModel() == null || vagaDTO.getEstagiarioModel().getId() == null)
        {
            throw new IllegalArgumentException("Campo ID de estagiario não pode ser nulo");
        }
        //Procura de ID de estagiário
        EstagiarioModel estagiarioID=this.estagiarioRepository.findById(vagaDTO.getEstagiarioModel().getId()).orElseThrow(() -> new IdNotFoundException("ID de estagiario não encontrado"));

        //Veirficação de duplicidade de ID de estagiário para mais de uma vaga
        if(vagaRepository.existsByEstagiarioModel_Id(estagiarioID.getId()))
        {
            throw new EstagiarioDuplicadoException();
        }

        //Verificação de campo nulo ID de empresa
        if(vagaDTO.getEmpresaModel() == null || vagaDTO.getEmpresaModel().getId() == null)
        {
            throw new IllegalArgumentException("Campo ID da empresa não pode ser nulo");
        }
        //Procura de ID de empresa
        EmpresaModel empresaID=this.empresaRepository.findById(vagaDTO.getEmpresaModel().getId()).orElseThrow(() -> new IdNotFoundException("ID de empresa não encontrado"));

        //Setar os ID correspondentes
        vagaDTO.setEstagiarioModel(estagiarioID);
        vagaDTO.setEmpresaModel(empresaID);

        VagaModel vagaSave=vagaDTO.toVaga();


        return this.vagaRepository.save(vagaSave);
    }

    public VagaModel updateById(Long id, VagaDTO vagaDTO)
    {
        VagaModel vagaID = this.vagaRepository.findById(id).orElseThrow(IdNotFoundException::new);

        //Metodo para validação de campos
        VagaValidator.validatorCamps(vagaDTO);

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

        //Atualiza a DTO com o ID correspondente
        vagaDTO.setEmpresaModel(empresaID);
        vagaDTO.setEstagiarioModel(estagiarioID);

        VagaModel vagaUpdate=vagaDTO.updateVaga(vagaID);

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
        VagaModel vaga =this.vagaRepository.findById(id).orElseThrow(IdNotFoundException::new);
        return VagaResponseDTO.fromVaga(vaga);
    }

    @Transactional
    public Boolean deleteById(Long id)
    {
        VagaModel vagaModel = this.vagaRepository.findById(id).orElseThrow(IdNotFoundException::new);

        if(vagaModel.getEstagiarioModel()!= null)
        {
            EstagiarioModel estagiarioModel = vagaModel.getEstagiarioModel();

            //Remove a referência da vaga no estagiário
            estagiarioModel.setVagaModel(null);

            //Atualiza o estagiário no banco de dados para refletir a remoção do vínculo
            this.estagiarioRepository.save(estagiarioModel);
        }

        EmpresaModel empresa=vagaModel.getEmpresaModel();
        if(empresa != null && empresa.getVagaModel() != null)
        {
            //Remove da lista de vagas da empresa a vaga que está sendo processada
            empresa.getVagaModel().removeIf(vaga -> vaga.getId().equals(id));

            //Atualiza a empresa no banco de dados para refletir a remoção da vaga
            this.empresaRepository.save(empresa);
        }
        this.vagaRepository.delete(vagaModel);
        return true;
    }

    public void deleteAll()
    {
        //Lista todas as vagas
        List<VagaModel>vagaDel=this.vagaRepository.findAll();
        if(vagaDel.isEmpty())
        {
            throw new EmptyListException();
        }

        //Percorre cada vaga presente na lista vagaDel
        for (VagaModel vaga : vagaDel)
        {
            if (vaga.getEstagiarioModel() != null)
            {
                EstagiarioModel estagiarioModel = vaga.getEstagiarioModel();

                //Remove a referência da vaga no estagiário
                estagiarioModel.setVagaModel(null);

                //Atualiza o estagiário no banco de dados para refletir a remoção do vínculo
                this.estagiarioRepository.save(estagiarioModel);
            }

            EmpresaModel empresa = vaga.getEmpresaModel();
            if (empresa != null && empresa.getVagaModel() != null)
            {
                //Remove da lista de vagas da empresa a vaga que está sendo processada
                empresa.getVagaModel().removeIf(vagas -> vagas.getId().equals(vaga.getId()));

                //Atualiza a empresa no banco de dados para refletir a remoção da vaga
                this.empresaRepository.save(empresa);
            }
        }
        this.vagaRepository.deleteAll();
    }
}