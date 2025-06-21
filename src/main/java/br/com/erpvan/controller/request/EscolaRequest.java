package br.com.erpvan.controller.request;

import java.util.List;

public record EscolaRequest(String nome, EnderecoRequest enderecoRequest, List<String> periodos) {
}
