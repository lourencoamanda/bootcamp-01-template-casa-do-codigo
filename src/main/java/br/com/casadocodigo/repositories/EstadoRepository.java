package br.com.casadocodigo.repositories;

import br.com.casadocodigo.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
    Optional<Estado> findByNome(String titulo);
    Optional<Estado> findById(Long id);

    }
