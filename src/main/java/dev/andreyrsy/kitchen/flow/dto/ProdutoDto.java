package dev.andreyrsy.kitchen.flow.dto;

import dev.andreyrsy.kitchen.flow.model.StatusValidade;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProdutoDto {
    private Long id;
    private String alimento;
    private Integer quantidade;
    private LocalDate dataValidade;

    private StatusValidade status;
}
