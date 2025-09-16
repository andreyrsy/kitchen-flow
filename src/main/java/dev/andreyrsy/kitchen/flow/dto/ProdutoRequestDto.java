package dev.andreyrsy.kitchen.flow.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class ProdutoRequestDto {
    private String nome;
    private String unidadeMedida;
    private Long categoriaId;
}
