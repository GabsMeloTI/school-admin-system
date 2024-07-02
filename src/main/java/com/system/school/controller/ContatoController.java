package com.system.school.controller;

import com.system.school.domain.Contato;
import com.system.school.dto.contato.AlterarContatoDto;
import com.system.school.dto.contato.CadastroContatoDto;
import com.system.school.dto.contato.ListagemContatoDto;
import com.system.school.repository.AlunoRepository;
import com.system.school.repository.ContatoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/contato")
public class ContatoController {

    @Autowired
    private ContatoRepository contatoRepository;

    @GetMapping
    public ResponseEntity<Page<ListagemContatoDto>> listagem(Pageable pageable) {
        var page = contatoRepository.findAll(pageable).map(ListagemContatoDto::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ListagemContatoDto> cadastro(@RequestBody @Valid CadastroContatoDto dto, UriComponentsBuilder uriBuilder) {
        var contato = new Contato(dto);
        contatoRepository.save(contato);
        var url = uriBuilder.path("/contato/{id}").buildAndExpand(contato.getCodigo()).toUri();
        return ResponseEntity.created(url).body(new ListagemContatoDto(contato));
    }

    @PutMapping("{codigo}")
    @Transactional
    public ResponseEntity<ListagemContatoDto> alterar(@PathVariable("codigo") Integer codigo, @RequestBody @Valid AlterarContatoDto dto) {
        var contato = contatoRepository.getReferenceById(codigo);
        contato.alterar(dto);
        return ResponseEntity.ok(new ListagemContatoDto(contato));
    }

    @DeleteMapping("{codigo}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("codigo") Integer codigo) {
        contatoRepository.deleteById(codigo);
        return ResponseEntity.noContent().build();
    }
}
