package br.com.erpvan.repository;

import br.com.erpvan.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Optional<Endereco> findByBairroIgnoreCaseAndRuaIgnoreCaseAndNumeroIgnoreCase(String bairro, String rua, String numero);
}
