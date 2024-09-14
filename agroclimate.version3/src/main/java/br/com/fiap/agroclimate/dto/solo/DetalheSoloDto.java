package br.com.fiap.agroclimate.dto.solo;

import br.com.fiap.agroclimate.model.Solo;

public record DetalheSoloDto(Long id, String tipoSolo, Integer phSolo, String nutrientesSolo) {

    public DetalheSoloDto(Solo Solo) {
        this(Solo.getId(), Solo.getTipoSolo(), Solo.getPhSolo(), Solo.getNutrientesSolo());

    }
}
