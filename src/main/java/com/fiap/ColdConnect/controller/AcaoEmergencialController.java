package com.fiap.ColdConnect.controller;

import com.fiap.ColdConnect.dto.AcaoEmergencialDTO;
import com.fiap.ColdConnect.model.filter.AcaoEmergencialFilter;
import com.fiap.ColdConnect.service.AcaoEmergencialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/acoes")
@RequiredArgsConstructor
public class AcaoEmergencialController {

    private final AcaoEmergencialService service;

    @PostMapping
    public ResponseEntity<AcaoEmergencialDTO> criar(@RequestBody @Valid AcaoEmergencialDTO dto) {
        return ResponseEntity.ok(service.criar(dto));
    }

    @GetMapping
    public ResponseEntity<Page<AcaoEmergencialDTO>> listar(
            AcaoEmergencialFilter filtro,
            Pageable pageable
    ) {
        return ResponseEntity.ok(service.listarComFiltro(filtro, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcaoEmergencialDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AcaoEmergencialDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AcaoEmergencialDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
