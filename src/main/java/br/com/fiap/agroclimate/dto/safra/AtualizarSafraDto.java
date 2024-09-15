package br.com.fiap.agroclimate.dto.safra;

import java.time.LocalDateTime;
import java.util.Calendar;

public record AtualizarSafraDto(LocalDateTime dataInicio,
                                LocalDateTime dataTermino) {
}
