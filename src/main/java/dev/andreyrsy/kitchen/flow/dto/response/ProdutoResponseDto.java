package dev.andreyrsy.kitchen.flow.dto.response;
import lombok.Data;

@Data
public class ProdutoResponseDto {
    private Long id;
    private String nome;
    private String unidadeMedida;
    private CategoriaResponseDto categoria;
}
