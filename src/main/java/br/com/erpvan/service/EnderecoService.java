package br.com.erpvan.service;

import br.com.erpvan.controller.request.EnderecoRequest;
import br.com.erpvan.entity.Endereco;
import br.com.erpvan.mapper.EnderecoMapper;
import br.com.erpvan.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository repository;

    public Endereco findOrCreate(EnderecoRequest request) {
        return repository
                .findByBairroIgnoreCaseAndRuaIgnoreCaseAndNumeroIgnoreCase(
                        request.bairro(),
                        request.rua(),
                        request.numero()
                )
                .orElseGet(() -> {
                    Endereco novoEndereco = EnderecoMapper.toEndereco(request);
                    return repository.save(novoEndereco);
                });
    }

    public List<Endereco> findAll() {
        return repository.findAll();
    }

    public Optional<Endereco> findById(Long id) {
        return repository.findById(id);
    }

    public Endereco enderecoSave(Endereco endereco) {
        return repository.save(endereco);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
