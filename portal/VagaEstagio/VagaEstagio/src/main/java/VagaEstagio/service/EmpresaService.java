package VagaEstagio.service;

import VagaEstagio.core.exception.CnpjDuplicateException;
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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final EstagiarioRepository estagiarioRepository;
    private final VagaRepository vagaRepository;

    public EmpresaService(EmpresaRepository empresaRepository, EstagiarioRepository estagiarioRepository, VagaRepository vagaRepository) {
        this.empresaRepository = empresaRepository;
        this.estagiarioRepository = estagiarioRepository;
        this.vagaRepository = vagaRepository;
    }

    public EmpresaModel addNew(EmpresaDTO empresaDTO) throws IllegalArgumentException {
        if (empresaDTO.getNome() == null || empresaDTO.getNome().isBlank()) {
            throw new IllegalArgumentException("Campo nome inválido");
        }

        if (empresaDTO.getArea() == null || empresaDTO.getArea().isBlank()) {
            throw new IllegalArgumentException("Campo Área inválido");
        }

        if (empresaDTO.getCnpj() == null || empresaDTO.getCnpj().isBlank() || !empresaDTO.getCnpj().matches("^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$")) {
            throw new IllegalArgumentException("Campo CNPJ inválido");
        }

        if (empresaRepository.existsByCnpj(empresaDTO.getCnpj())) {
            throw new CnpjDuplicateException();
        }

        EmpresaModel empresaModel = empresaDTO.toEmpresa();
        this.empresaRepository.save(empresaModel);
        return empresaModel;
    }

    public EmpresaModel updateById(Long id, EmpresaDTO empresaDTO) {
        Optional<EmpresaModel> empresaID = this.empresaRepository.findById(id);
        if (empresaID.isEmpty()) {
            throw new IdNotFoundException();
        }

        if (empresaDTO.getNome() == null || empresaDTO.getNome().isBlank()) {
            throw new IllegalArgumentException("Campo nome inválido");
        }

        if (empresaDTO.getArea() == null || empresaDTO.getArea().isBlank()) {
            throw new IllegalArgumentException("Campo Área inválido");
        }

        EmpresaModel empresaRecId = empresaID.get();
        empresaDTO.updateEmpresa(empresaRecId);
        this.empresaRepository.save(empresaRecId);
        return empresaRecId;
    }

    public List<EmpresaResponseDTO> getAll() {
        List<EmpresaModel> empresa = this.empresaRepository.findAll();
        if (empresa.isEmpty()) {
            throw new EmptyListException();
        }
        return empresa.stream().map(EmpresaResponseDTO::fromEmpresaResponseDTO).toList();
    }

    public EmpresaResponseDTO getById(Long id) {
        EmpresaModel empresa = this.empresaRepository.findById(id).orElseThrow(() -> new IdNotFoundException());
        return EmpresaResponseDTO.fromEmpresaResponseDTO(empresa);
    }

    public Boolean deleteById(Long id) {
        Optional<EmpresaModel> empresaID = this.empresaRepository.findById(id);
        if (empresaID.isEmpty()) {
            throw new IdNotFoundException();
        }

        EmpresaModel empresaDel = empresaID.get();

        if (empresaDel.getVagaModel() != null || !empresaDel.getVagaModel().isEmpty()) {
            for (VagaModel vaga : empresaDel.getVagaModel()) {
                EstagiarioModel estagiario = vaga.getEstagiarioModel();

                if (estagiario != null)
                {
                    estagiario.setVagaModel(null);
                    this.estagiarioRepository.save(estagiario);
                }
                this.vagaRepository.delete(vaga);
            }
        }

        this.empresaRepository.delete(empresaDel);
        return true;
    }

    //Verificar esse metodo no postman
    public void deleteAll() {
        this.empresaRepository.deleteAll();
    }
}