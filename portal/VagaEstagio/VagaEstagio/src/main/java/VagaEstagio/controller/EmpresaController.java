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
    public ResponseEntity<Object> addNew(EmpresaDTO empresaDTO) throws Exception
    {
        this.empresaService.addNew(empresaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateById(Long id, @RequestBody EmpresaDTO empresaDTO)
    {
        this.empresaService.updateById(id,empresaDTO);
        return ResponseEntity.status(HttpStatus.OK).body(empresaDTO);
    }

    @GetMapping("/todos")
    public ResponseEntity<Object> getAll()
    {
        List<EmpresaModel> empresaAll = this.empresaService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(empresaAll);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(Long id)
    {
        EmpresaModel empresaID = this.empresaService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(empresaID);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object>deleteById(Long id)
    {
        Boolean empresaDel = this.empresaService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(empresaDel);
    }

    @DeleteMapping("/todos")
    public void deleteAll()
    {
        this.empresaService.deleteAll();
    }
}
