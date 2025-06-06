package com.fiap.ColdConnect.controller;

import com.fiap.ColdConnect.dto.LeituraTemperaturaDTO;
import com.fiap.ColdConnect.model.filter.LeituraTemperaturaFilter;
import com.fiap.ColdConnect.service.LeituraTemperaturaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leituras")
@RequiredArgsConstructor
public class LeituraTemperaturaController {

    private final LeituraTemperaturaService service;

    @PostMapping
    public ResponseEntity<LeituraTemperaturaDTO> criar(@RequestBody @Valid LeituraTemperaturaDTO dto) {
        LeituraTemperaturaDTO criado = service.criar(dto);
        return ResponseEntity.status(201).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<LeituraTemperaturaDTO>> listarTodas() {
        List<LeituraTemperaturaDTO> lista = service.listarTodas();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeituraTemperaturaDTO> buscarPorId(@PathVariable Long id) {
        LeituraTemperaturaDTO dto = service.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/filtrar")
    public ResponseEntity<Page<LeituraTemperaturaDTO>> filtrar(
            @ModelAttribute LeituraTemperaturaFilter filtro,
            Pageable pageable
    ) {
        return ResponseEntity.ok(service.buscarComFiltro(filtro, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeituraTemperaturaDTO> atualizar(@PathVariable Long id,
                                                          @RequestBody @Valid LeituraTemperaturaDTO dto) {
        LeituraTemperaturaDTO atualizado = service.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
