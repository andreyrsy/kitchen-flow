package dev.andreyrsy.kitchen.flow.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "lotes")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lotes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantidade;
    @JsonProperty("dataEntrada")
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    @Column(name = "dataEntrada")
    private LocalDate dataEntrada;
    @JsonProperty("dataValidade")
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    @Column(name = "dataValidade")
    private LocalDate dataValidade;

    @ManyToOne
    @JoinColumn(name = "produtoId")
    private Produto produto;
}
