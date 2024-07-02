package com.system.school.dto.aluno;

import com.system.school.dto.contato.ListagemContatoDto;
import com.system.school.dto.curso.ListagemCursoDto;
import com.system.school.dto.endereco.ListagemEnderecoDto;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record CadastroAlunoDto(
        String nome,
        LocalDate nascimento,
        String foto,
        @NotNull(message = "Contato ID must not be null")
        Integer contatoId,
        @NotNull(message = "Endereço ID must not be null")
        Integer enderecoId) {
}
