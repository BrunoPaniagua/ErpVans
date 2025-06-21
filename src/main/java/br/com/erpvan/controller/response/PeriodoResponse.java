package br.com.erpvan.controller.response;

import lombok.Builder;

@Builder
public record PeriodoResponse(Long id, String periodo) {
}
