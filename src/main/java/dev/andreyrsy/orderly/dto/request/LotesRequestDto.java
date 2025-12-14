package dev.andreyrsy.orderly.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LotesRequestDto {
    @NotNull(message = "A quantidade deve ser informada")
    private Integer quantidade;

    @JsonProperty("dataEntrada")
    @NotNull(message = "A data de entrada deve ser informada")
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataEntrada;

    @JsonProperty("dataValidade")
    @NotNull(message = "A data de validade deve ser informada")
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataValidade;

    @NotNull(message = "O ID do produto deve ser informado")
    private Long produtoId;
}
