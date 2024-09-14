package br.com.fiap.agroclimate.dto.safra;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

public record CadastrarSafraDto(

        @NotNull
        @Past
        LocalDateTime dataInicio,


        @NotNull @Future
        LocalDateTime dataTermino

) {
}
