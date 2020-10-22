package br.com.casadocodigo.repositories;

import br.com.casadocodigo.entity.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CupomRepository extends  JpaRepository<Cupom, Long> {
    Optional<Cupom> findById(Long Id);
    Optional<Cupom> findByCodigoCupom(String CodigoCupom);
}
