package br.com.erpvan.service;

import br.com.erpvan.entity.Aluno;
import br.com.erpvan.entity.Escola;
import br.com.erpvan.entity.Periodo;
import br.com.erpvan.repository.AlunoRepository;
import br.com.erpvan.repository.EscolaRepository;
import br.com.erpvan.repository.PeriodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final EscolaRepository escolaRepository;
    private final PeriodoRepository periodoRepository;

    public Aluno save(Aluno aluno, Long escolaId, Long periodoId) {
        Optional<Escola> escolaOpt = escolaRepository.findById(escolaId);
        Optional<Periodo> periodoOpt = periodoRepository.findById(periodoId);

        if (escolaOpt.isEmpty() || periodoOpt.isEmpty()) {
            return null;
        }

        Escola escola = escolaOpt.get();
        Periodo periodo = periodoOpt.get();

        if (!escola.getPeriodos().contains(periodo)) {
            return null;
        }

        aluno.setEscola(escola);
        aluno.setPeriodo(periodo);
        return alunoRepository.save(aluno);
    }

    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    public List<Aluno> findByNome(String nome) {
        return alunoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Aluno update(Long id, Aluno alunoUpdates, Long escolaId, Long periodoId) {
        return alunoRepository.findById(id)
                .map(alunoExistente -> {
                    if (escolaId != null) {
                        escolaRepository.findById(escolaId).ifPresent(escola -> {
                            // Verifica período se for fornecido
                            if (periodoId != null) {
                                periodoRepository.findById(periodoId)
                                        .filter(p -> escola.getPeriodos().contains(p))
                                        .ifPresent(alunoExistente::setPeriodo);
                            }
                            alunoExistente.setEscola(escola);
                        });
                    } else if (periodoId != null) {
                        periodoRepository.findById(periodoId)
                                .filter(p -> alunoExistente.getEscola().getPeriodos().contains(p))
                                .ifPresent(alunoExistente::setPeriodo);
                    }

                    // Atualiza campos simples
                    if (alunoUpdates.getNome() != null) {
                        alunoExistente.setNome(alunoUpdates.getNome());
                    }
                    if (alunoUpdates.getCpf() != null) {
                        alunoExistente.setCpf(alunoUpdates.getCpf());
                    }

                    return alunoRepository.save(alunoExistente);
                })
                .orElse(null);
    }
}