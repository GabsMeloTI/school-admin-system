package com.system.school.controller;

import com.system.school.domain.Endereco;
import com.system.school.dto.curso.ListagemCursoDto;
import com.system.school.dto.endereco.AlterarEnderecoDto;
import com.system.school.dto.endereco.CadastroEnderecoDto;
import com.system.school.dto.endereco.ListagemEnderecoDto;
import com.system.school.repository.EnderecoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;


    @GetMapping
    public ResponseEntity<Page<ListagemEnderecoDto>> listagem(Pageable pageable) {
        var page = enderecoRepository.findAll(pageable).map(ListagemEnderecoDto::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ListagemEnderecoDto> cadastro(@RequestBody @Valid CadastroEnderecoDto dto, UriComponentsBuilder uriBuilder) {
        var endereco = new Endereco(dto);
        enderecoRepository.save(endereco);
        var url = uriBuilder.path("endereco/{id}").buildAndExpand(endereco.getCodigo()).toUri();
        return ResponseEntity.created(url).body(new ListagemEnderecoDto(endereco));
    }

    @PutMapping("{codigo}")
    @Transactional
    public ResponseEntity<ListagemEnderecoDto> alterar(@PathVariable("codigo") Integer codigo, @RequestBody @Valid AlterarEnderecoDto dto) {
        var endereco = enderecoRepository.getReferenceById(codigo);
        endereco.alterar(dto);
        return ResponseEntity.ok(new ListagemEnderecoDto(endereco));
    }

    @DeleteMapping("{codigo}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable("codigo") Integer codigo) {
        enderecoRepository.deleteById(codigo);
        return ResponseEntity.noContent().build();
    }
}
