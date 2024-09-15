package br.com.fiap.agroclimate.model;

import br.com.fiap.agroclimate.dto.solo.AtualizarSoloDto;
import br.com.fiap.agroclimate.dto.solo.CadastrarSoloDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "Solo")
public class Solo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_solo")
    private Long id;

    @Column(name = "tipo_solo", length = 30, nullable = false)
    private String tipoSolo;

    @Column(name = "ph_solo")
    private Integer phSolo;

    @Column(name = "nutrientes_solo")
    private String nutrientesSolo;

    @OneToOne(mappedBy = "solo")
    private Fazenda fazenda;

    public Solo(CadastrarSoloDto soloDto) {
        tipoSolo = soloDto.tipoSolo();
        phSolo = soloDto.phSolo();
        nutrientesSolo = soloDto.nutrientesSolo();
    }

    public void atualizarSolo(AtualizarSoloDto dto) {
        if (dto.tipoSolo() != null)
            tipoSolo = dto.tipoSolo();
        if (dto.phSolo() != null)
            phSolo = dto.phSolo();
        if (dto.nutrientesSolo() != null)
            nutrientesSolo = dto.nutrientesSolo();
    }

}
