package br.com.erpvan.controller.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AlunoResponse(
        Long id,
        String nome,
        String cpf,
        LocalDate dataNasc,
        String nomeRespo,
        String cpfRespo,
        LocalDate dataNascResp,
        String telefone,
        BigDecimal valorPag,
        Integer diaPaga,
        EscolaResponse escola,
        PeriodoResponse periodo
) {
}
