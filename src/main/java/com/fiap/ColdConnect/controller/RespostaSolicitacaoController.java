package com.fiap.ColdConnect.controller;

import com.fiap.ColdConnect.dto.RespostaSolicitacaoDTO;
import com.fiap.ColdConnect.model.filter.RespostaSolicitacaoFilter;
import com.fiap.ColdConnect.service.RespostaSolicitacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/respostas")
@RequiredArgsConstructor
public class RespostaSolicitacaoController {

    private final RespostaSolicitacaoService service;

    @PostMapping
    public ResponseEntity<RespostaSolicitacaoDTO> criar(@RequestBody @Valid RespostaSolicitacaoDTO dto) {
        RespostaSolicitacaoDTO criado = service.criar(dto);
        return ResponseEntity.status(201).body(criado);
    }

    @GetMapping
    public ResponseEntity<Page<RespostaSolicitacaoDTO>> listarComFiltros(
            RespostaSolicitacaoFilter filtro,
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(service.listarComFiltros(filtro, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespostaSolicitacaoDTO> buscar(@PathVariable Long id) {
        RespostaSolicitacaoDTO dto = service.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespostaSolicitacaoDTO> atualizar(@PathVariable Long id,
                                                            @RequestBody @Valid RespostaSolicitacaoDTO dto) {
        RespostaSolicitacaoDTO atualizado = service.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
