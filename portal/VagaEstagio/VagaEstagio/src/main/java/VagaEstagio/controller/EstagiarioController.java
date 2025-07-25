package VagaEstagio.controller;

import VagaEstagio.dto.estagiario.EstagiarioDTO;
import VagaEstagio.model.EstagiarioModel;
import VagaEstagio.service.EstagiarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estagiario")
public class EstagiarioController {

    private final EstagiarioService estagiarioService;

    public EstagiarioController(EstagiarioService estagiarioService) {
        this.estagiarioService = estagiarioService;
    }


    @PostMapping("/add")
    public ResponseEntity<Object> addNew(@RequestBody EstagiarioDTO estagiarioDTO)
    {
        EstagiarioModel estagiarioCriado = this.estagiarioService.addNew(estagiarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(estagiarioCriado);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Object> updateById(@PathVariable Long id, @RequestBody EstagiarioDTO estagiarioDTO)
    {
        EstagiarioModel estagiarioAtualizado=this.estagiarioService.updateById(id,estagiarioDTO);
        return ResponseEntity.status(HttpStatus.OK).body(estagiarioAtualizado);
    }

    @GetMapping("/reportAll")
    public ResponseEntity<Object> getById()
    {
        List<EstagiarioModel>estagiarioGetAll =this.estagiarioService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(estagiarioGetAll);
    }

    @GetMapping("reportById/{id}")
    public ResponseEntity<Object> getAll(@PathVariable Long id)
    {
        EstagiarioModel estagiarioID = this.estagiarioService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(estagiarioID);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id)
    {
        Boolean estagiarioDel = this.estagiarioService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(estagiarioDel);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Object> deleteAll()
    {
        this.estagiarioService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("Todos foram deletados");
    }
}
