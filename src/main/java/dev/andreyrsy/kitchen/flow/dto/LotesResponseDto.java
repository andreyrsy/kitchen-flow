package dev.andreyrsy.kitchen.flow.dto;

import dev.andreyrsy.kitchen.flow.model.StatusValidade;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LotesResponseDto {
    private Long id;
    private Integer quantidade;
    private LocalDate dataValidade;
    private LocalDate dataEntrada;
    private StatusValidade statusValidade;
    private ProdutoResponseDto produtoDto;
}
