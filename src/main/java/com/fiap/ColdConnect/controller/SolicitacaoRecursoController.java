package com.fiap.ColdConnect.controller;

import com.fiap.ColdConnect.dto.SolicitacaoRecursoDTO;
import com.fiap.ColdConnect.model.filter.SolicitacaoRecursoFilter;
import com.fiap.ColdConnect.service.SolicitacaoRecursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/solicitacoes")
@RequiredArgsConstructor
public class SolicitacaoRecursoController {

    private final SolicitacaoRecursoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SolicitacaoRecursoDTO criar(@RequestBody @Valid SolicitacaoRecursoDTO dto) {
        return service.criar(dto);
    }

    @GetMapping
    public ResponseEntity<Page<SolicitacaoRecursoDTO>> listar(
            SolicitacaoRecursoFilter filtro,
            Pageable pageable
    ) {
        return ResponseEntity.ok(service.buscarComFiltro(filtro, pageable));
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
