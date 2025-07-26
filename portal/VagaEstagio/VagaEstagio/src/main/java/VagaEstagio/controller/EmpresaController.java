package VagaEstagio.controller;

import VagaEstagio.dto.empresa.EmpresaDTO;
import VagaEstagio.dto.empresa.EmpresaResponseDTO;
import VagaEstagio.model.EmpresaModel;
import VagaEstagio.service.EmpresaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addNew(@RequestBody EmpresaDTO empresaDTO) throws IllegalArgumentException
    {
        this.empresaService.addNew(empresaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaDTO);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Object> updateById(@PathVariable Long id, @RequestBody EmpresaDTO empresaDTO)
    {
        EmpresaModel empresaAtualizada = this.empresaService.updateById(id,empresaDTO);
        return ResponseEntity.status(HttpStatus.OK).body(empresaAtualizada);
    }

    @GetMapping("/reportAll")
    public ResponseEntity<Object> getAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(this.empresaService.getAll());
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id)
    {
        EmpresaResponseDTO empresaID = this.empresaService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(empresaID);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object>deleteById(@PathVariable Long id)
    {
        Boolean empresaDel = this.empresaService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(empresaDel);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Object> deleteAll()
    {
        this.empresaService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("Todos os empregados foram excluídos");
    }
}
