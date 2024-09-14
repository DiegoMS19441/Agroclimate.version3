package br.com.fiap.agroclimate.dto.plantacao;

import br.com.fiap.agroclimate.model.InfoPlantacao;

import java.util.Calendar;

public record ListagemInfoPlantacaoDto(Long id, Calendar dataPlantacao, String itemPlantado, Integer areaPlantada) {

    public ListagemInfoPlantacaoDto(InfoPlantacao infoPlantacao) {
        this(infoPlantacao.getId(), infoPlantacao.getDataPlantacao(), infoPlantacao.getItemPlantado()
                , infoPlantacao.getAreaPlantada());
    }
}
