package br.com.fiap.agroclimate.dto.usuario.agricultor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CadastroAgricultor(

        @NotNull@NotBlank
        String nome,
        @Email@NotBlank@NotNull
        String login,
        @NotBlank@NotNull@Pattern(regexp = "\\S+", message = "O campo e-mail não deve conter espaços")
        String senha) {

}
