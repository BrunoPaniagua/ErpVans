package br.com.erpvan.service;

import br.com.erpvan.entity.Endereco;
import br.com.erpvan.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository repository;

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
