package com.desafio.controle.view.controller;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.desafio.controle.services.CreditoServices;
import com.desafio.controle.shared.CreditoDTO;
import com.desafio.controle.view.model.CreditoRequest;
import com.desafio.controle.view.model.CreditoResponse;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/controle/credito")
public class CreditoController {
    
    @Autowired
    private CreditoServices creditoServices;

    /**
     * 
     * @return Retorna um response do credito atualizado e o status 200 OK
     */
    @GetMapping
    public ResponseEntity<List<CreditoResponse>> obterTodos(){
        List<CreditoDTO> creditos = creditoServices.obterTodos();

        ModelMapper mapper = new ModelMapper();

        List<CreditoResponse> resposta = creditos.stream()
        .map(creditodto -> mapper.map(creditodto, CreditoResponse.class))
        .collect(Collectors.toList());

        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    /**
     * 
     * @param id id do credito a ser obtido
     * @return Retorna um Optinal de credito e o  status 200 OK
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<CreditoResponse>> obterPorId(@PathVariable Integer id){
        Optional<CreditoDTO> dto =  creditoServices.obterPorId(id);
        
         CreditoResponse credito = new ModelMapper().map(dto.get(), CreditoResponse.class);

        return new ResponseEntity<>(Optional.of(credito), HttpStatus.OK);   

        
    }

    /**
     * 
     * @param creditoreq credito a ser adicionado
     * @return Retorna um response do credito adicionado
     */
    @PostMapping
    public ResponseEntity<CreditoResponse> adicionar (@RequestBody  CreditoRequest creditoreq){
        ModelMapper mapper = new ModelMapper();

        CreditoDTO creditodto = mapper.map(creditoreq, CreditoDTO.class);

        creditodto = creditoServices.adicionar(creditodto);

        return new ResponseEntity<>(mapper.map(creditodto, CreditoResponse.class), HttpStatus.CREATED);
    }

    /**
     * 
     * @param id Id do credito a ser deletado
     * @return Retorna o status 204 NO content
     */
     @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id){

        creditoServices.deletar(id); 
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * 
     * @param id Id do Credito a ser atualizado 
     * @param creditoreq Credito a ser atualizado 
     * @return Retorna um response com o credito atualizado 
     */
    @PutMapping("/{id}")
    public ResponseEntity<CreditoResponse> atualizar(@PathVariable Integer id,@RequestBody CreditoRequest creditoreq ){

        ModelMapper mapper = new ModelMapper();

        CreditoDTO creditodto = mapper.map(creditoreq, CreditoDTO.class);

        creditodto =  creditoServices.atualizar(id, creditodto);

        return new ResponseEntity<>(
            mapper.map(creditodto, CreditoResponse.class),
            HttpStatus.OK);
    }

   /**
     * 
     * @return A soma dos valores recebidos
     */
    @GetMapping("/soma-valores-recebidos")
    public ResponseEntity<String> getSomaValoresRecebidos() {
        String soma = creditoServices.calcularSomaValoresRecebidos();
        return ResponseEntity.ok(soma);
    }
}
