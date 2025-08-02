package VagaEstagio.service.validator;

import VagaEstagio.dto.vaga.VagaDTO;

import java.math.BigDecimal;

public class VagaValidator {

    //Metodo responsável por validar campos
    public static void validatorCamps(VagaDTO vagaDTO)
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
    }
}
