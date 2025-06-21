package br.com.erpvan.service;

import br.com.erpvan.controller.request.PeriodoRequest;
import br.com.erpvan.entity.Periodo;
import br.com.erpvan.mapper.PeriodoMapper;
import br.com.erpvan.repository.PeriodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PeriodoService {

    private final PeriodoRepository repository;

    public Periodo findOrCreate(PeriodoRequest request) {
        return repository.findByPeriodoIgnoreCase(request.periodo())
                .orElseGet(() -> {
                    Periodo novoPeriodo = PeriodoMapper.toPeriodo(request);
                    return repository.save(novoPeriodo);
                });
    }

    public Optional<Periodo> findByPeriodoIgnoreCase(String periodo) {
        return repository.findByPeriodoIgnoreCase(periodo);
    }

    public List<Periodo> findAll() {
        return repository.findAll();
    }

    public Optional<Periodo> findById(Long id) {
        return repository.findById(id);
    }

    public Periodo save(Periodo periodo) {
        return repository.save(periodo);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Periodo update(Long id, Periodo periodo) {
        Optional<Periodo> periodoOptional = repository.findById(id);

        if (periodoOptional.isPresent()) {

            Periodo newPeriodo = periodoOptional.get();

            if(periodo.getPeriodo() != null) {
                newPeriodo.setPeriodo(periodo.getPeriodo());
            }

            Periodo periodoSaved = repository.save(newPeriodo);
            return periodoSaved;
        }
        return null;
    }
}
