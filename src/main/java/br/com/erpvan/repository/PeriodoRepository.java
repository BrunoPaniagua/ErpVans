package br.com.erpvan.repository;

import br.com.erpvan.entity.Periodo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PeriodoRepository extends JpaRepository<Periodo, Long> {
    Optional<Periodo> findByPeriodoIgnoreCase(String periodo);
}
