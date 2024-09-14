package br.com.fiap.agroclimate.dto.colheita;

import java.util.Calendar;

public record AtualizarColheitaDto(Calendar dataColheita, Integer quantidade) {
}
