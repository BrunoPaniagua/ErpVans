package br.com.erpvan.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "bairro", length = 45, nullable = false)
    private String bairro;

    @Column(name = "rua", length = 45, nullable = false)
    private String rua;

    @Column(name = "numero", length = 45, nullable = false)
    private String numero;

    @Column(name = "complemento", length = 45, nullable = true)
    private String complemento;
}
