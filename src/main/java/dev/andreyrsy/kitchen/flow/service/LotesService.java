package dev.andreyrsy.kitchen.flow.service;

import dev.andreyrsy.kitchen.flow.dto.CategoriaResponseDto;
import dev.andreyrsy.kitchen.flow.dto.LotesRequestDto;
import dev.andreyrsy.kitchen.flow.dto.LotesResponseDto;
import dev.andreyrsy.kitchen.flow.dto.ProdutoResponseDto;
import dev.andreyrsy.kitchen.flow.model.Categoria;
import dev.andreyrsy.kitchen.flow.model.Lotes;
import dev.andreyrsy.kitchen.flow.model.Produto;
import dev.andreyrsy.kitchen.flow.model.StatusValidade;
import dev.andreyrsy.kitchen.flow.repository.LotesRepository;
import dev.andreyrsy.kitchen.flow.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class LotesService {
    private final LotesRepository lotesRepository;
    private final ProdutoRepository produtoRepository;

    public LotesService(LotesRepository lotesRepository, ProdutoRepository produtoRepository) {
        this.lotesRepository = lotesRepository;
        this.produtoRepository = produtoRepository;
    }

    public LotesResponseDto criarLote(LotesRequestDto dto) throws Exception {
        log.info("Criando lote produtoId={} quantidade={} dataEntrada={} dataValidade={}",
                dto.getProdutoId(), dto.getQuantidade(), dto.getDataEntrada(), dto.getDataValidade());

        Produto produtoSelecionado = produtoRepository.findById(dto.getProdutoId())
                .orElseThrow(() -> new EntityNotFoundException("Produto com o id " + dto.getProdutoId() + " não encontrado"));

        Categoria categoriaSelecionada = produtoSelecionado.getCategoria();

        try {
            if (dto.getDataValidade().isBefore(dto.getDataEntrada())) {
                log.error("Data de validade inválida dataValidade={} dataEntrada={}", dto.getDataValidade(), dto.getDataEntrada());
                throw new Exception("Insira uma data de validade correta!");
            }

            Lotes entity = new Lotes();
            entity.setQuantidade(dto.getQuantidade());
            entity.setData_validade(dto.getDataValidade());
            entity.setData_entrada(dto.getDataEntrada());
            entity.setProduto(produtoSelecionado);

            Lotes loteSalvo = lotesRepository.save(entity);
            log.debug("Lote salvo no banco id={}", loteSalvo.getId());

            CategoriaResponseDto categoria = new CategoriaResponseDto();
            categoria.setId(categoriaSelecionada.getId());
            categoria.setNome(categoriaSelecionada.getNome());

            ProdutoResponseDto produtoDto = new ProdutoResponseDto();
            produtoDto.setId(produtoSelecionado.getId());
            produtoDto.setNome(produtoSelecionado.getNome());
            produtoDto.setUnidadeMedida(produtoSelecionado.getUnidadeMedida());
            produtoDto.setCategoriaDto(categoria);

            LotesResponseDto lotesDto = new LotesResponseDto();
            lotesDto.setId(loteSalvo.getId());
            lotesDto.setQuantidade(dto.getQuantidade());
            lotesDto.setDataValidade(dto.getDataValidade());
            lotesDto.setDataEntrada(dto.getDataEntrada());
            lotesDto.setProdutoDto(produtoDto);

            log.info("Lote criado com sucesso id={} produto={}",
                    loteSalvo.getId(), loteSalvo.getProduto().getNome());
            return lotesDto;

        } catch (Exception ex) {
            log.error("Falha ao criar lote produtoId={} quantidade={}", dto.getProdutoId(), dto.getQuantidade(), ex);
            throw new Exception("Erro ao criar lote!", ex);
        }
    }

    public List<LotesResponseDto> listarLotes() {
        log.info("Listando todos os lotes");
        List<Lotes> lotesList = lotesRepository.findAll();

        List<LotesResponseDto> lotesResponseDtos = new ArrayList<>();

        try {
            for (Lotes lote : lotesList) {
                LotesResponseDto loteDto = new LotesResponseDto();
                loteDto.setId(lote.getId());
                loteDto.setQuantidade(lote.getQuantidade());
                loteDto.setDataValidade(lote.getData_validade());
                loteDto.setDataEntrada(lote.getData_entrada());
                loteDto.setStatusValidade(calcularStatus(lote.getData_validade()));

                ProdutoResponseDto produtoDto = new ProdutoResponseDto();
                produtoDto.setId(lote.getProduto().getId());
                produtoDto.setNome(lote.getProduto().getNome());
                produtoDto.setUnidadeMedida(lote.getProduto().getUnidadeMedida());

                CategoriaResponseDto categoriaDto = new CategoriaResponseDto();
                categoriaDto.setId(lote.getProduto().getCategoria().getId());
                categoriaDto.setNome(lote.getProduto().getCategoria().getNome());

                produtoDto.setCategoriaDto(categoriaDto);
                loteDto.setProdutoDto(produtoDto);


                lotesResponseDtos.add(loteDto);
            }

        } catch (Exception ex) {
            log.error("Falha ao converter lotes para DTOs", ex);
            throw new RuntimeException("Falha ao listar lotes");
        }

        log.info("Encontrados {} lotes", lotesResponseDtos.size());
        return lotesResponseDtos;
    }

    public Lotes findById(Long id) {
        log.debug("Buscando lote por id={}", id);
        Lotes lotes = lotesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Lote não encontrado!"));
        log.debug("Lote encontrado id={} produto={} quantidade={}",
                lotes.getId(), lotes.getProduto().getNome(), lotes.getQuantidade());
        return lotes;
    }

    public void usarProduto(Long id, Integer quantidadeConsumida) throws Exception {
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

    public void deletarPorId(Long id) {
        log.info("Iniciando deleção do lote id={}", id);
        lotesRepository.deleteById(id);
        log.info("Lote deletado com sucesso id={}", id);
    }
}
