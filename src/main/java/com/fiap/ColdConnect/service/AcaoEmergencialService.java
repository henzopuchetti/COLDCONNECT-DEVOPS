package com.fiap.ColdConnect.service;

import com.fiap.ColdConnect.dto.AcaoEmergencialDTO;
import com.fiap.ColdConnect.mapper.AcaoEmergencialMapper;
import com.fiap.ColdConnect.model.AcaoEmergencial;
import com.fiap.ColdConnect.model.filter.AcaoEmergencialFilter;
import com.fiap.ColdConnect.repository.AcaoEmergencialRepository;
import com.fiap.ColdConnect.specification.AcaoEmergencialSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AcaoEmergencialService {

    private final AcaoEmergencialRepository repository;
    private final AcaoEmergencialMapper mapper;

    public AcaoEmergencialDTO criar(AcaoEmergencialDTO dto) {
        AcaoEmergencial entidade = mapper.toEntity(dto);
        AcaoEmergencial salvo = repository.save(entidade);
        return mapper.toDTO(salvo);
    }

    public List<AcaoEmergencialDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public AcaoEmergencialDTO buscarPorId(Long id) {
        AcaoEmergencial entidade = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ação Emergencial não encontrada com ID: " + id));
        return mapper.toDTO(entidade);
    }

    public AcaoEmergencialDTO atualizar(Long id, AcaoEmergencialDTO dtoAtualizado) {
        AcaoEmergencial existente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ação Emergencial não encontrada com ID: " + id));

        AcaoEmergencial atualizado = mapper.toEntity(dtoAtualizado);
        atualizado.setId(id);

        AcaoEmergencial salvo = repository.save(atualizado);
        return mapper.toDTO(salvo);
    }

    public void deletar(Long id) {
        AcaoEmergencial entidade = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ação Emergencial não encontrada com ID: " + id));
        repository.delete(entidade);
    }

        // Método novo de busca com filtro, paginação e ordenação
    public Page<AcaoEmergencialDTO> listarComFiltro(AcaoEmergencialFilter filtro, Pageable pageable) {
        Page<AcaoEmergencial> paginaEntidades = repository.findAll(
                AcaoEmergencialSpecification.filtroDinamico(filtro), pageable);

        List<AcaoEmergencialDTO> dtos = paginaEntidades.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtos, pageable, paginaEntidades.getTotalElements());
    }
}