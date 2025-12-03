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

    @Mapping(source = "dataEntrada", target = "dataEntrada")
    @Mapping(source = "dataValidade", target = "dataValidade")
    Lotes toLotesEntity(LotesRequestDto requestDto);

    @Mapping(source = "lotes.id", target = "id")
    @Mapping(source = "lotes.quantidade", target = "quantidade")
    @Mapping(source = "lotes.dataValidade", target = "dataValidade")
    @Mapping(source = "lotes.dataEntrada", target = "dataEntrada")
    @Mapping(source = "produtoSelecionado", target = "produto")
    LotesResponseDto toLotesResponseDto(Lotes lotes, Produto produtoSelecionado);
}
