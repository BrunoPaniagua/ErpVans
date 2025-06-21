package br.com.erpvan.service;

import br.com.erpvan.controller.request.EnderecoRequest;
import br.com.erpvan.controller.request.EscolaRequest;
import br.com.erpvan.controller.request.PeriodoRequest;
import br.com.erpvan.entity.Endereco;
import br.com.erpvan.entity.Escola;
import br.com.erpvan.entity.Periodo;
import br.com.erpvan.mapper.EnderecoMapper;
import br.com.erpvan.mapper.PeriodoMapper;
import br.com.erpvan.repository.EnderecoRepository;
import br.com.erpvan.repository.EscolaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EscolaService {

    private final EscolaRepository escolaRepository;
    private final PeriodoService periodoService;
    private final EnderecoService enderecoService;

    public Escola save(EscolaRequest request) {
        Endereco endereco = enderecoService.findOrCreate(request.enderecoRequest());

        List<Periodo> periodos = request.periodos().stream()
                .map(periodo -> periodoService.findOrCreate(new PeriodoRequest(periodo)))
                .collect(Collectors.toList());

        Escola escola = Escola.builder()
                .nome(request.nome())
                .endereco(endereco)
                .periodos(periodos)
                .build();

        return escolaRepository.save(escola);
    }

    public Escola atualizarEscola(Long id, EscolaRequest request) {
        Escola escolaExistente = escolaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Escola não encontrada"));


        Endereco enderecoAtualizado = atualizarEnderecoSeNecessario(
                escolaExistente.getEndereco(),
                request.enderecoRequest());


        List<Periodo> periodosAtualizados = atualizarPeriodosSeNecessario(
                escolaExistente.getPeriodos(),
                request.periodos());


        escolaExistente.setNome(request.nome());
        escolaExistente.setEndereco(enderecoAtualizado);
        escolaExistente.setPeriodos(periodosAtualizados);

        return escolaRepository.save(escolaExistente);
    }

    private Endereco atualizarEnderecoSeNecessario(Endereco enderecoAtual, EnderecoRequest novoEnderecoRequest) {

        if (enderecoAtual.equals(EnderecoMapper.toEndereco(novoEnderecoRequest))) {
            return enderecoAtual;
        }


        long escolasComEsteEndereco = escolaRepository.countByEnderecoId(enderecoAtual.getId());


        if (escolasComEsteEndereco <= 1) {
            enderecoAtual.setBairro(novoEnderecoRequest.bairro());
            enderecoAtual.setRua(novoEnderecoRequest.rua());
            enderecoAtual.setNumero(novoEnderecoRequest.numero());
            enderecoAtual.setComplemento(novoEnderecoRequest.complemento());
            return enderecoService.enderecoSave(enderecoAtual);
        }


        return enderecoService.findOrCreate(novoEnderecoRequest);
    }

    private List<Periodo> atualizarPeriodosSeNecessario(List<Periodo> periodosAtuais, List<String> novosPeriodos) {

        List<Periodo> periodosRequest = novosPeriodos.stream()
                .map(p -> new PeriodoRequest(p))
                .map(PeriodoMapper::toPeriodo)
                .collect(Collectors.toList());


        if (new HashSet<>(periodosAtuais).equals(new HashSet<>(periodosRequest))) {
            return periodosAtuais;
        }

        return periodosRequest.stream()
                .map(periodo -> {
                    Optional<Periodo> existente = periodoService.findByPeriodoIgnoreCase(periodo.getPeriodo());
                    if (existente.isPresent() && !periodosAtuais.contains(existente.get())) {
                        // Se já existe mas não estava na escola, cria novo
                        return periodoService.save(new Periodo(null, periodo.getPeriodo()));
                    }
                    return existente.orElseGet(() -> periodoService.save(periodo));
                })
                .collect(Collectors.toList());
    }

    public List<Escola> findByNome(String nome) {
        return escolaRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Escola> findAll() {
        return escolaRepository.findAll();
    }

}
