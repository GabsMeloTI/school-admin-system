package com.system.school.controller;

import com.system.school.domain.Curso;
import com.system.school.dto.curso.CadastroCursoDto;
import com.system.school.dto.curso.ListagemCursoDto;
import com.system.school.repository.CursoRepository;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public ResponseEntity<Page<ListagemCursoDto>> listagem(Pageable pageable) {
        var page = cursoRepository.findAll(pageable).map(ListagemCursoDto::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ListagemCursoDto> cadastrar(@RequestBody @Valid CadastroCursoDto dto, UriComponentsBuilder uriBuilder) {
        var curso = new Curso(dto);
        cursoRepository.save(curso);
        var url = uriBuilder.path("curso/{id}").buildAndExpand(curso.getCodigo()).toUri();
        return ResponseEntity.created(url).body(new ListagemCursoDto(curso));
    }



}
