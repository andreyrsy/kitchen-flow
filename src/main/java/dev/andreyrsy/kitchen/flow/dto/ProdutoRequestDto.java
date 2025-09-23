package dev.andreyrsy.kitchen.flow.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class ProdutoRequestDto {
    @NotBlank(message = "Nome obrigatório")
    @Size(min = 2, max = 50, message = "Nome deve ter entre 2 e 50 caracteres")
    private String nome;
    @NotBlank(message = "Unidade de medida obrigatória")
    private String unidadeMedida;
    @NotNull(message = "Categoria obrigatória")
    private Long categoriaId;
}
