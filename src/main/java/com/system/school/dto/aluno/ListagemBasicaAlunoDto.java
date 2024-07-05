package com.system.school.dto.aluno;

import com.system.school.domain.Aluno;
import com.system.school.dto.contato.ListagemContatoDto;
import com.system.school.dto.endereco.ListagemEnderecoDto;

public record ListagemBasicaAlunoDto(Integer codigo, String nome) {
    public ListagemBasicaAlunoDto(Aluno aluno) {
        this(aluno.getCodigo(), aluno.getNome());
    }
}
