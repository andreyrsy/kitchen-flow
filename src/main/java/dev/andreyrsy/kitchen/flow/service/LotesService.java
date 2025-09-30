package dev.andreyrsy.kitchen.flow.service;

import dev.andreyrsy.kitchen.flow.dto.LotesRequestDto;
import dev.andreyrsy.kitchen.flow.dto.LotesResponseDto;
import dev.andreyrsy.kitchen.flow.mapper.LotesMapper;
import dev.andreyrsy.kitchen.flow.model.Lotes;
import dev.andreyrsy.kitchen.flow.model.Produto;
import dev.andreyrsy.kitchen.flow.repository.LotesRepository;
import dev.andreyrsy.kitchen.flow.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LotesService {
    private final LotesRepository lotesRepository;
    private final ProdutoRepository produtoRepository;
    private final LotesMapper mapper;

    public LotesService(LotesRepository lotesRepository, ProdutoRepository produtoRepository, LotesMapper mapper) {
        this.lotesRepository = lotesRepository;
        this.produtoRepository = produtoRepository;
        this.mapper = mapper;
    }

    public LotesResponseDto salvarLote(LotesRequestDto dto) throws Exception {
        log.info("Criando lote produtoId={} quantidade={} dataEntrada={} dataValidade={}",
                dto.getProdutoId(), dto.getQuantidade(), dto.getDataEntrada(), dto.getDataValidade());

        Produto produtoSelecionado = produtoRepository.findById(dto.getProdutoId())
                .orElseThrow(() -> new EntityNotFoundException("Produto com o id " + dto.getProdutoId() + " não encontrado"));

        try {
            if (dto.getDataValidade().isBefore(dto.getDataEntrada())) {
                log.error("Data de validade inválida dataValidade={} dataEntrada={}", dto.getDataValidade(), dto.getDataEntrada());
                throw new Exception("Insira uma data de validade correta!");
            }

            Lotes toEntity = mapper.toEntity(dto);
            log.debug("Lote salvo no banco id={}", toEntity.getId());

            LotesResponseDto toResponseDto = mapper.toDto(toEntity, produtoSelecionado);

            log.info("Lote criado com sucesso id={} produto={}", toResponseDto.getId(), produtoSelecionado.getNome());
            return toResponseDto;

        } catch (Exception ex) {
            log.error("Falha ao criar lote produtoId={} quantidade={}", dto.getProdutoId(), dto.getQuantidade(), ex);
            throw new Exception("Erro ao criar lote!", ex);
        }
    }

    public List<LotesResponseDto> listarTodosLotes() {

        log.info("Listando todos os lotes");
        List<Lotes> findAll = lotesRepository.findAll();

        List<LotesResponseDto> toResposneDto = findAll.stream()
                .map(lote -> mapper.toDto(lote, lote.getProduto()))
                .collect(Collectors.toList());
        log.info("Listados {} produtos com sucesso", toResposneDto.size());

        return toResposneDto;
    }

    public Lotes findById(Long id) {
        log.debug("Buscando lote por id={}", id);
        Lotes lotes = lotesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Lote não encontrado!"));
        log.debug("Lote encontrado id={} produto={} quantidade={}",
                lotes.getId(), lotes.getProduto().getNome(), lotes.getQuantidade());
        return lotes;
    }

    public void utilizarProduto(Long id, Integer quantidadeConsumida) throws Exception {
        log.info("Iniciando consumo do lote id={} quantidadeConsumida={}", id, quantidadeConsumida);
        Lotes idProduto = lotesRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado."));
        if (idProduto.getQuantidade() >= quantidadeConsumida) {
            idProduto.setQuantidade(idProduto.getQuantidade() - quantidadeConsumida);
            log.info("Consumo realizado com sucesso loteId={} quantidadeRestante={}", id, idProduto.getQuantidade());
        } else {
            log.error("Quantidade insuficiente no estoque loteId={} quantidadeDisponivel={} quantidadeSolicitada={}", id, idProduto.getQuantidade(), quantidadeConsumida);
            throw new Exception("Quantidade insuficiente no estoque.");
        }
        lotesRepository.saveAndFlush(idProduto);
    }

    /*
        public StatusValidade calcularStatus(LocalDate dataValidade) {
            log.debug("Calculando status de validade para data={}", dataValidade);
            long diasRestantes = ChronoUnit.DAYS.between(LocalDate.now(), dataValidade);

            StatusValidade status;
            if (diasRestantes < 0) {
                status = StatusValidade.VENCIDO;
            } else if (diasRestantes <= 1) {
                status = StatusValidade.URGENTE;
            } else if (diasRestantes <= 3) {
                status = StatusValidade.ATENCAO;
            } else {
                status = StatusValidade.NORMAL;
            }
            log.debug("Status calculado: {} para {} dias restantes", status, diasRestantes);
            return status;
        }
     */
    public void deletarPorId(Long id) {
        log.info("Iniciando deleção do lote id={}", id);
        lotesRepository.deleteById(id);
        log.info("Lote deletado com sucesso id={}", id);
    }
}
