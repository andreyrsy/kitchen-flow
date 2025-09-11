package dev.andreyrsy.kitchen.flow.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Table(name = "lotes")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lotes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal quantidade;
    @JsonProperty("data_validade")
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    @Column(name = "data_validade")
    private LocalDate data_validade;
    @JsonProperty("data_entrada")
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    @Column(name = "data_entrada")
    private LocalDate data_entrada;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
}
