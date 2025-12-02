package dev.andreyrsy.kitchen.flow.service;

import dev.andreyrsy.kitchen.flow.dto.ConsumoResponseDto;
import dev.andreyrsy.kitchen.flow.dto.LotesRequestDto;
import dev.andreyrsy.kitchen.flow.dto.LotesResponseDto;
import dev.andreyrsy.kitchen.flow.exception.business.DataInvalidaException;
import dev.andreyrsy.kitchen.flow.exception.business.LoteNaoEncontradoException;
import dev.andreyrsy.kitchen.flow.exception.business.QuantidadeInsuficienteException;
import dev.andreyrsy.kitchen.flow.mapper.KitchenMapper;
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
    private final KitchenMapper kitchenMapper;
    private final LotesMapper mapper;

    public LotesService(LotesRepository lotesRepository, ProdutoRepository produtoRepository, KitchenMapper kitchenMapper, LotesMapper mapper) {
        this.lotesRepository = lotesRepository;
        this.produtoRepository = produtoRepository;
        this.kitchenMapper = kitchenMapper;
        this.mapper = mapper;
    }

    public LotesResponseDto salvarLote(LotesRequestDto dto) throws Exception {
        log.info("Criando lote produtoId={} quantidade={} dataEntrada={} dataValidade={}",
                dto.getProdutoId(), dto.getQuantidade(), dto.getDataEntrada(), dto.getDataValidade());

        Produto produtoSelecionado = produtoRepository.findById(dto.getProdutoId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Produto com o id " + dto.getProdutoId() + " não encontrado"));

        try {
            if (dto.getDataValidade().isBefore(dto.getDataEntrada())) {
                log.error("Data de validade inválida dataValidade={} dataEntrada={}", dto.getDataValidade(),
                        dto.getDataEntrada());
                throw new DataInvalidaException("Data de validade não pode ser anterior à data de entrada!");
            }

            Lotes toEntity = kitchenMapper.toLotesEntity(dto);
            toEntity.setProduto(produtoSelecionado);
            lotesRepository.saveAndFlush(toEntity);
            log.info("Lote salvo no banco id={}", toEntity.getId());


            LotesResponseDto toResponseDto = kitchenMapper.toLotesResponseDto(toEntity, produtoSelecionado);


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
                .map(lote -> kitchenMapper.toLotesResponseDto(lote, lote.getProduto()))
                .collect(Collectors.toList());
        log.info("Encontrados {} lotes", toResposneDto.size());

        return toResposneDto;
    }

    public Lotes findById(Long id) {
        log.info("Buscando lote por id={}", id);
        Lotes lotes = lotesRepository.findById(id).orElseThrow(() -> new LoteNaoEncontradoException(id));
        log.info("Lote encontrado id={} produto={} quantidade={}",
                lotes.getId(), lotes.getProduto().getNome(), lotes.getQuantidade());
        return lotes;
    }

    public ConsumoResponseDto utilizarProduto(Long id, Integer quantidadeSolicitada) throws Exception {
        log.info("Iniciando consumo do lote id={} quantidadeSolicitada={}", id, quantidadeSolicitada);
        Lotes loteId = lotesRepository.findById(id).orElseThrow(() -> new LoteNaoEncontradoException(id));

        if (loteId.getQuantidade() >= quantidadeSolicitada) {
            loteId.setQuantidade(loteId.getQuantidade() - quantidadeSolicitada);
        } else {
            log.error("Quantidade insuficiente no estoque loteId={} quantidadeDisponivel={} quantidadeSolicitada={}",
                    id, loteId.getQuantidade(), quantidadeSolicitada);
            throw new QuantidadeInsuficienteException(loteId.getId(), loteId.getQuantidade(), quantidadeSolicitada);
        }
        lotesRepository.saveAndFlush(loteId);
        ConsumoResponseDto novoConsumo = new ConsumoResponseDto();

        novoConsumo.setLoteId(loteId.getId());
        novoConsumo.setQuantidade(loteId.getQuantidade());

        log.info("Consumo realizado com sucesso loteId={} quantidadeRestante={}", id, loteId.getQuantidade());

        return novoConsumo;
    }

    public void deleteById(Long id) {
        log.info("Iniciando deleção do lote id={}", id);
        try {
            lotesRepository.deleteById(id);
            log.info("Lote apagado com sucesso id={}", id);
        } catch (Exception ex) {
            log.error("Falha ao deletar lote id={}!", id, ex);
            throw new LoteNaoEncontradoException(id);
        }
        log.info("Lote deletado com sucesso id={}", id);
    }
}
