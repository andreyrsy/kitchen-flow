package dev.andreyrsy.kitchen.flow.dto;
import lombok.Data;

@Data
public class ProdutoResponseDto {
    private Long id;
    private String nome;
    private String unidade_medida;
    private CategoriaResponseDto categoriaDto;
//    private List<Lotes> lotes;
}
