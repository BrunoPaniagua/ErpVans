package br.com.erpvan.controller;

import br.com.erpvan.controller.request.PeriodoRequest;
import br.com.erpvan.controller.response.PeriodoResponse;
import br.com.erpvan.entity.Periodo;
import br.com.erpvan.mapper.PeriodoMapper;
import br.com.erpvan.service.PeriodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/erpvan/periodo")
@RequiredArgsConstructor
public class PeriodoController {

    private final PeriodoService periodoService;

    @GetMapping("")
    public ResponseEntity<?> getAllPeriodo() {
        List<PeriodoResponse> periodos = periodoService.findAll()
                .stream()
                .map(PeriodoMapper::toPeriodoResponde)
                .toList();

        if (periodos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(periodos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPeriodoById(@PathVariable Long id) {
        return periodoService.findById(id)
                .map(periodo -> ResponseEntity.ok().body(PeriodoMapper.toPeriodoResponde(periodo)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<PeriodoResponse> savePeriodo(@RequestBody PeriodoRequest Request) {
        Periodo newPeriodo = PeriodoMapper.toPeriodo(Request);
        Periodo savedPeriodo = periodoService.save(newPeriodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(PeriodoMapper.toPeriodoResponde(savedPeriodo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePeriodo(@PathVariable Long id) {
        if(periodoService.findById(id).isPresent()) {
            periodoService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updatePeriodo(@PathVariable Long id, @RequestBody PeriodoRequest Request) {
        Periodo updatedPeriodo = periodoService.update(id,PeriodoMapper.toPeriodo(Request));

        if(updatedPeriodo != null) {
            return ResponseEntity.ok().body(PeriodoMapper.toPeriodoResponde(updatedPeriodo));
        }
        return ResponseEntity.notFound().build();
    }
}
