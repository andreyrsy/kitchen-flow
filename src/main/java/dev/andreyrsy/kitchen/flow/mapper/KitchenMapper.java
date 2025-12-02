package dev.andreyrsy.kitchen.flow.mapper;

import dev.andreyrsy.kitchen.flow.dto.*;
import dev.andreyrsy.kitchen.flow.model.Categoria;
import dev.andreyrsy.kitchen.flow.model.Lotes;
import dev.andreyrsy.kitchen.flow.model.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface KitchenMapper {

    Categoria toCategoriaEntity(CategoriaRequestDto categoriaDto);
    CategoriaResponseDto toCategoriaResponseDto(Categoria categoria);

    Produto toProdutoEntity(ProdutoRequestDto requestDto);
    ProdutoResponseDto toProdutoResponseDto(Produto entity);
    List<ProdutoResponseDto> toProdutoDtoList(List<Produto> produtos);

    Lotes toLotesEntity(LotesRequestDto requestDto);

    @Mapping(source = "lotes.id", target = "id")
    @Mapping(source = "lotes.quantidade", target = "quantidade")
    @Mapping(source = "lotes.data_validade", target = "dataValidade")
    @Mapping(source = "lotes.data_entrada", target = "dataEntrada")
    @Mapping(source = "produtoSelecionado", target = "produto")
    LotesResponseDto toLotesResponseDto(Lotes lotes, Produto produtoSelecionado);
}
