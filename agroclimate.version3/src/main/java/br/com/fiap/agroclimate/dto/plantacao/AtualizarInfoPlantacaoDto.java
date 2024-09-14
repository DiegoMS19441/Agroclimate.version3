package br.com.fiap.agroclimate.dto.plantacao;

import java.util.Calendar;

public record AtualizarInfoPlantacaoDto(Calendar dataPlantacao, String itemPlantado, Integer areaPlantada) {
}
