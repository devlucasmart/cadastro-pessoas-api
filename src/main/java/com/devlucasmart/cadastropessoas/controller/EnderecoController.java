package com.devlucasmart.cadastropessoas.controller;

import com.devlucasmart.cadastropessoas.dto.endereco.EnderecoRequest;
import com.devlucasmart.cadastropessoas.dto.endereco.EnderecoResponse;
import com.devlucasmart.cadastropessoas.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/endereco")
public class EnderecoController {
    private final EnderecoService service;

    @GetMapping
    public ResponseEntity<List<EnderecoResponse>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<EnderecoResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody EnderecoRequest request) {
        var endereco = service.save(request);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(endereco)
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody EnderecoRequest request) {
        service.update(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
