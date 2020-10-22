package br.com.casadocodigo.autor;

import br.com.casadocodigo.entity.Autor;
import br.com.casadocodigo.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class AutorController {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private AutorRepository autorRepository;

    @PostMapping(path = "/save/autor")
    @Transactional
    public ResponseEntity<?> cadastraAutor(@Valid @RequestBody AutorRequest requestAutor){

    if(autorRepository.findByEmail(requestAutor.getEmail()).isPresent()){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Autor já possui cadastro!" + requestAutor);
    }

    Autor novoAutor = new Autor(requestAutor.getNome(),requestAutor.getEmail(),requestAutor.getDescricao());
    entityManager.persist(novoAutor);

    return ResponseEntity.status(HttpStatus.CREATED).body("Autor Cadastrado com sucesso!" + novoAutor);

    }
}
