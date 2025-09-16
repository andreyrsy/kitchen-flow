package dev.andreyrsy.kitchen.flow.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LotesRequestDto {
    private Integer quantidade;

    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataValidade;

    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataEntrada;

    private Long produtoId;
}
