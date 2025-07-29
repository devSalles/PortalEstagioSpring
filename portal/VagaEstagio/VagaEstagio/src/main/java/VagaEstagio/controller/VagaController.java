package VagaEstagio.controller;

import VagaEstagio.dto.vaga.VagaDTO;
import VagaEstagio.dto.vaga.VagaResponseDTO;
import VagaEstagio.model.VagaModel;
import VagaEstagio.service.VagaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vaga")
public class VagaController {

    private final VagaService vagaService;

    public VagaController(VagaService vagaService) {
        this.vagaService = vagaService;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addNew(@RequestBody VagaDTO vagaDTO)
    {
        VagaModel newVaga = this.vagaService.addNew(vagaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newVaga);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Object> updateById(@PathVariable Long id, @RequestBody VagaDTO vagaDTO)
    {
        return ResponseEntity.ok().body(this.vagaService.updateById(id,vagaDTO));
    }

    @GetMapping("/reportAll")
    public ResponseEntity<Object> getAll()
    {
        List<VagaResponseDTO> vagaModel=this.vagaService.getAll();
        return ResponseEntity.ok().body(vagaModel);
    }

    @GetMapping("/report/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id)
    {
        VagaResponseDTO vagaModel=this.vagaService.getById(id);
        return ResponseEntity.ok().body(vagaModel);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> removeById(@PathVariable Long id)
    {
        boolean remove = this.vagaService.deleteById(id);
        return ResponseEntity.ok().body(remove);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Object> removeAll()
    {
        this.vagaService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("Todos os registros foram deletados");
    }
}
