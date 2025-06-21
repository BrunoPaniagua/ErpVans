package br.com.erpvan.mapper;

import br.com.erpvan.controller.request.EnderecoRequest;
import br.com.erpvan.controller.response.EnderecoResponse;
import br.com.erpvan.entity.Endereco;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EnderecoMapper {

    public static Endereco toEndereco(EnderecoRequest enderecoRequest) {
        return Endereco
                .builder()
                .bairro(enderecoRequest.bairro())
                .rua(enderecoRequest.rua())
                .numero(enderecoRequest.numero())
                .complemento(enderecoRequest.complemento())
                .build();
    }

    public static EnderecoResponse toEnderecoResponse(Endereco endereco) {
        return EnderecoResponse
                .builder()
                .id(endereco.getId())
                .bairro(endereco.getBairro())
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .complemento(endereco.getComplemento())
                .build();
    }
}
