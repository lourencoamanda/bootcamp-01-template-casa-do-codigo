package br.com.casadocodigo.repositories;

import br.com.casadocodigo.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByTitulo(String titulo);
    Optional<Livro> findByIsbn(String isbn);

    }
