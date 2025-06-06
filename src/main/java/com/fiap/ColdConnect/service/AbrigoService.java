package com.fiap.ColdConnect.service;

import com.fiap.ColdConnect.dto.AbrigoDTO;
import com.fiap.ColdConnect.mapper.AbrigoMapper;
import com.fiap.ColdConnect.model.Abrigo;
import com.fiap.ColdConnect.repository.AbrigoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.fiap.ColdConnect.model.filter.AbrigoFilter;
import com.fiap.ColdConnect.specification.AbrigoSpecification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AbrigoService {

    private final AbrigoRepository abrigoRepository;
    private final AbrigoMapper abrigoMapper;

    public AbrigoDTO criar(AbrigoDTO dto) {
        Abrigo abrigo = abrigoMapper.toEntity(dto);
        Abrigo salvo = abrigoRepository.save(abrigo);
        return abrigoMapper.toDTO(salvo);
    }

    public List<AbrigoDTO> listarTodos() {
        return abrigoRepository.findAll()
                .stream()
                .map(abrigoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AbrigoDTO buscarPorId(Long id) {
        Abrigo abrigo = abrigoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Abrigo não encontrado com ID: " + id));
        return abrigoMapper.toDTO(abrigo);
    }

    public AbrigoDTO atualizar(Long id, AbrigoDTO dtoAtualizado) {
        Abrigo abrigoExistente = abrigoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Abrigo não encontrado com ID: " + id));

        Abrigo abrigoAtualizado = abrigoMapper.toEntity(dtoAtualizado);
        abrigoAtualizado.setId(id); // Garantir que o ID permaneça o mesmo

        Abrigo salvo = abrigoRepository.save(abrigoAtualizado);
        return abrigoMapper.toDTO(salvo);
    }

    public void deletar(Long id) {
        Abrigo abrigo = abrigoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Abrigo não encontrado com ID: " + id));
        abrigoRepository.delete(abrigo);
    }

    public Page<AbrigoDTO> listarComFiltros(AbrigoFilter filtro, Pageable pageable) {
    return abrigoRepository.findAll(AbrigoSpecification.comFiltros(filtro), pageable)
            .map(abrigoMapper::toDTO);
}

}
