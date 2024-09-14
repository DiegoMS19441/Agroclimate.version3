package br.com.fiap.agroclimate.dto.usuario.agricultor;

import br.com.fiap.agroclimate.model.Agricultor;

public record DetalhesAgricultorDto(Long id, String nome, String login, String senha) {

    public DetalhesAgricultorDto(Agricultor agricultor) {
        this(agricultor.getId(), agricultor.getNome(), agricultor.getLogin(), agricultor.getSenha());
    }

}
