package dev.andreyrsy.kitchen.flow.mapper;

import dev.andreyrsy.kitchen.flow.dto.CategoriaResponseDto;
import dev.andreyrsy.kitchen.flow.dto.LotesRequestDto;
import dev.andreyrsy.kitchen.flow.dto.LotesResponseDto;
import dev.andreyrsy.kitchen.flow.dto.ProdutoResponseDto;
import dev.andreyrsy.kitchen.flow.model.Lotes;
import dev.andreyrsy.kitchen.flow.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class LotesMapper {

    public Lotes toEntity(LotesRequestDto dto) {
        Lotes lotes = new Lotes();
        lotes.setQuantidade(dto.getQuantidade());
        lotes.setData_validade(dto.getDataValidade());
        lotes.setData_entrada(dto.getDataEntrada());
        return lotes;
    }

    public LotesResponseDto toDto(Lotes lotes, Produto produtoSelecionado) {
        LotesResponseDto loteResponse = new LotesResponseDto();

        CategoriaResponseDto categoriaResponseDto = new CategoriaResponseDto();
        categoriaResponseDto.setId(produtoSelecionado.getCategoria().getId());
        categoriaResponseDto.setNome(produtoSelecionado.getCategoria().getNome());

        ProdutoResponseDto produtoResponseDto = new ProdutoResponseDto();
        produtoResponseDto.setId(produtoSelecionado.getId());
        produtoResponseDto.setNome(produtoSelecionado.getNome());
        produtoResponseDto.setUnidadeMedida(produtoSelecionado.getUnidadeMedida());
        produtoResponseDto.setCategoriaDto(categoriaResponseDto);

        loteResponse.setId(lotes.getId());
        loteResponse.setQuantidade(lotes.getQuantidade());
        loteResponse.setDataValidade(lotes.getData_validade());
        loteResponse.setDataEntrada(lotes.getData_entrada());
        loteResponse.setProdutoDto(produtoResponseDto);

        return loteResponse;
    }
}
