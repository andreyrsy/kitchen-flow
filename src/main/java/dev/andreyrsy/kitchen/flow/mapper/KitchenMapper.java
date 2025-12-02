package dev.andreyrsy.kitchen.flow.mapper;

import dev.andreyrsy.kitchen.flow.dto.CategoriaRequestDto;
import dev.andreyrsy.kitchen.flow.dto.CategoriaResponseDto;
import dev.andreyrsy.kitchen.flow.dto.ProdutoRequestDto;
import dev.andreyrsy.kitchen.flow.dto.ProdutoResponseDto;
import dev.andreyrsy.kitchen.flow.model.Categoria;
import dev.andreyrsy.kitchen.flow.model.Produto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface KitchenMapper {

    Categoria toCategoriaEntity(CategoriaRequestDto categoriaDto);
    CategoriaResponseDto toCategoriaResponseDto(Categoria categoria);

    Produto toProdutoEntity(ProdutoRequestDto requestDto);
    ProdutoResponseDto toProdutoResponseDto(Produto entity);
    List<ProdutoResponseDto> toProdutoDtoList(List<Produto> produtos);
}
