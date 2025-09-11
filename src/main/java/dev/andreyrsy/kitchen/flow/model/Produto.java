package dev.andreyrsy.kitchen.flow.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "produto")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "unidade_medida")
    private String unidadeMedida;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @JsonIgnoreProperties({"produto"})
    private Categoria categoria;

//    @JsonProperty("data_validade")
//    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
//    @Column(name = "data_validade")
//    private LocalDate dataValidade;
}
