package com.system.school.dto.aluno;

import com.system.school.dto.contato.ListagemContatoDto;
import com.system.school.dto.curso.ListagemCursoDto;
import com.system.school.dto.endereco.ListagemEnderecoDto;

import java.time.LocalDate;
import java.util.List;

public record CadastroAlunoDto(String nome, LocalDate nascimento, String foto, ListagemCursoDto cursoId, ListagemEnderecoDto enderecoId, ListagemContatoDto telefoneId) {
}
