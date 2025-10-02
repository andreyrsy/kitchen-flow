package dev.andreyrsy.kitchen.flow.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ConsumoRequestDto {
    @NotNull
    @Positive
    private Integer quantidade;
}
