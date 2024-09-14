package br.com.fiap.agroclimate.dto.fazenda;

import br.com.fiap.agroclimate.dto.usuario.agricultor.DetalhesAgricultorDto;
import br.com.fiap.agroclimate.model.Clima;
import br.com.fiap.agroclimate.model.Fazenda;

public record DetalhesFazendaDto(Long id, String cnpj, String razaoSocial, String cep, String email,
                                 String telefone, Integer tamanhoFazenda, Clima clima) {

    public DetalhesFazendaDto(Fazenda fazenda) {
        this(fazenda.getId(), fazenda.getCnpj(), fazenda.getRazaoSocial(),
                fazenda.getCep(), fazenda.getEmail(), fazenda.getTelefone(), fazenda.getTamanhoFazenda(), fazenda.getClima());
    }
}


