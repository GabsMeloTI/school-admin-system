package com.system.school.dto.contato;

import com.system.school.domain.Tipo;
import com.system.school.dto.aluno.ListagemAlunoDto;

public record CadastroContatoDto(String telefone, String email, Tipo tipo, Integer alunoId) {
}
