package dev.andreyrsy.kitchen.flow.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoriaRequestDto {
    @NotBlank(message = "Nome da categoria é obrigatório")
    private String nome;
}
