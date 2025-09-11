package dev.andreyrsy.kitchen.flow.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.andreyrsy.kitchen.flow.model.StatusValidade;
import lombok.Data;

@Data
public class ProdutoRequestDto {
    private String nome;
    @JsonProperty("unidade_medida")
    private String unidadeMedida;
    @JsonProperty("categoria_id")
    private Long categoriaId;
}
