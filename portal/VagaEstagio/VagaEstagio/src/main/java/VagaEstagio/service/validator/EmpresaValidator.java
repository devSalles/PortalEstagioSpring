package VagaEstagio.service.validator;

import VagaEstagio.dto.empresa.EmpresaDTO;
import VagaEstagio.dto.empresa.EmpresaUpdateDTO;

public class EmpresaValidator {

    //Metodo responsável por validar campos
    public static void validatorCamps(EmpresaDTO empresaDTO)
    {
        if (empresaDTO.getNome() == null || empresaDTO.getNome().isBlank()) {
            throw new IllegalArgumentException("Campo nome inválido");
        }

        if (empresaDTO.getArea() == null || empresaDTO.getArea().isBlank()) {
            throw new IllegalArgumentException("Campo Área inválido");
        }

        //Regex para verificação de CNPJ
        if (empresaDTO.getCnpj() == null || empresaDTO.getCnpj().isBlank() || !empresaDTO.getCnpj().matches("^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$")) {
            throw new IllegalArgumentException("Campo CNPJ inválido");
        }
    }

    //Metodo responsável por validar campos quando atualiza um registro
    public static void updateValidatorCamps(EmpresaUpdateDTO empresaDTO)
    {
        if (empresaDTO.getNome() == null || empresaDTO.getNome().isBlank()) {
            throw new IllegalArgumentException("Campo nome inválido");
        }

        if (empresaDTO.getArea() == null || empresaDTO.getArea().isBlank()) {
            throw new IllegalArgumentException("Campo Área inválido");
        }
    }
}
