package dev.andreyrsy.kitchen.flow.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LotesResponseDto {
    private Long id;
    private Integer quantidade;
    private LocalDate dataEntrada;
    private LocalDate dataValidade;
    private ProdutoResponseDto produto;
}
