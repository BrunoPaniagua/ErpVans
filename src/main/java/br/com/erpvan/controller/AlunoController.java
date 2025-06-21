package br.com.erpvan.controller;

import br.com.erpvan.controller.request.AlunoRequest;
import br.com.erpvan.controller.response.AlunoResponse;
import br.com.erpvan.entity.Aluno;
import br.com.erpvan.mapper.AlunoMapper;
import br.com.erpvan.service.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/erpvan/aluno")
@RequiredArgsConstructor
public class AlunoController {
    private final AlunoService alunoService;

    @PostMapping
    public ResponseEntity<AlunoResponse> create(
            @RequestBody @Valid AlunoRequest request) {
        Aluno aluno = AlunoMapper.toAluno(request);
        Aluno saved = alunoService.save(aluno, request.escolaId(), request.periodoId());

        if (saved == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(AlunoMapper.toAlunoResponse(saved));
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponse>> findAll() {
        return ResponseEntity.ok(
                alunoService.findAll().stream()
                        .map(AlunoMapper::toAlunoResponse)
                        .toList()
        );
    }

    @GetMapping("/buscar/{nome}")
    public ResponseEntity<List<AlunoResponse>> findByNome(
            @PathVariable String nome) {
        return ResponseEntity.ok(
                alunoService.findByNome(nome).stream()
                        .map(AlunoMapper::toAlunoResponse)
                        .toList()
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AlunoResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid AlunoRequest request) {
        Aluno alunoUpdates = AlunoMapper.toAluno(request);
        Aluno updated = alunoService.update(id, alunoUpdates, request.escolaId(), request.periodoId());

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(AlunoMapper.toAlunoResponse(updated));
    }
}
