package com.system.school.dto.contato;

import com.system.school.domain.Tipo;

public record AlterarContatoDto(String telefone, String email, Tipo tipo) {
}
