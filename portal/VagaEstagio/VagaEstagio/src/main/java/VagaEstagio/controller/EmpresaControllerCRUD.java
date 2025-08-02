package VagaEstagio.controller;

import VagaEstagio.dto.empresa.EmpresaDTO;
import VagaEstagio.dto.empresa.EmpresaResponseDTO;
import VagaEstagio.model.EmpresaModel;
import VagaEstagio.service.EmpresaServiceCRUD;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresa")
@Tag(name = "Empresa")
public class EmpresaControllerCRUD {

    private final EmpresaServiceCRUD empresaServiceCRUD;

    public EmpresaControllerCRUD(EmpresaServiceCRUD empresaServiceCRUD) {
        this.empresaServiceCRUD = empresaServiceCRUD;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addNew(@RequestBody EmpresaDTO empresaDTO) throws IllegalArgumentException
    {
        this.empresaServiceCRUD.addNew(empresaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaDTO);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Object> updateById(@PathVariable Long id, @RequestBody EmpresaDTO empresaDTO)
    {
        EmpresaModel empresaAtualizada = this.empresaServiceCRUD.updateById(id,empresaDTO);
        return ResponseEntity.status(HttpStatus.OK).body(empresaAtualizada);
    }

    @GetMapping("/reportAll")
    public ResponseEntity<Object> getAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(this.empresaServiceCRUD.getAll());
    }

    @GetMapping("/report/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id)
    {
        EmpresaResponseDTO empresaID = this.empresaServiceCRUD.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(empresaID);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object>deleteById(@PathVariable Long id)
    {
        Boolean empresaDel = this.empresaServiceCRUD.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(empresaDel);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Object> deleteAll()
    {
        this.empresaServiceCRUD.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("Todos as empresas foram excluídas");
    }
}
