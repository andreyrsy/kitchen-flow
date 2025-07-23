package dev.andreyrsy.kitchen.flow.service;

import dev.andreyrsy.kitchen.flow.dto.KitchenResponseDTO;
import dev.andreyrsy.kitchen.flow.model.KitchenModel;
import dev.andreyrsy.kitchen.flow.model.StatusValidade;
import dev.andreyrsy.kitchen.flow.repository.KitchenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class KitchenService {
    private final KitchenRepository kitchenRepository;

    public KitchenService(KitchenRepository kitchenRepository) {
        this.kitchenRepository = kitchenRepository;
    }

    public StatusValidade calcularStatus(LocalDate dataValidade) {
        LocalDate dataAtual = LocalDate.now();
        long diasRestantes = ChronoUnit.DAYS.between(dataAtual, dataValidade);

        if (diasRestantes < 0) {
            return StatusValidade.VENCIDO;
        } else if (diasRestantes <= 1) {
            return StatusValidade.URGENTE;
        } else if (diasRestantes <= 3) {
            return StatusValidade.ATENCAO;
        } else {
            return StatusValidade.NORMAL;
        }
    }

    public KitchenModel adicionarAlimento(KitchenModel kitchenModel) {
        return kitchenRepository.save(kitchenModel);
    }

    public List<KitchenResponseDTO> listarAlimentos() {
        List<KitchenModel> alimentosDoBanco = kitchenRepository.findAll();
        List<KitchenResponseDTO> dtosParaEnviar = new ArrayList<>();

        for(KitchenModel alimento : alimentosDoBanco){
            StatusValidade statusValidade = calcularStatus(alimento.getDataValidade());

            KitchenResponseDTO dto = new KitchenResponseDTO();

            dto.setId(alimento.getId());
            dto.setAlimento(alimento.getAlimento());
            dto.setQuantidade(alimento.getQuantidade());
            dto.setDataValidade(alimento.getDataValidade());

            dto.setStatus(statusValidade);
            dtosParaEnviar.add(dto);
        }
        return dtosParaEnviar;
    }

    public void consumirAlimento(Long id, Integer quantidadeConsumida) throws Exception {
        KitchenModel idUsuario = kitchenRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario nao encontrado."));
        if (idUsuario.getQuantidade() >= quantidadeConsumida) {
            idUsuario.setQuantidade(idUsuario.getQuantidade() - quantidadeConsumida);
        } else {
            throw new Exception("Quantidade insuficiente no estoque.");
        }
        kitchenRepository.save(idUsuario);
    }

    public void deletarAlimento(Long id) {
        kitchenRepository.deleteById(id);
    }
}
