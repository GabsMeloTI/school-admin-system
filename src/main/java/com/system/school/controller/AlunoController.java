package com.system.school.controller;

import com.system.school.domain.Aluno;
import com.system.school.domain.Contato;
import com.system.school.domain.Curso;
import com.system.school.domain.Endereco;
import com.system.school.dto.aluno.CadastroAlunoDto;
import com.system.school.dto.aluno.ListagemAlunoDto;
import com.system.school.repository.AlunoRepository;
import com.system.school.repository.ContatoRepository;
import com.system.school.repository.CursoRepository;
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
@RequestMapping("/aluno")
public class AlunoController {
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ContatoRepository contatoRepository;

    @GetMapping
    public ResponseEntity<Page<ListagemAlunoDto>> listagem(Pageable pageable) {
        var page = alunoRepository.findAll(pageable).map(ListagemAlunoDto::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ListagemAlunoDto> cadastrar(@RequestBody @Valid CadastroAlunoDto dto, UriComponentsBuilder uriBuilder) {
        if (dto.contatoId() == null || dto.enderecoId() == null) {
            throw new IllegalArgumentException("Contato ID and Endereço ID must not be null");
        }

        Endereco endereco = enderecoRepository.findById(dto.enderecoId())
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        Contato contato = contatoRepository.findById(dto.contatoId())
                .orElseThrow(() -> new RuntimeException("Contato não encontrado"));

        var aluno = new Aluno(dto);
        aluno.setEndereco(endereco);
        aluno.setContato(contato);
        alunoRepository.save(aluno);

        var url = uriBuilder.path("/aluno/{id}").buildAndExpand(aluno.getCodigo()).toUri();
        return ResponseEntity.created(url).body(new ListagemAlunoDto(aluno));
    }


}
