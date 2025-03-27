package com.gawa.contatos_app.web.controller;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
import com.gawa.contatos_app.entity.Contato;
import com.gawa.contatos_app.service.ContatoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/contatos")
@CrossOrigin(origins = "http://localhost:4200")

public class ContatoController {

    private final ContatoService contatoService;

    @PostMapping
    public ResponseEntity<Contato> create(@RequestBody Contato contato){
        Contato cont = contatoService.salvar(contato);
        return ResponseEntity.status(HttpStatus.CREATED).body(cont);
    }

    @GetMapping("/{contato_id}")
    public ResponseEntity<Contato> getById(@PathVariable long contato_id){
        Contato cont = contatoService.buscarPorId(contato_id);
        return ResponseEntity.ok(cont);
    }

    @GetMapping
    public ResponseEntity<List<Contato>> getAllContatos(){
        List<Contato> conts = contatoService.buscarTodos();
        return ResponseEntity.ok(conts);
    }

    @PatchMapping("/{contato_id}")
    public ResponseEntity<Contato> updateContato(@PathVariable Long contato_id, @RequestBody Map<String, Object> updates) {
        Contato contatoAtualizado = contatoService.atualizarParcialmente(contato_id, updates);
        return ResponseEntity.ok(contatoAtualizado);
    }

    @DeleteMapping("/{contato_id}")
    public ResponseEntity<String> deleteContato(@PathVariable Long contato_id) {
        String nomeContato = contatoService.deletar(contato_id);
        return ResponseEntity.noContent().build();
    }
}
