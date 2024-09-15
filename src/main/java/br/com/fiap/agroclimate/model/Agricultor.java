package br.com.fiap.agroclimate.model;

import br.com.fiap.agroclimate.dto.usuario.agricultor.AtualizarAgricultor;
import br.com.fiap.agroclimate.dto.usuario.agricultor.CadastroAgricultor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor@AllArgsConstructor
@Entity
@Table(name = "Agricultor")
public class Agricultor{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_AGRICULTOR")
    private Long id;

    @Column(name = "NM_AGRIGULTOR", nullable = false)
    private String nome;

    @Column(name = "EMAIL_AGRICULTOR")
    private String login;

    @Column(name = "SENHA_AGRICULTOR")
    private String senha;

    @OneToMany(mappedBy = "agricultor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Fazenda> fazendas;

    public Agricultor(CadastroAgricultor dto){
        nome = dto.nome();
        login = dto.login();
        senha = dto.senha();
    }

    public void atualizarAgricultor(AtualizarAgricultor dto) {
        if (dto.nome() != null)
            nome = dto.nome();
        if (dto.login() != null)
            login = dto.login();
        if (dto.senha() != null)
            senha = dto.senha();

    }
}

