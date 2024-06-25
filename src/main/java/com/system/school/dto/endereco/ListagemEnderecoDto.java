package com.system.school.dto.endereco;

import com.system.school.domain.Aluno;
import com.system.school.domain.Endereco;
import com.system.school.domain.Estado;
import com.system.school.dto.aluno.ListagemAlunoDto;

import java.util.List;
import java.util.stream.Collectors;

public record ListagemEnderecoDto(Integer codigo, String rua, Integer numero, String complemento, String bairro, String cidade, Estado estado, String cep, List<ListagemAlunoDto> alunos) {
    public ListagemEnderecoDto(Endereco endereco) {
        this(endereco.getCodigo(), endereco.getRua(), endereco.getNumero(), endereco.getComplemento(), endereco.getBairro(), endereco.getCidade(), endereco.getEstado(), endereco.getCep(), endereco.getAlunos().stream().map(ListagemAlunoDto::new).collect(Collectors.toList()));
    }
}
