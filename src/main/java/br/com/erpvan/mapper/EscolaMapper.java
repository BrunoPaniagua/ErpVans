package br.com.erpvan.mapper;

import br.com.erpvan.controller.request.EscolaRequest;
import br.com.erpvan.controller.response.EnderecoResponse;
import br.com.erpvan.controller.response.EscolaResponse;
import br.com.erpvan.controller.response.PeriodoResponse;
import br.com.erpvan.entity.Endereco;
import br.com.erpvan.entity.Escola;
import br.com.erpvan.entity.Periodo;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class EscolaMapper {

    public static Escola toEscola(EscolaRequest request) {

        List<Periodo> periodos = request.periodos().stream()
                .map(periodo -> Periodo.builder().periodo(periodo).build())
                .collect(Collectors.toList());

        Endereco endereco = EnderecoMapper.toEndereco(request.enderecoRequest());

        return Escola.builder()
                .nome(request.nome())
                .endereco(endereco)
                .periodos(periodos)
                .build();
    }

    public static EscolaResponse toEscolaResponse(Escola escola) {
        List<PeriodoResponse> periodos = escola.getPeriodos().stream()
                .map(PeriodoMapper::toPeriodoResponde)
                .collect(Collectors.toList());

        EnderecoResponse endereco = EnderecoMapper.toEnderecoResponse(escola.getEndereco());

        return EscolaResponse.builder()
                .id(escola.getId())
                .nome(escola.getNome())
                .enderecoResponse(endereco)
                .periodoResponse(periodos)
                .build();
    }

}
