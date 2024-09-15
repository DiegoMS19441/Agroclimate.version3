package br.com.fiap.agroclimate.dto.plantacao;

import br.com.fiap.agroclimate.model.InfoPlantacao;

import java.util.Calendar;

public record DetalhePlantacaoDto(Long id, Calendar dataPlantacao, String itemPlantado, Integer areaPlantada) {

    public DetalhePlantacaoDto(InfoPlantacao infoPlantacao) {
        this(infoPlantacao.getId(), infoPlantacao.getDataPlantacao(), infoPlantacao.getItemPlantado(), infoPlantacao.getAreaPlantada());
    }
}
