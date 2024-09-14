package br.com.fiap.agroclimate.dto.safra;

import br.com.fiap.agroclimate.model.Safra;

import java.time.LocalDateTime;
import java.util.Calendar;

public record InformacaoSafraDto(

        Long id,

        LocalDateTime dataInicio,

        LocalDateTime dataTermino) {

    public InformacaoSafraDto(Safra safra) {
        this(safra.getId(), safra.getDataInicio(), safra.getDataTermino());
    }
}
