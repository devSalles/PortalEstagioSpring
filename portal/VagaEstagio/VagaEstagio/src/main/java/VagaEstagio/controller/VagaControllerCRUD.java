package VagaEstagio.controller;

import VagaEstagio.dto.vaga.VagaDTO;
import VagaEstagio.dto.vaga.VagaResponseDTO;
import VagaEstagio.model.VagaModel;
import VagaEstagio.service.VagaServiceCRUD;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vaga")
@Tag(name = "Vaga")
public class VagaControllerCRUD {

    private final VagaServiceCRUD vagaServiceCRUD;

    public VagaControllerCRUD(VagaServiceCRUD vagaServiceCRUD) {
        this.vagaServiceCRUD = vagaServiceCRUD;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addNew(@RequestBody VagaDTO vagaDTO)
    {
        VagaModel newVaga = this.vagaServiceCRUD.addNew(vagaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newVaga);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Object> updateById(@PathVariable Long id, @RequestBody VagaDTO vagaDTO)
    {
        return ResponseEntity.ok().body(this.vagaServiceCRUD.updateById(id,vagaDTO));
    }

    @GetMapping("/reportAll")
    public ResponseEntity<Object> getAll()
    {
        List<VagaResponseDTO> vagaModel=this.vagaServiceCRUD.getAll();
        return ResponseEntity.ok().body(vagaModel);
    }

    @GetMapping("/report/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id)
    {
        VagaResponseDTO vagaModel=this.vagaServiceCRUD.getById(id);
        return ResponseEntity.ok().body(vagaModel);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> removeById(@PathVariable Long id)
    {
        boolean remove = this.vagaServiceCRUD.deleteById(id);
        return ResponseEntity.ok().body(remove);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Object> removeAll()
    {
        this.vagaServiceCRUD.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("Todos os registros foram deletados");
    }
}
