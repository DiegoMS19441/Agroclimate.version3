package br.com.fiap.agroclimate.dto.usuario.agricultor;

import br.com.fiap.agroclimate.model.Agricultor;

public record ListagemAgricultorDto( String nome, String login, String senha) {

    public ListagemAgricultorDto(Agricultor agricultor){
        this(agricultor.getNome(),agricultor.getLogin(),agricultor.getSenha());
    }
}
