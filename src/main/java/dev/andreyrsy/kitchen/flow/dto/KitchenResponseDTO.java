package dev.andreyrsy.kitchen.flow.dto;

import dev.andreyrsy.kitchen.flow.model.StatusValidade;
import lombok.Data;

import java.time.LocalDate;

@Data
public class KitchenResponseDTO {
    private Long id;
    private String alimento;
    private Integer quantidade;
    private LocalDate dataValidade;

    // O nosso campo novo e calculado!
    private StatusValidade status;
}
