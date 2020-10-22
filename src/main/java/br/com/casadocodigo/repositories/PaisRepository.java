package br.com.casadocodigo.repositories;

import br.com.casadocodigo.entity.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {
    Optional<Pais> findById(Long id);
    Optional<Pais> findByDescricao(String descricao);
}
