package br.com.fiap.agroclimate.dto.solo;

import br.com.fiap.agroclimate.model.Solo;

public record ListagemSoloDto(Long id, String tipoSolo, Integer phSolo, String nutrientesSolo) {

    public ListagemSoloDto(Solo Solo) {
        this(Solo.getId(),Solo.getTipoSolo(), Solo.getPhSolo(), Solo.getNutrientesSolo());

    }
}
