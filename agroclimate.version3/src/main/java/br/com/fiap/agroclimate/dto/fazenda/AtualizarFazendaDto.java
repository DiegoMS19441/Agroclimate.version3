package br.com.fiap.agroclimate.dto.fazenda;

import br.com.fiap.agroclimate.model.Clima;

public record AtualizarFazendaDto(Long id, String cnpj, String razaoSocial,
                                  String cep, String email, String telefone, Integer tamanhoFazenda, Clima clima) {
}
