package dev.andreyrsy.kitchen.flow.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "produto")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome do produto é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @NotBlank(message = "Unidade de medida é obrigatória")
    @Size(min = 1, max = 20, message = "Unidade deve ter entre 1 e 20 caracteres")
    @Column(name = "unidadeMedida", nullable = false)
    private String unidadeMedida;

    @ManyToOne
    @JoinColumn(name = "categoriaId")
    private Categoria categoria;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<Lotes> lotes;
}
