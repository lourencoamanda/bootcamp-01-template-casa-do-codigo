package br.com.casadocodigo.cupom;

import br.com.casadocodigo.entity.Cupom;
import br.com.casadocodigo.repositories.CupomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class CupomController {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CupomRepository cupomRepository;

    @PostMapping(path = "/save/cupom")
    @Transactional
    public ResponseEntity<?> cadastraCupom(@Valid @RequestBody CupomRequest cupomRequest) {

        if(cupomRepository.findByCodigoCupom(cupomRequest.getCodigoCupom()).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cupomRequest);
        }

        Cupom novoCupom = new Cupom(cupomRequest.getCodigoCupom(),cupomRequest.getPercentualDesconto(),cupomRequest.getValidade());
        entityManager.persist(novoCupom);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoCupom);

    }

    @PutMapping(path = "/atualiza/cupom/{id}")
    @Transactional
    public ResponseEntity<?> atualizaCupom(@PathVariable("id") Long id, @RequestBody @Valid CupomRequest cupomRequest ) {

        return cupomRepository.findById(id).map(cupom -> {
                    cupom.setCodigoCupom(cupomRequest.getCodigoCupom());
                    cupom.setPercentualDesconto(cupomRequest.getPercentualDesconto());
                    cupom.setValidade(cupomRequest.getValidade());
                    Cupom updated = cupomRepository.save(cupom);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }
}