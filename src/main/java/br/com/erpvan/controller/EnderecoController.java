package br.com.erpvan.controller;

import br.com.erpvan.controller.request.EnderecoRequest;
import br.com.erpvan.controller.response.EnderecoResponse;
import br.com.erpvan.entity.Endereco;
import br.com.erpvan.mapper.EnderecoMapper;
import br.com.erpvan.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/erpvan/endereco")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService enderecoService;

    @GetMapping("")
    public ResponseEntity<?> getAllEndereco() {
        List<EnderecoResponse> enderecos = enderecoService.findAll()
                .stream()
                .map(EnderecoMapper::toEnderecoResponse)
                .toList();
        if (enderecos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(enderecos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEnderecoById(@PathVariable Long id) {
        return enderecoService.findById(id)
                .map(endereco -> ResponseEntity.ok(EnderecoMapper.toEnderecoResponse(endereco)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<EnderecoResponse> saveEndereco(@RequestBody EnderecoRequest request) {
        Endereco newEndereco = EnderecoMapper.toEndereco(request);
        Endereco savedEndereco = enderecoService.enderecoSave(newEndereco);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(EnderecoMapper.toEnderecoResponse(savedEndereco));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEnderecoById(@PathVariable Long id) {
        if (enderecoService.findById(id).isPresent()) {
            enderecoService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
