package VagaEstagio.service;

import VagaEstagio.core.exception.CnpjDuplicadoException;
import VagaEstagio.core.exception.EmptyListException;
import VagaEstagio.core.exception.IdNotFoundException;
import VagaEstagio.dto.empresa.EmpresaDTO;
import VagaEstagio.dto.empresa.EmpresaResponseDTO;
import VagaEstagio.model.EmpresaModel;
import VagaEstagio.model.EstagiarioModel;
import VagaEstagio.model.VagaModel;
import VagaEstagio.repository.EmpresaRepository;
import VagaEstagio.repository.EstagiarioRepository;
import VagaEstagio.repository.VagaRepository;
import VagaEstagio.service.validator.EmpresaValidator;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmpresaServiceCRUD {

    private final EmpresaRepository empresaRepository;
    private final EstagiarioRepository estagiarioRepository;
    private final VagaRepository vagaRepository;

    public EmpresaServiceCRUD(EmpresaRepository empresaRepository, EstagiarioRepository estagiarioRepository, VagaRepository vagaRepository) {
        this.empresaRepository = empresaRepository;
        this.estagiarioRepository = estagiarioRepository;
        this.vagaRepository = vagaRepository;
    }

    public EmpresaModel addNew(EmpresaDTO empresaDTO) throws IllegalArgumentException
    {
        //Metodo para validação de campos
        EmpresaValidator.validatorCamps(empresaDTO);

        if (empresaRepository.existsByCnpj(empresaDTO.getCnpj())) {
            throw new CnpjDuplicadoException();
        }

        EmpresaModel empresaModel = empresaDTO.toEmpresa();
        this.empresaRepository.save(empresaModel);
        return empresaModel;
    }

    public EmpresaModel updateById(Long id, EmpresaDTO empresaDTO)
    {
        EmpresaModel empresaID = this.empresaRepository.findById(id).orElseThrow(IdNotFoundException::new);

        //Metodo para validação de campos
        EmpresaValidator.updateValidatorCamps(empresaDTO);

        empresaDTO.updateEmpresa(empresaID);
        this.empresaRepository.save(empresaID);
        return empresaID;
    }

    public List<EmpresaResponseDTO> getAll()
    {
        List<EmpresaModel> empresa = this.empresaRepository.findAll();
        if (empresa.isEmpty()) {
            throw new EmptyListException();
        }
        return empresa.stream().map(EmpresaResponseDTO::fromEmpresaResponseDTO).toList();
    }

    public EmpresaResponseDTO getById(Long id)
    {
        EmpresaModel empresa = this.empresaRepository.findById(id).orElseThrow(IdNotFoundException::new);
        return EmpresaResponseDTO.fromEmpresaResponseDTO(empresa);
    }

    public Boolean deleteById(Long id)
    {
        EmpresaModel empresaID = this.empresaRepository.findById(id).orElseThrow(IdNotFoundException::new);

        if (empresaID.getVagaModel() != null || !empresaID.getVagaModel().isEmpty()) {
            for (VagaModel vaga : empresaID.getVagaModel()) {
                EstagiarioModel estagiario = vaga.getEstagiarioModel();

                if (estagiario != null)
                {
                    estagiario.setVagaModel(null);
                    this.estagiarioRepository.save(estagiario);
                }
                this.vagaRepository.delete(vaga);
            }
        }

        this.empresaRepository.delete(empresaID);
        return true;
    }

    public void deleteAll() {
        List<EmpresaModel>empresa=this.empresaRepository.findAll();
        if(empresa.isEmpty())
        {
            throw new EmptyListException();
        }

        for(EmpresaModel empresaModel:empresa)
        {
            List<VagaModel> vaga=empresaModel.getVagaModel();
            if(vaga != null && !vaga.isEmpty())
            {
                for (VagaModel vagaModel:vaga)
                {
                    if(vagaModel.getEstagiarioModel() != null)
                    {
                        EstagiarioModel estagiarioModel = vagaModel.getEstagiarioModel();
                        estagiarioModel.setVagaModel(null);
                        this.estagiarioRepository.save(estagiarioModel);
                    }
                }
                vaga.clear();
            }
            this.empresaRepository.save(empresaModel);
        }
        this.empresaRepository.deleteAll();
    }
}