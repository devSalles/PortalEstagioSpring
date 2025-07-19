package VagaEstagio.controller;

import VagaEstagio.dto.VagaDTO;
import VagaEstagio.model.VagaModel;
import VagaEstagio.service.VagaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vaga")
public class VagaController {

    private final VagaService vagaService;

    public VagaController(VagaService vagaService) {
        this.vagaService = vagaService;
    }

    @PostMapping("/add")
    public ResponseEntity<Object>addNew(@RequestBody VagaDTO vagaDTO)
    {
        VagaModel newVaga = this.vagaService.addNew(vagaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newVaga);
    }
}
