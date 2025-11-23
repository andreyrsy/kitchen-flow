package dev.andreyrsy.kitchen.flow.mapper;

import dev.andreyrsy.kitchen.flow.dto.CategoriaResponseDto;
import dev.andreyrsy.kitchen.flow.dto.ProdutoRequestDto;
import dev.andreyrsy.kitchen.flow.dto.ProdutoResponseDto;
import dev.andreyrsy.kitchen.flow.model.Produto;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoMapper {

    public Produto toEntity(ProdutoRequestDto dtoRequest) {
        Produto entity = new Produto();
        entity.setNome(dtoRequest.getNome());
        entity.setUnidadeMedida(dtoRequest.getUnidadeMedida());

        return entity;
    }

    public ProdutoResponseDto toDto(Produto entity) {
        ProdutoResponseDto responseDto = new ProdutoResponseDto();
        responseDto.setId(entity.getId());
        responseDto.setNome(entity.getNome());
        responseDto.setUnidadeMedida(entity.getUnidadeMedida());

        if (entity.getCategoria() != null) {
            CategoriaResponseDto categoriaDto = new CategoriaResponseDto();
            categoriaDto.setId(entity.getCategoria().getId());
            categoriaDto.setNome(entity.getCategoria().getNome());
            responseDto.setCategoria(categoriaDto);
        }

        return responseDto;
    }

    public List<ProdutoResponseDto> toDtoList(List<Produto> produtos) {
        if (produtos == null) {
            return Collections.emptyList();
        }

        return produtos.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
