package dev.andreyrsy.orderly.service;

import dev.andreyrsy.orderly.dto.response.ConsumoResponseDto;
import dev.andreyrsy.orderly.dto.request.LotesRequestDto;
import dev.andreyrsy.orderly.dto.response.LotesResponseDto;
import dev.andreyrsy.orderly.exception.business.DataInvalidaException;
import dev.andreyrsy.orderly.exception.business.LoteInvalidoException;
import dev.andreyrsy.orderly.exception.business.LoteNaoEncontradoException;
import dev.andreyrsy.orderly.exception.business.QuantidadeInsuficienteException;
import dev.andreyrsy.orderly.mapper.ProjectMapper;
import dev.andreyrsy.orderly.model.Lotes;
import dev.andreyrsy.orderly.model.Produto;
import dev.andreyrsy.orderly.repository.LotesRepository;
import dev.andreyrsy.orderly.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LotesService {
    private final LotesRepository lotesRepository;
    private final ProdutoRepository produtoRepository;
    private final ProjectMapper projectMapper;

    public LotesService(LotesRepository lotesRepository, ProdutoRepository produtoRepository, ProjectMapper projectMapper) {
        this.lotesRepository = lotesRepository;
        this.produtoRepository = produtoRepository;
        this.projectMapper = projectMapper;
    }

    @Transactional
    public LotesResponseDto salvarLote(LotesRequestDto dto) throws Exception {
        log.info("Criando lote produtoId={} quantidade={} dataEntrada={} dataValidade={}",
                dto.getProdutoId(), dto.getQuantidade(), dto.getDataEntrada(), dto.getDataValidade());

        Produto produtoSelecionado = produtoRepository.findById(dto.getProdutoId()).orElseThrow(
                () -> new EntityNotFoundException("Produto com o id " + dto.getProdutoId() + " não encontrado"));

        try {
            if (dto.getDataValidade().isBefore(dto.getDataEntrada())) {
                log.error("Data de validade inválida dataValidade={} dataEntrada={}", dto.getDataValidade(),
                        dto.getDataEntrada());
                throw new DataInvalidaException("Data de validade não pode ser anterior à data de entrada!");
            }

            Lotes toEntity = projectMapper.toLotesEntity(dto);
            toEntity.setProduto(produtoSelecionado);
            lotesRepository.saveAndFlush(toEntity);
            log.info("Lotes salvo no banco id={}", toEntity.getId());

            LotesResponseDto toResponseDto = projectMapper.toLotesResponseDto(toEntity, produtoSelecionado);

            log.info("Lotes criado com sucesso id={} produto={}", toResponseDto.getId(), produtoSelecionado.getNome());
            return toResponseDto;

        } catch (Exception ex) {
            log.error("Falha ao criar lote produtoId={} quantidade={}", dto.getProdutoId(), dto.getQuantidade(), ex);
            throw new LoteInvalidoException(produtoSelecionado.getNome());
        }
    }

    public List<LotesResponseDto> listarTodosLotes() {

        log.info("Listando todos os lotes");
        List<Lotes> findAll = lotesRepository.findAll();

        List<LotesResponseDto> toResposneDto = findAll.stream()
                .map(lote -> projectMapper.toLotesResponseDto(lote, lote.getProduto()))
                .collect(Collectors.toList());
        log.info("Encontrados {} lotes", toResposneDto.size());

        return toResposneDto;
    }

    public Lotes findById(Long id) {
        log.info("Buscando lotes por id={}", id);
        Lotes lotes = lotesRepository.findById(id).orElseThrow(() -> new LoteNaoEncontradoException(id));
        log.info("Lotes encontrado id={} produto={} quantidade={}",
                lotes.getId(), lotes.getProduto().getNome(), lotes.getQuantidade());
        return lotes;
    }

    @Transactional
    public ConsumoResponseDto utilizarProduto(Long id, Integer quantidadeSolicitada) {
        log.info("Iniciando consumo do lote id={} quantidadeSolicitada={}", id, quantidadeSolicitada);
        Lotes lotesId = lotesRepository.findById(id).orElseThrow(() -> new LoteNaoEncontradoException(id));

        if (lotesId.getQuantidade() >= quantidadeSolicitada) {
            lotesId.setQuantidade(lotesId.getQuantidade() - quantidadeSolicitada);
        } else {
            log.error("Quantidade insuficiente no estoque lotesId={} quantidadeDisponivel={} quantidadeSolicitada={}",
                    id, lotesId.getQuantidade(), quantidadeSolicitada);
            throw new QuantidadeInsuficienteException(lotesId.getId(), lotesId.getQuantidade(), quantidadeSolicitada);
        }
        lotesRepository.saveAndFlush(lotesId);
        ConsumoResponseDto novoConsumo = new ConsumoResponseDto();

        novoConsumo.setLoteId(lotesId.getId());
        novoConsumo.setQuantidade(lotesId.getQuantidade());

        log.info("Consumo realizado com sucesso lotesId={} quantidadeRestante={}", id, lotesId.getQuantidade());
        return novoConsumo;
    }

    @Transactional
    public void deleteById(Long id) {
        log.info("Iniciando deleção do lote id={}", id);
        try {
            lotesRepository.deleteById(id);
            log.info("Lotes apagado com sucesso id={}", id);
        } catch (Exception ex) {
            log.error("Falha ao deletar lote id={}!", id, ex);
            throw new LoteNaoEncontradoException(id);
        }
        log.info("Lotes deletado com sucesso id={}", id);
    }
}
