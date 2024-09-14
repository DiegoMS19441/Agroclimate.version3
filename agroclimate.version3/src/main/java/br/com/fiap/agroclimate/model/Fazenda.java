package br.com.fiap.agroclimate.model;

import br.com.fiap.agroclimate.dto.fazenda.AtualizarFazendaDto;
import br.com.fiap.agroclimate.dto.fazenda.CadastrarFazendaDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Fazenda")
public class Fazenda {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_fazenda")
    private Long id;

    @Column(name = "cnpj_fazenda", length = 14, nullable = false, unique = true)
    private String cnpj;

    @Column(name = "nm_fazenda", nullable = false, length = 14, unique = true)
    private String razaoSocial;

    @Column(name = "cep", nullable = false)
    private String cep;

    @Column(nullable = false, length = 30)
    private String email;

    @Column(name = "tel_fazenda", precision = 11)
    private String telefone;

    @Column(name = "tamanho_fazenda", precision = 9)
    private Integer tamanhoFazenda;

    @Column(name = "clima_fazenda")
    private Clima clima;


    @ManyToOne
    @JoinColumn(name = "id_agricultor")
    private Agricultor agricultor;

    @OneToMany(mappedBy = "fazenda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Colheita> colheitas;

    @OneToOne
    @JoinColumn(name = "id_plantacao")
    private InfoPlantacao infoPlantacao;

    @OneToOne
    @JoinColumn(name = "id_solo")
    private Solo solo;

    @ManyToMany
    @JoinTable(
            name = "Fazenda_Safra",
            joinColumns = @JoinColumn(name = "id_fazenda"),
            inverseJoinColumns = @JoinColumn(name = "id_safra")
    )
    private List<Safra> safras;

    public Fazenda(CadastrarFazendaDto fazendaDto) {
        cnpj = fazendaDto.cnpj();
        razaoSocial = fazendaDto.razaoSocial();
        cep = fazendaDto.cep();
        email = fazendaDto.email();
        telefone = fazendaDto.telefone();
        tamanhoFazenda = fazendaDto.tamanhoFazenda();
        clima = fazendaDto.clima();
    }

    public void atualizarFazenda(AtualizarFazendaDto dto) {
        if (dto.cnpj() != null)
            cnpj = dto.cnpj();
        if (dto.razaoSocial() != null)
            razaoSocial = dto.razaoSocial();
        if (dto.cep() != null)
            cep = dto.cep();
        if (dto.email() != null)
            email = dto.email();
        if (dto.telefone() != null)
            telefone = dto.telefone();
        if (dto.tamanhoFazenda() != null)
            tamanhoFazenda = dto.tamanhoFazenda();
        if (dto.clima() != null)
            clima = dto.clima();
    }
}
