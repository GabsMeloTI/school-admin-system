package com.system.school.dto.aluno;

import com.system.school.domain.Aluno;
import com.system.school.domain.Contato;
import com.system.school.domain.Curso;
import com.system.school.domain.Endereco;
import com.system.school.dto.contato.ListagemContatoDto;
import com.system.school.dto.curso.ListagemCursoDto;
import com.system.school.dto.endereco.ListagemEnderecoDto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record ListagemAlunoDto(Integer codigo, String nome, LocalDate nascimento, String foto, ListagemCursoDto curso, ListagemEnderecoDto endereco, List<ListagemContatoDto> telefones) {
    public ListagemAlunoDto(Aluno aluno) {
        this(aluno.getCodigo(), aluno.getNome(), aluno.getNascimento(), aluno.getFoto(), new ListagemCursoDto(aluno.getCurso()), new ListagemEnderecoDto(aluno.getEndereco()), aluno.getTelefones().stream().map(ListagemContatoDto::new).collect(Collectors.toList()));
    }
}
