package br.com.fiap.agroclimate.repository;

import br.com.fiap.agroclimate.model.Colheita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ColheitaRepository extends JpaRepository<Colheita, Long> {
}
