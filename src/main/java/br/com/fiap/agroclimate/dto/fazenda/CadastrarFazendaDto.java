package br.com.fiap.agroclimate.dto.fazenda;

import br.com.fiap.agroclimate.model.Clima;
import jakarta.validation.constraints.*;

public record CadastrarFazendaDto(
        @NotNull
        @Size(max = 14, min = 14)
        String cnpj,

        @NotBlank
        @Size(max = 100, min = 10)
        String razaoSocial,


        @Size(max = 8, min = 8)
        @NotNull
        String cep,

        @NotNull
        @NotBlank
        @Email
        String email,

        @NotNull
        @Size(max = 11, min = 11)
        String telefone,

        @NotNull @Min(100)
        Integer tamanhoFazenda,

        @NotNull
        Clima clima) {
}
