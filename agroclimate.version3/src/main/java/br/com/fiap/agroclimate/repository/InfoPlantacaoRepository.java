package br.com.fiap.agroclimate.repository;

import br.com.fiap.agroclimate.model.InfoPlantacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InfoPlantacaoRepository extends JpaRepository<InfoPlantacao, Long> {
}
