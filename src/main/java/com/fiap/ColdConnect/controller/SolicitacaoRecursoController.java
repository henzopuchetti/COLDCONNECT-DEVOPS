package com.fiap.ColdConnect.controller;

import com.fiap.ColdConnect.dto.SolicitacaoRecursoDTO;
import com.fiap.ColdConnect.service.SolicitacaoRecursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitacoes")
@RequiredArgsConstructor
public class SolicitacaoRecursoController {

    private final SolicitacaoRecursoService service;

    @PostMapping
    public ResponseEntity<SolicitacaoRecursoDTO> criar(@RequestBody @Valid SolicitacaoRecursoDTO dto) {
        SolicitacaoRecursoDTO criado = service.criar(dto);
        return ResponseEntity.status(201).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<SolicitacaoRecursoDTO>> listar() {
        List<SolicitacaoRecursoDTO> lista = service.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitacaoRecursoDTO> buscar(@PathVariable Long id) {
        SolicitacaoRecursoDTO dto = service.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitacaoRecursoDTO> atualizar(@PathVariable Long id,
                                                          @RequestBody @Valid SolicitacaoRecursoDTO dto) {
        SolicitacaoRecursoDTO atualizado = service.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
