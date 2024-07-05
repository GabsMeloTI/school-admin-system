package com.system.school.dto.curso;

import com.system.school.domain.Curso;
import com.system.school.dto.aluno.ListagemBasicaAlunoDto;

import java.util.List;
import java.util.stream.Collectors;

public record ListagemCursoDto(Integer codigo, String nome, String descricao, List<ListagemBasicaAlunoDto> alunos) {
    public ListagemCursoDto(Curso curso) {
        this(curso.getCodigo(), curso.getNome(), curso.getDescricao(),
                curso.getAlunos() != null ?
                        curso.getAlunos().stream().map(ListagemBasicaAlunoDto::new).collect(Collectors.toList()) :
                        List.of());
    }
}
