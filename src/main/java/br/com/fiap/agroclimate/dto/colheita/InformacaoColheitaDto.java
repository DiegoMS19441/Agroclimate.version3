package br.com.fiap.agroclimate.dto.colheita;

import br.com.fiap.agroclimate.model.Colheita;

import java.util.Calendar;

public record InformacaoColheitaDto(Long id, Calendar dataColheita, Integer quantidade) {

    public InformacaoColheitaDto(Colheita colheita) {
        this(colheita.getId(), colheita.getDataColheita(),
                colheita.getQuantidade());
    }
}
