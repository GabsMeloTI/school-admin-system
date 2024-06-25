package com.system.school.dto.endereco;

import com.system.school.domain.Estado;

public record AlterarEnderecoDto(String rua, Integer numero, String complemento, String bairro, String cidade, Estado estado, String cep) {
}
