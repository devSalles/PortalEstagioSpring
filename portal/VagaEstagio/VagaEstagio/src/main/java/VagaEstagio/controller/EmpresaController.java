package VagaEstagio.controller;

import VagaEstagio.dto.EmpresaDTO;
import VagaEstagio.model.EmpresaModel;
import VagaEstagio.service.EmpresaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateById(@PathVariable Long id, @RequestBody EmpresaDTO empresaDTO)
    {
        EmpresaModel empresaAtualizada = this.empresaService.updateById(id,empresaDTO);
        return ResponseEntity.status(HttpStatus.OK).body(empresaAtualizada);
    }

    @GetMapping("/todos")
    public ResponseEntity<Object> getAll()
    {
        List<EmpresaModel> empresaAll = this.empresaService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(empresaAll);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id)
    {
        EmpresaModel empresaID = this.empresaService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(empresaID);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object>deleteById(@PathVariable Long id)
    {
        Boolean empresaDel = this.empresaService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(empresaDel);
    }

    @DeleteMapping("/todos")
    public ResponseEntity<Object> deleteAll()
    {
        this.empresaService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("Todos os empregados foram excluidos");
    }
}
