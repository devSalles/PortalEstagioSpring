package VagaEstagio.controller;

import VagaEstagio.dto.estagiario.EstagiarioDTO;
import VagaEstagio.dto.estagiario.EstagiarioResponseDTO;
import VagaEstagio.model.EstagiarioModel;
import VagaEstagio.service.EstagiarioServiceCRUD;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estagiario")
public class EstagiarioControllerCRUD {

    private final EstagiarioServiceCRUD estagiarioServiceCRUD;

    public EstagiarioControllerCRUD(EstagiarioServiceCRUD estagiarioServiceCRUD) {
        this.estagiarioServiceCRUD = estagiarioServiceCRUD;
    }


    @PostMapping("/add")
    public ResponseEntity<Object> addNew(@RequestBody EstagiarioDTO estagiarioDTO) throws IllegalArgumentException
    {
        EstagiarioModel estagiarioCriado = this.estagiarioServiceCRUD.addNew(estagiarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(estagiarioCriado);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Object> updateById(@PathVariable Long id, @RequestBody EstagiarioDTO estagiarioDTO) throws IllegalArgumentException
    {
        EstagiarioModel estagiarioAtualizado=this.estagiarioServiceCRUD.updateById(id,estagiarioDTO);
        return ResponseEntity.status(HttpStatus.OK).body(estagiarioAtualizado);
    }

    @GetMapping("/reportAll")
    public ResponseEntity<Object> getById()
    {
        List<EstagiarioResponseDTO>estagiarioGetAll=this.estagiarioServiceCRUD.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(estagiarioGetAll);
    }

    @GetMapping("/report/{id}")
    public ResponseEntity<Object> getAll(@PathVariable Long id)
    {
        EstagiarioResponseDTO estagiarioID = this.estagiarioServiceCRUD.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(estagiarioID);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id)
    {
        Boolean estagiarioDel = this.estagiarioServiceCRUD.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(estagiarioDel);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Object> deleteAll()
    {
        this.estagiarioServiceCRUD.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("Todos foram deletados");
    }
}
