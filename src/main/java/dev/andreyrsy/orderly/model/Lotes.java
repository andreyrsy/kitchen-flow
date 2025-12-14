package dev.andreyrsy.orderly.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "lotes")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Lotes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantidade;

    @Column(name = "dataEntrada")
    private LocalDate dataEntrada;

    @Column(name = "dataValidade")
    private LocalDate dataValidade;

    @ManyToOne
    @JoinColumn(name = "produtoId")
    private Produto produto;
}
