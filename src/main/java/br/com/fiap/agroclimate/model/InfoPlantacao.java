package br.com.fiap.agroclimate.model;

import br.com.fiap.agroclimate.dto.plantacao.AtualizarInfoPlantacaoDto;
import br.com.fiap.agroclimate.dto.plantacao.CadastrarInfoPlantacaoDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
@NoArgsConstructor
@Entity

@Table(name = "Plantacao")
public class InfoPlantacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    @Column(name = "id_plantacao")
    private Long id;

    @Column(name = "data_plantacao")
    private Calendar dataPlantacao;

    @Column(name = "item_plantado")
    private String itemPlantado;

    @Column(name = "area_plantada")
    private Integer areaPlantada;

    @OneToOne(mappedBy = "infoPlantacao")
    private Fazenda fazenda;

    public InfoPlantacao(CadastrarInfoPlantacaoDto PlantacaoDto) {
        dataPlantacao = PlantacaoDto.dataPlantacao();
        itemPlantado = PlantacaoDto.itemPlantado();
        areaPlantada = PlantacaoDto.areaPlantada();
    }

    public void atualizarInfoPlantacao(AtualizarInfoPlantacaoDto dto) {
        if (dto.dataPlantacao() != null) dataPlantacao = dto.dataPlantacao();
        if (dto.itemPlantado() != null) itemPlantado = dto.itemPlantado();
        if (dto.areaPlantada() != null) areaPlantada = dto.areaPlantada();
    }

}
