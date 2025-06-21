package br.com.erpvan.mapper;

import br.com.erpvan.controller.request.AlunoRequest;
import br.com.erpvan.controller.response.AlunoResponse;
import br.com.erpvan.entity.Aluno;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AlunoMapper {
    public static Aluno toAluno(AlunoRequest request) {
        return Aluno.builder()
                .nome(request.nome())
                .cpf(request.cpf())
                .dataNascimento(request.dataNasc())
                .nomeResponsavel(request.nomeRespo())
                .cpfResponsavel(request.cpfRespo())
                .dataNascimentoResponsavel(request.dataNascResp())
                .telefone(request.telefone())
                .valorPagamento(request.valorPag())
                .diaPagamento(request.diaPaga())
                .build();
    }

    public static AlunoResponse toAlunoResponse(Aluno aluno) {
        return new AlunoResponse(
                aluno.getId(),
                aluno.getNome(),
                aluno.getCpf(),
                aluno.getDataNascimento(),
                aluno.getNomeResponsavel(),
                aluno.getCpfResponsavel(),
                aluno.getDataNascimentoResponsavel(),
                aluno.getTelefone(),
                aluno.getValorPagamento(),
                aluno.getDiaPagamento(),
                EscolaMapper.toEscolaResponse(aluno.getEscola()),
                PeriodoMapper.toPeriodoResponde(aluno.getPeriodo())
        );
    }
}
