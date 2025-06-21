package br.com.erpvan.controller.response;

import br.com.erpvan.controller.request.EnderecoRequest;
import lombok.Builder;

import java.util.List;

@Builder
public record EscolaResponse(Long id, String nome, EnderecoResponse enderecoResponse, List<PeriodoResponse> periodoResponse) {
}
