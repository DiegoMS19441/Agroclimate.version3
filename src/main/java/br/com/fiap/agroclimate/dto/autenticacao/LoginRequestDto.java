package br.com.fiap.agroclimate.dto.autenticacao;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record LoginRequestDto (

        @NotNull@NotBlank@Email
        String login,

        @Pattern(regexp = "\\S+", message = "O campo e-mail não deve conter espaços")
        String senha) {
}
