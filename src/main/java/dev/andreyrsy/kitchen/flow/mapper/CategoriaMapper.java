package dev.andreyrsy.kitchen.flow.mapper;

import dev.andreyrsy.kitchen.flow.dto.CategoriaRequestDto;
import dev.andreyrsy.kitchen.flow.dto.CategoriaResponseDto;
import dev.andreyrsy.kitchen.flow.model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public Categoria toEntity(CategoriaRequestDto requestDto) {
        Categoria categoria = new Categoria();
        categoria.setNome(requestDto.getNome());
        return categoria;
    }

    public CategoriaResponseDto toResponseDto(Categoria categoria) {
        CategoriaResponseDto responseDto = new CategoriaResponseDto();
        responseDto.setId(categoria.getId());
        responseDto.setNome(categoria.getNome());
        return responseDto;
    }
}
