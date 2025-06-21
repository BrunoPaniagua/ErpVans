package br.com.erpvan.controller;

import br.com.erpvan.controller.request.EscolaRequest;
import br.com.erpvan.controller.response.EscolaResponse;
import br.com.erpvan.entity.Escola;
import br.com.erpvan.mapper.EscolaMapper;
import br.com.erpvan.service.EscolaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/erpvan/escola")
@RequiredArgsConstructor
public class EscolaController {

    private final EscolaService escolaService;

    @PostMapping("")
    public ResponseEntity<EscolaResponse> save(@RequestBody EscolaRequest request) {
       Escola escolaSaved = escolaService.save(request);
        return ResponseEntity.ok(EscolaMapper.toEscolaResponse(escolaSaved));
    }

    @GetMapping
    public ResponseEntity<List<EscolaResponse>> findAll() {
        return ResponseEntity.ok(escolaService.findAll()
                .stream()
                .map(EscolaMapper::toEscolaResponse)
                .toList());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<EscolaResponse>> buscarPorNome(
            @RequestParam String nome) {

        List<EscolaResponse> escolas = escolaService.findByNome(nome)
                .stream()
                .map(EscolaMapper::toEscolaResponse)
                .collect(Collectors.toList());

        if (escolas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(escolas);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EscolaResponse> atualizarEscola(
            @PathVariable Long id,
            @RequestBody EscolaRequest request) {

        Escola escolaAtualizada = escolaService.atualizarEscola(id, request);
        return ResponseEntity.ok(EscolaMapper.toEscolaResponse(escolaAtualizada));
    }

}
