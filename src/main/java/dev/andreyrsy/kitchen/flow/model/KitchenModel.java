package dev.andreyrsy.kitchen.flow.model;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "alimento")
    private String alimento;
    @Column(name = "quantidade")
    private Integer quantidade;
    @Column(name = "data_validade")
    private LocalDate dataValidade;
}
