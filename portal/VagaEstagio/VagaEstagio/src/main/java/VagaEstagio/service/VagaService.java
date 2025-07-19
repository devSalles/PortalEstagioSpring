package VagaEstagio.service;

import VagaEstagio.core.exception.IdNotFoundException;
import VagaEstagio.dto.VagaDTO;
import VagaEstagio.model.EmpresaModel;
import VagaEstagio.model.EstagiarioModel;
import VagaEstagio.model.VagaModel;
import VagaEstagio.repository.EmpresaRepository;
import VagaEstagio.repository.EstagiarioRepository;
import VagaEstagio.repository.VagaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class VagaService {

    private final EmpresaRepository empresaRepository;
    private final EstagiarioRepository estagiarioRepository;
    private final VagaRepository vagaRepository;

    public VagaService(EmpresaRepository empresaRepository, EstagiarioRepository estagiarioRepository, VagaRepository vagaRepository) {
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
            throw new IllegalArgumentException("Campo vaga inválido");
        }

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
        Optional<EstagiarioModel>estagiarioID=this.estagiarioRepository.findById(vagaDTO.getEstagiarioModel().getId());
        if(estagiarioID.isEmpty())
        {
            throw new IdNotFoundException();
        }

        //Verificação de campo nulo ID de empresa
        if(vagaDTO.getEmpresaModel() == null || vagaDTO.getEmpresaModel().getId() == null)
        {
            throw new IllegalArgumentException("Campo ID de empresa não pode ser nulo");
        }

        //Procura de ID de empresa
        Optional<EmpresaModel>empresaID=this.empresaRepository.findById(vagaDTO.getEmpresaModel().getId());
        if(empresaID.isEmpty())
        {
            throw new IdNotFoundException();
        }

        VagaModel vagaSave=vagaDTO.toVaga();

        //Settar os ID correspondentes
        vagaDTO.setEstagiarioModel(estagiarioID.get());
        vagaDTO.setEmpresaModel(empresaID.get());

        return this.vagaRepository.save(vagaSave);
    }

    public void deleteAll()
    {
        this.vagaRepository.deleteAll();
    }
}