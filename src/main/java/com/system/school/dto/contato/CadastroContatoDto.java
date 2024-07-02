package com.system.school.dto.contato;

import com.system.school.domain.Tipo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CadastroContatoDto(
        @NotBlank @Size(max = 11)
        String telefone,

        @NotBlank @Email @Size(max = 150)
        String email,

        Tipo tipo
) {}
