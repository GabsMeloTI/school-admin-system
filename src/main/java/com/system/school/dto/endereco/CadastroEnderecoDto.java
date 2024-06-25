package com.system.school.dto.endereco;

import com.system.school.domain.Estado;
import com.system.school.dto.aluno.ListagemAlunoDto;

import java.util.List;

public record CadastroEnderecoDto(String rua, Integer numero, String complemento, String bairro, String cidade, Estado estado, String cep, Integer alunoId) {
}
