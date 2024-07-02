package com.system.school.dto.curso;

import com.system.school.domain.Curso;
import com.system.school.dto.aluno.ListagemAlunoDto;

import java.util.List;
import java.util.stream.Collectors;

public record ListagemCursoDto(Integer codigo, String nome, String descricao) {
    public ListagemCursoDto(Curso curso) {
        this(curso.getCodigo(), curso.getNome(), curso.getDescricao());
    }
}
