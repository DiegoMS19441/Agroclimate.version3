package br.com.fiap.agroclimate.repository;

import br.com.fiap.agroclimate.model.Agricultor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface




AgricultorRepository extends JpaRepository<Agricultor, Long> {

     Optional<Agricultor>findByLogin(String login);
}
