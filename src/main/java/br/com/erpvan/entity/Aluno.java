package br.com.erpvan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "aluno")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45)
    private String nome;

    @Column(length = 11)
    private String cpf;

    @Column(name = "data_nasc", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "nome_respo", nullable = false, length = 45)
    private String nomeResponsavel;

    @Column(name = "cpf_respo", nullable = false, length = 11)
    private String cpfResponsavel;

    @Column(name = "data_nasc_resp", nullable = false)
    private LocalDate dataNascimentoResponsavel;

    @Column(length = 15)
    private String telefone;

    @Column(name = "valor_pag", precision = 10, scale = 2)
    private BigDecimal valorPagamento;

    @Column(name = "dia_paga")
    private Integer diaPagamento;

    @ManyToOne
    @JoinColumn(name = "escola_id", nullable = false)
    private Escola escola;

    @ManyToOne
    @JoinColumn(name = "periodo_id", nullable = false)
    private Periodo periodo;
}
