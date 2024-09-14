package br.com.fiap.agroclimate.model;

import br.com.fiap.agroclimate.dto.safra.AtualizarSafraDto;
import br.com.fiap.agroclimate.dto.safra.CadastrarSafraDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Safra")
@EntityListeners(AuditingEntityListener.class)
public class Safra {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_safra")
    private Long id;

    @Column(name = "dt_inicio", nullable = false)
    private LocalDateTime dataInicio;

    @Column(name = "dt_termino", nullable = false)
    private LocalDateTime dataTermino;

    @ManyToMany(mappedBy = "safras")
    private List<Fazenda> fazendas;

    public Safra(CadastrarSafraDto safraDto) {
        dataInicio = safraDto.dataInicio();
        dataTermino = safraDto.dataTermino();
    }

    public void atualizarSafra(AtualizarSafraDto dto) {
        if (dto.dataInicio() != null)
            dataInicio = dto.dataInicio();
        if (dto.dataTermino() != null)
            dataTermino = dto.dataTermino();
    }

}


