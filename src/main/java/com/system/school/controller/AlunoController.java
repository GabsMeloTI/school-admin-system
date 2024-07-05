package com.system.school.controller;

import com.system.school.domain.Aluno;
import com.system.school.domain.Contato;
import com.system.school.domain.Curso;
import com.system.school.domain.Endereco;
import com.system.school.dto.aluno.AlterarAlunoDto;
import com.system.school.dto.aluno.CadastroAlunoDto;
import com.system.school.dto.aluno.ListagemAlunoDto;
import com.system.school.repository.AlunoRepository;
import com.system.school.repository.ContatoRepository;
import com.system.school.repository.CursoRepository;
import com.system.school.repository.EnderecoRepository;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ContatoRepository contatoRepository;

    @GetMapping
    public ResponseEntity<Page<ListagemAlunoDto>> listagem(Pageable pageable) {
        var page = alunoRepository.findAll(pageable).map(ListagemAlunoDto::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/ordenadosAZ")
    public List<Aluno> listarAlunosOrdenados() {
        return alunoRepository.findAllByOrderByNomeAsc();
    }

    @GetMapping("/ordenadosZA")
    public List<Aluno> listarAlunosDesordenados() {
        return alunoRepository.findAllByOrderByNomeDesc();
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

    @PostMapping("/{alunoId}/curso/{cursoId}")
    public Aluno adicionarAlunoAoCurso(@PathVariable Integer alunoId, @PathVariable Integer cursoId) {
        Aluno aluno = alunoRepository.findById(alunoId).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        Curso curso = cursoRepository.findById(cursoId).orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        aluno.setCurso(curso);
        return alunoRepository.save(aluno);
    }


    @PutMapping("/{codigo}")
    @Transactional
    public ResponseEntity<ListagemAlunoDto> alterar(@PathVariable("codigo") Integer codigo, @RequestBody @Valid AlterarAlunoDto dto) {
        var aluno = alunoRepository.getReferenceById(codigo);
        aluno.alterar(dto);
        return ResponseEntity.ok(new ListagemAlunoDto(aluno));
    }

    @DeleteMapping("/{codigo}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("codigo") Integer codigo) {
        alunoRepository.deleteById(codigo);
        return ResponseEntity.noContent().build();
    }
}
