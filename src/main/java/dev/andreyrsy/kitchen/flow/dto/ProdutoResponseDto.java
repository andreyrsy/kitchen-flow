package dev.andreyrsy.kitchen.flow.dto;
import dev.andreyrsy.kitchen.flow.model.Categoria;
import lombok.Data;

@Data
public class ProdutoResponseDto {
    private Long id;
    private String nome;
    private String unidade_medida;
    private Categoria categoria;
//    private List<Lotes> lotes;
}
