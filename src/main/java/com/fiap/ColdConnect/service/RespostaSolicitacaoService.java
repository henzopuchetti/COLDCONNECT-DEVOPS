package com.fiap.ColdConnect.service;

import com.fiap.ColdConnect.dto.RespostaSolicitacaoDTO;
import com.fiap.ColdConnect.mapper.RespostaSolicitacaoMapper;
import com.fiap.ColdConnect.model.RespostaSolicitacao;
import com.fiap.ColdConnect.model.SolicitacaoRecurso;
import com.fiap.ColdConnect.model.enums.StatusResposta;
import com.fiap.ColdConnect.model.enums.StatusSolicitacao;
import com.fiap.ColdConnect.repository.RespostaSolicitacaoRepository;
import com.fiap.ColdConnect.repository.SolicitacaoRecursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RespostaSolicitacaoService {

    private final RespostaSolicitacaoRepository repository;
    private final SolicitacaoRecursoRepository solicitacaoRepository;
    private final RespostaSolicitacaoMapper mapper;

public RespostaSolicitacaoDTO criar(RespostaSolicitacaoDTO dto) {
    SolicitacaoRecurso solicitacao = solicitacaoRepository.findById(dto.getSolicitacaoId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Solicitação não encontrada"));

    StatusSolicitacao statusAnterior = solicitacao.getStatus(); // capturar antes de mudar

    if (dto.getNovoStatus() != null) {
        solicitacao.setStatus(dto.getNovoStatus());
        solicitacaoRepository.save(solicitacao);
    }

    RespostaSolicitacao resposta = mapper.toEntity(dto);
    resposta.setSolicitacao(solicitacao);
    resposta.setStatus(StatusResposta.PENDENTE);
    resposta.setNovoStatus(dto.getNovoStatus()); // adicionar aqui o novo status da solicitação

    RespostaSolicitacao salva = repository.save(resposta);
    RespostaSolicitacaoDTO retorno = mapper.toDTO(salva);
    retorno.setNovoStatus(dto.getNovoStatus());   // garantir que volta no DTO
    retorno.setStatusRespostaAnterior(statusAnterior); // (opcional) se quiser mostrar o status anterior também

    return retorno;
}

    public List<RespostaSolicitacaoDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public RespostaSolicitacaoDTO buscarPorId(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resposta não encontrada"));
    }

    public RespostaSolicitacaoDTO atualizar(Long id, RespostaSolicitacaoDTO dtoAtualizada) {
        RespostaSolicitacao resposta = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resposta não encontrada"));

        resposta.setAtendidoPor(dtoAtualizada.getAtendidoPor());
        resposta.setObservacao(dtoAtualizada.getObservacao());
        resposta.setNovoStatus(dtoAtualizada.getNovoStatus());

        RespostaSolicitacao atualizada = repository.save(resposta);
        return mapper.toDTO(atualizada);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resposta não encontrada");
        }
        repository.deleteById(id);
    }
}
