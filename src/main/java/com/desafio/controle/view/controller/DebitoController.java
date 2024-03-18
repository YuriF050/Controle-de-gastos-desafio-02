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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.desafio.controle.services.DebitoServices;
import com.desafio.controle.shared.DebitoDTO;
import com.desafio.controle.view.model.DebitoRequest;
import com.desafio.controle.view.model.DebitoResponse;

@RestController
@RequestMapping("/api/controle/debito")
public class DebitoController {
    
    @Autowired
    private DebitoServices debitoServices;

    /**
     * 
     * @return Retorna um response do debito atualizado e o status 200 OK
     */
    @GetMapping
    public ResponseEntity<List<DebitoResponse>> obterTodos(){
        List<DebitoDTO> debitos = debitoServices.obterTodos();

        ModelMapper mapper = new ModelMapper();

        List<DebitoResponse> resposta = debitos.stream()
        .map(debitodto -> mapper.map(debitodto, DebitoResponse.class))
        .collect(Collectors.toList());

        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    /**
     * 
     * @param id id do debito a ser obtido
     * @return Retorna um Optinal de Debito e o  status 200 OK
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<DebitoResponse>> obterPorId(@PathVariable Integer id){
        Optional<DebitoDTO> dto =  debitoServices.obterPorId(id);
        
         DebitoResponse debito = new ModelMapper().map(dto.get(), DebitoResponse.class);

        return new ResponseEntity<>(Optional.of(debito), HttpStatus.OK);   

        
    }

     /**
     * 
     * @param debitoreq debito a ser adicionado
     * @return Retorna um response do debito adicionado
     */
    @PostMapping
    public ResponseEntity<DebitoResponse> adicionar (@RequestBody   DebitoRequest debitoreq){
        ModelMapper mapper = new ModelMapper();

        DebitoDTO debitodto = mapper.map(debitoreq, DebitoDTO.class);

        debitodto = debitoServices.adicionar(debitodto);

        return new ResponseEntity<>(mapper.map(debitodto, DebitoResponse.class), HttpStatus.CREATED);
    }

    /**
     * 
     * @param id Id do debito a ser deletado
     * @return Retorna o status 204 NO content
     */
     @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id){

        debitoServices.deletar(id); 
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /**
     * 
     * @param id Id do debito a ser atualizado 
     * @param debitoreq debito a ser atualizado 
     * @return Retorna um response com o debito atualizado 
     */
    @PutMapping("/{id}")
    public ResponseEntity<DebitoResponse> atualizar(@PathVariable Integer id,@RequestBody DebitoRequest debitoreq ){

        ModelMapper mapper = new ModelMapper();

        DebitoDTO debitodto = mapper.map(debitoreq, DebitoDTO.class);

        debitodto =  debitoServices.atualizar(id, debitodto);

        return new ResponseEntity<>(
            mapper.map(debitodto, DebitoResponse.class),
            HttpStatus.OK);
    }

     /**
     * 
     * @return A soma dos valores recebidos
     */
    @GetMapping("/soma-valores-recebidos")
    public ResponseEntity<String> getSomaValoresRecebidos() {
        String soma = debitoServices.calcularSomaValoresRecebidos();
        return ResponseEntity.ok(soma);
    }

}
