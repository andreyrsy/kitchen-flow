package dev.andreyrsy.orderly.mapper;

import dev.andreyrsy.orderly.dto.request.CategoriaRequestDto;
import dev.andreyrsy.orderly.dto.request.LotesRequestDto;
import dev.andreyrsy.orderly.dto.request.ProdutoRequestDto;
import dev.andreyrsy.orderly.dto.response.CategoriaResponseDto;
import dev.andreyrsy.orderly.dto.response.LotesResponseDto;
import dev.andreyrsy.orderly.dto.response.ProdutoResponseDto;
import dev.andreyrsy.orderly.model.Categoria;
import dev.andreyrsy.orderly.model.Lotes;
import dev.andreyrsy.orderly.model.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

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
