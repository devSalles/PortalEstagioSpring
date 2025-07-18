package VagaEstagio.service;

import VagaEstagio.core.exception.EmptyListException;
import VagaEstagio.core.exception.IdNotFoundException;
import VagaEstagio.dto.EmpresaDTO;
import VagaEstagio.model.EmpresaModel;
import VagaEstagio.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public EmpresaModel addNew(EmpresaDTO empresaDTO) throws IllegalArgumentException
    {
        if(empresaDTO.getNome()==null || empresaDTO.getNome().isBlank())
        {
            throw new IllegalArgumentException("Campo nome inválido");
        }

        if(empresaDTO.getArea()==null || empresaDTO.getArea().isBlank())
        {
            throw new IllegalArgumentException("Campo Área inválido");
        }

        if(empresaDTO.getCnpj()==null || empresaDTO.getCnpj().isBlank() || !empresaDTO.getCnpj().matches("^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$"))
        {
            throw new IllegalArgumentException("Campo CNPJ inválido");
        }

        EmpresaModel empresaModel = empresaDTO.toEmpresa();
        this.empresaRepository.save(empresaModel);
        return empresaModel;
    }

    public EmpresaModel updateById(Long id , EmpresaDTO empresaDTO)
    {
        Optional<EmpresaModel>empresaID=this.empresaRepository.findById(id);
        if(empresaID.isEmpty())
        {
            throw new IdNotFoundException();
        }

        if(empresaDTO.getNome()==null || empresaDTO.getNome().isBlank())
        {
            throw new IllegalArgumentException("Campo nome inválido");
        }

        if(empresaDTO.getArea()==null || empresaDTO.getArea().isBlank())
        {
            throw new IllegalArgumentException("Campo Área inválido");
        }

        if(empresaDTO.getCnpj()==null || empresaDTO.getCnpj().isBlank() || !empresaDTO.getCnpj().matches("^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$"))
        {
            throw new IllegalArgumentException("Campo CNPJ inválido");
        }

        EmpresaModel empresaRecId=empresaID.get();
        empresaDTO.updateEmpresa(empresaRecId);
        this.empresaRepository.save(empresaRecId);
        return empresaRecId;
    }

    public List<EmpresaModel> getAll()
    {
        List<EmpresaModel>empresa=this.empresaRepository.findAll();
        if(empresa.isEmpty())
        {
            throw new EmptyListException();
        }
        return empresa;
    }

    public EmpresaModel getById(Long id)
    {
        return this.empresaRepository.findById(id).orElseThrow(() -> new IdNotFoundException());
    }

    public Boolean deleteById(Long id)
    {
        Optional<EmpresaModel>empresaID=this.empresaRepository.findById(id);
        if(empresaID.isEmpty())
        {
            throw new IdNotFoundException();
        }

        EmpresaModel empresaDel=empresaID.get();
        this.empresaRepository.delete(empresaDel);
        return true;
    }

    public void deleteAll()
    {
        this.empresaRepository.deleteAll();
    }
}
