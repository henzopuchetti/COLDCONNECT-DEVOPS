package com.fiap.ColdConnect.service;

import com.fiap.ColdConnect.dto.LeituraTemperaturaDTO;
import com.fiap.ColdConnect.mapper.LeituraTemperaturaMapper;
import com.fiap.ColdConnect.model.AcaoEmergencial;
import com.fiap.ColdConnect.model.AlertaFrio;
import com.fiap.ColdConnect.model.LeituraTemperatura;
import com.fiap.ColdConnect.model.enums.GrauAlerta;
import com.fiap.ColdConnect.model.enums.StatusAcao;
import com.fiap.ColdConnect.repository.LeituraTemperaturaRepository;
import com.fiap.ColdConnect.repository.AcaoEmergencialRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeituraTemperaturaService {

    private final LeituraTemperaturaRepository repository;
    private final LeituraTemperaturaMapper mapper;
    private final AlertaFrioService alertaFrioService;
    private final AcaoEmergencialRepository acaoEmergencialRepository;

    @Transactional
    public LeituraTemperaturaDTO criar(LeituraTemperaturaDTO dto) {
        // 1. Salvar leitura
        LeituraTemperatura leitura = mapper.toEntity(dto);
        LeituraTemperatura salva = repository.save(leitura);

        // 2. Calcular grau do alerta
        GrauAlerta grau = alertaFrioService.calcularGrau(leitura.getTemperatura());

        if (grau != null) {
            // 3. Criar alerta
            AlertaFrio alerta = AlertaFrio.builder()
                    .grau(grau)
                    .regiao(identificarRegiao(leitura.getLatitude(), leitura.getLongitude()))
                    .build();
            alerta = alertaFrioService.salvar(alerta); // deve retornar alerta com ID

            // 4. Criar ação emergencial com base no grau
            String tipoAcao = switch (grau) {
                case GRAVE -> "RESGATE URGENTE";
                case MODERADO -> "SUPORTE INTERMEDIÁRIO";
                case LEVE -> "MONITORAMENTO";
            };

            AcaoEmergencial acao = AcaoEmergencial.builder()
                    .tipo(tipoAcao)
                    .status(StatusAcao.PENDENTE)
                    .alertaFrio(alerta)
                    .build();

            acaoEmergencialRepository.save(acao);
        }

        return mapper.toDTO(salva);
    }

    public List<LeituraTemperaturaDTO> listarTodas() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public LeituraTemperaturaDTO buscarPorId(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Leitura não encontrada"));
    }

    public LeituraTemperaturaDTO atualizar(Long id, LeituraTemperaturaDTO dtoAtualizada) {
        LeituraTemperatura existente = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Leitura não encontrada"));

        existente.setLatitude(dtoAtualizada.getLatitude());
        existente.setLongitude(dtoAtualizada.getLongitude());
        existente.setTemperatura(dtoAtualizada.getTemperatura());

        LeituraTemperatura atualizada = repository.save(existente);
        return mapper.toDTO(atualizada);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Leitura não encontrada");
        }
        repository.deleteById(id);
    }

    private String identificarRegiao(Double lat, Double lon) {
        if (lat < -10) return "Sul";
        if (lat > -10 && lat < 0) return "Sudeste";
        return "Norte";
    }
}
