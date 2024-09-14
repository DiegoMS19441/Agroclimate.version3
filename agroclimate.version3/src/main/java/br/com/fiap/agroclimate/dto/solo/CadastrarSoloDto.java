package br.com.fiap.agroclimate.dto.solo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastrarSoloDto(
        @NotNull @NotBlank
        String tipoSolo,
        @NotNull @Min(1) @Max(14)
        Integer phSolo,
        @NotNull @NotBlank
        String nutrientesSolo) {
}
