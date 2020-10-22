package br.com.casadocodigo.estado;

import br.com.casadocodigo.entity.Estado;
import br.com.casadocodigo.repositories.EstadoRepository;
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
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private EntityManager entityManager;

    @PostMapping(path = "/save/estado")
    @Transactional
    public ResponseEntity<?> cadastraEstado(@Valid @RequestBody EstadoRequest estadoRequest) {

       if(estadoRepository.findByNome(estadoRequest.getNome()).isPresent()){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Estado já possui cadastro:  " + estadoRequest.getNome());
      }
        Estado novoEstado = estadoRequest.toModel(entityManager);
        entityManager.persist(novoEstado);

        return ResponseEntity.status(HttpStatus.CREATED).body("Estado cadastrado com sucesso!" + novoEstado);

    }

}
