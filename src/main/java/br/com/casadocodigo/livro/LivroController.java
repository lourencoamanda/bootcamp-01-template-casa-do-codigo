package br.com.casadocodigo.livro;

import br.com.casadocodigo.entity.Livro;
import br.com.casadocodigo.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
public class LivroController {

    @PersistenceContext
    private EntityManager entityManager;

   @Autowired
    private LivroRepository livroRepository;

    @PostMapping(path = "/save/livro")
    @Transactional
    public ResponseEntity<?> cadastraLivro(@Valid @RequestBody LivroRequest livroRequest){

        if(livroRepository.findByTitulo(livroRequest.getTitulo()).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Titulo já possui cadastro nas bases de livro: " + livroRequest.getTitulo());
        }

        if(livroRepository.findByIsbn(livroRequest.getIsbn()).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ISBN já possui cadastro nas bases de livro: " + livroRequest.getIsbn());
        }

        Livro novoLivro = livroRequest.toModel(entityManager);
        entityManager.persist(novoLivro);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroRequest);
    }

    @GetMapping(path = "/livros/{id}")
    public ResponseEntity<?> detalhesLivro(@PathVariable("id") Long id){

        Livro livroBuscado = entityManager.find(Livro.class, id);

        if (livroBuscado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(livroBuscado);
    }

    @GetMapping(path = "/lista/livros")

    public List<Livro> listaLivros(){
        return livroRepository.findAll();
    }

}
