package dev.andreyrsy.kitchen.flow.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "tb_alimento")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class KitchenModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "alimento")
    private String alimento;
    @Column(name = "quantidade")
    private Integer quantidade;
    @JsonProperty("data_validade")
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    @Column(name = "data_validade")
    private LocalDate dataValidade;
}
