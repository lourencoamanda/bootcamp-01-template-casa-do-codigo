package br.com.casadocodigo.compra;

import br.com.casadocodigo.entity.*;
import br.com.casadocodigo.estado.ValidaEstadoPertencePais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class CompraController {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ValidaEstadoPertencePais validaEstadoPertencePais;

    @Autowired
    private ValidaCpfCnpj validaCpfCnpj;

    @Autowired
    private ValidaCupom validaCupom;

    @Autowired
    private ValidaTotal validaTotal;

    @InitBinder
    public void init(WebDataBinder binder) {
       binder.addValidators(validaCpfCnpj, validaEstadoPertencePais,validaTotal,validaCupom);
    }

    @PostMapping(path = "/validar/compra")
    @Transactional
    public ResponseEntity<?> validarCliente(@Valid @RequestBody CompraRequest compraRequest) {

        Cliente novoCliente = compraRequest.toModel(entityManager);
        novoCliente.toModelCupom(compraRequest.getCodigoCupom(), entityManager);

        entityManager.persist(novoCliente);
        URI location = URI.create(String.format("/validar/compra", novoCliente.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(compraRequest);
    }

    @GetMapping(path = "/compra/{id}")
    public ResponseEntity<?> consultaDetalheCompra(@PathVariable("id") Long id){

        Cliente buscaDetalheCompra = entityManager.find(Cliente.class, id);
        if (buscaDetalheCompra == null){
          return ResponseEntity.notFound().build(); }
        return ResponseEntity.status(HttpStatus.OK).body(buscaDetalheCompra);

    }
}