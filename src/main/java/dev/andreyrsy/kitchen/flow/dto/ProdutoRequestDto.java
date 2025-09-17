package dev.andreyrsy.kitchen.flow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class ProdutoRequestDto {
    @NotBlank(message = "Nome obrigatório")
    private String nome;
    @NotBlank(message = "Unidade de medida obrigatória")
    private String unidadeMedida;
    @NotNull(message = "Categoria obrigatória")
    private Long categoriaId;
}
