package br.com.erpvan.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.id.CompositeNestedGeneratedValueGenerator;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "escola")
public class Escola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @ManyToMany
    @JoinTable(name = "escola_tem_periodo",
    joinColumns = @JoinColumn (name = "escola_id"),
    inverseJoinColumns = @JoinColumn (name = "periodo_id"))
    private List<Periodo> periodos;

}
