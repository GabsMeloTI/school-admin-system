package com.system.school.dto.aluno;

import com.system.school.domain.Aluno;
import com.system.school.dto.contato.ListagemContatoDto;
import com.system.school.dto.curso.ListagemCursoDto;
import com.system.school.dto.endereco.ListagemEnderecoDto;

import java.time.LocalDate;

public record ListagemAlunoDto(Integer codigo, String nome, LocalDate nascimento, String foto, ListagemContatoDto contato, ListagemEnderecoDto endereco, ListagemCursoDto curso) {
    public ListagemAlunoDto(Aluno aluno) {
        this(aluno.getCodigo(), aluno.getNome(), aluno.getNascimento(), aluno.getFoto(),
                aluno.getContato() != null ? new ListagemContatoDto(aluno.getContato()) : null,
                aluno.getEndereco() != null ? new ListagemEnderecoDto(aluno.getEndereco()) : null,
                aluno.getCurso() != null ? new ListagemCursoDto(aluno.getCurso()) : null);
    }
}
