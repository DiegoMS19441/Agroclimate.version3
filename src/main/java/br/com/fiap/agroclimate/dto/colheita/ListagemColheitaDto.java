package br.com.fiap.agroclimate.dto.colheita;

import br.com.fiap.agroclimate.model.Colheita;

import java.util.Calendar;

public record ListagemColheitaDto(Long id, Calendar dataColheita,
                                  Integer quantidade) {

    public ListagemColheitaDto(Colheita colheita) {
        this(colheita.getId(), colheita.getDataColheita(),
                colheita.getQuantidade());
    }

}
