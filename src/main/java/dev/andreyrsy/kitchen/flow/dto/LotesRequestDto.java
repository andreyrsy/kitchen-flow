package dev.andreyrsy.kitchen.flow.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LotesRequestDto {
    @NotBlank(message = "A quantidade deve ser informada")
    private Integer quantidade;

    @NotBlank(message = "A data de validade deve ser informada")
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataValidade;

    @NotBlank(message = "A data de entrada deve ser informada")
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataEntrada;

    @NotNull(message = "O ID do produto deve ser informado")
    private Long produtoId;
}
