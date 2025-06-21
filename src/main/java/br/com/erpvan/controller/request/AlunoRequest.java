package br.com.erpvan.controller.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AlunoRequest(
        @NotBlank String nome,
        String cpf,
        @NotNull LocalDate dataNasc,
        @NotBlank String nomeRespo,
        @NotBlank String cpfRespo,
        @NotNull LocalDate dataNascResp,
        String telefone,
        BigDecimal valorPag,
        @Min(1) @Max(31) Integer diaPaga,
        @NotNull Long escolaId,
        @NotNull Long periodoId
) {}
