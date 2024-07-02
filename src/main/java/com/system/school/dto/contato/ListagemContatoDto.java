package com.system.school.dto.contato;

import com.system.school.domain.Aluno;
import com.system.school.domain.Contato;
import com.system.school.domain.Tipo;
import com.system.school.dto.aluno.ListagemAlunoDto;

public record ListagemContatoDto(Integer codigo, String telefone, String email, Tipo tipo) {
    public ListagemContatoDto(Contato contato) {
        this(contato.getCodigo(), contato.getTelefone(), contato.getEmail(), contato.getTipo());
    }
}
