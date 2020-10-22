package br.com.casadocodigo.pais;

import br.com.casadocodigo.entity.Pais;
import br.com.casadocodigo.repositories.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class PaisController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PaisRepository paisRepository;

    @PostMapping(path = "/save/pais")
    @Transactional
    public ResponseEntity<?> cadastraPais(@Valid @RequestBody PaisRequest paisRequest){

        if(paisRepository.findByDescricao(paisRequest.getDescricao()).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pais já possui cadastro: " + paisRequest.getDescricao());
        }

        Pais novoPais = new Pais(paisRequest.getDescricao());
        entityManager.persist(novoPais);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoPais);

    }
}
