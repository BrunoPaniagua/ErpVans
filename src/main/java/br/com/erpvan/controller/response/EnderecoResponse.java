package br.com.erpvan.controller.response;

import lombok.Builder;

@Builder
public record EnderecoResponse(Long id, String bairro, String rua, String numero, String complemento) {
}
