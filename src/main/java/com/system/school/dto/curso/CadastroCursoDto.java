package com.system.school.dto.curso;

import com.system.school.dto.aluno.ListagemAlunoDto;

import java.util.List;

public record CadastroCursoDto(String nome, String descricao, Integer alunosId) {
}
