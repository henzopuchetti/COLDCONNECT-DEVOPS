// 3. Atualização em SolicitacaoRecursoService.java
package com.fiap.ColdConnect.service;

import com.fiap.ColdConnect.dto.SolicitacaoRecursoDTO;
import com.fiap.ColdConnect.mapper.SolicitacaoRecursoMapper;
import com.fiap.ColdConnect.model.Abrigo;
import com.fiap.ColdConnect.model.AlertaFrio;
import com.fiap.ColdConnect.model.SolicitacaoRecurso;
import com.fiap.ColdConnect.model.enums.StatusSolicitacao;
import com.fiap.ColdConnect.model.filter.SolicitacaoRecursoFilter;
import com.fiap.ColdConnect.repository.AbrigoRepository;
import com.fiap.ColdConnect.repository.AlertaFrioRepository;
import com.fiap.ColdConnect.repository.SolicitacaoRecursoRepository;
import com.fiap.ColdConnect.specification.SolicitacaoRecursoSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SolicitacaoRecursoService {

    private final SolicitacaoRecursoRepository repository;
    private final AbrigoRepository abrigoRepository;
    private final AlertaFrioRepository alertaRepository;
    private final SolicitacaoRecursoMapper mapper;

    public SolicitacaoRecursoDTO criar(SolicitacaoRecursoDTO dto) {
        Abrigo abrigo = abrigoRepository.findById(dto.getAbrigoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Abrigo não encontrado"));

        AlertaFrio alerta = alertaRepository.findById(dto.getAlertaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alerta não encontrado"));

        SolicitacaoRecurso solicitacao = mapper.toEntity(dto);
        solicitacao.setAbrigo(abrigo);
        solicitacao.setAlerta(alerta);
        solicitacao.setStatus(StatusSolicitacao.PENDENTE);

        return mapper.toDTO(repository.save(solicitacao));
    }

    public List<SolicitacaoRecursoDTO> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public Page<SolicitacaoRecursoDTO> buscarComFiltro(SolicitacaoRecursoFilter filtro, Pageable pageable) {
        return repository.findAll(SolicitacaoRecursoSpecification.comFiltros(filtro), pageable)
                .map(mapper::toDTO);
    }

    public SolicitacaoRecursoDTO buscarPorId(Long id) {
        SolicitacaoRecurso solicitacao = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Solicitação não encontrada"));
        return mapper.toDTO(solicitacao);
    }

    public SolicitacaoRecursoDTO atualizar(Long id, SolicitacaoRecursoDTO dtoAtualizada) {
        SolicitacaoRecurso solicitacao = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Solicitação não encontrada"));

        solicitacao.setTipoRecurso(dtoAtualizada.getTipoRecurso());
        solicitacao.setQuantidade(dtoAtualizada.getQuantidade());
        solicitacao.setStatus(dtoAtualizada.getStatus());

        return mapper.toDTO(repository.save(solicitacao));
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Solicitação não encontrada");
        }
        repository.deleteById(id);
    }
}
