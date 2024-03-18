package com.desafio.controle.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.controle.model.Creditos;
import com.desafio.controle.model.Debitos;
import com.desafio.controle.model.exception.ResourceNotFoundException;
import com.desafio.controle.repository.DebitoRepository;
import com.desafio.controle.shared.DebitoDTO;


@Service
public class DebitoServices {
    
    @Autowired
    private DebitoRepository debitoRepository;

    /**
     * 
     * @return Retorna todos os debitos
     */
    public List<DebitoDTO> obterTodos() {

        List<Debitos> debitos = debitoRepository.findAll();
        
        return debitos.stream()
        .map(debito -> new ModelMapper().map(debito, DebitoDTO.class))
        .collect(Collectors.toList());

    }

    /**
     * 
     * @param id id do debito a ser requisitado
     * @return Retorna o debito requisitado pelo id
     * @throws - Caso não ache o id dispara uma mensagem
     */
    public Optional<DebitoDTO> obterPorId(Integer id) {
        // obtendo optinal de Debito pelo ID
        Optional<Debitos> debito = debitoRepository.findById(id);
        //  se nao achar avisa
        if(debito.isEmpty()){
            throw new ResourceNotFoundException("Debito com o id "+ id + " não encontrado!");
        }
        //  Convertendo meu opitinal para um DTO
        DebitoDTO dto = new ModelMapper().map(debito.get(), DebitoDTO.class);

        // Criando e retornando um optinal de Debitodto
        return Optional.of(dto);
    }

    /**
     * 
     * @param debitodto Adiciona um debito de acordo com o modelo base utilizando um DTO
     * @return Retorna o debito adicionado
     */
    public DebitoDTO adicionar(DebitoDTO debitodto) {
        // Removendo Id para conseguir fazer o cadastro
        debitodto.setId(null);
        
        // Criar um mapeamento
        ModelMapper mapper = new ModelMapper();

        // Converter o DebitoDTO em um produto 
        Debitos debito = mapper.map(debitodto, Debitos.class);

        // Salvar o Debito no banco
        debito = debitoRepository.save(debito);

        debitodto.setId(debito.getId());

        // retornar o DebitoDTO atualizado

        return debitodto;


    }
    /**
     * 
     * @param id Deleta o debito com o id mencionado no path
     */
    public void deletar(Integer id) {
        // Verificar se o produto existe

        Optional <Debitos> debito = debitoRepository.findById(id);

        //  Se nao existir lança um exception
        if(debito.isEmpty()){
            throw new ResourceNotFoundException("Não foi possivel deletar o debito com id: "+ id + " - Produto não existe!");
        }

        debitoRepository.deleteById(id);
    }
    /**
     * 
     * @param id Id do debito a ser atualizado
     * @param debitodto Atualiza um debito de acordo com o modelo base utilizando um DTO
     * @return Retorna o debito atualizado 
     */
    public DebitoDTO atualizar(Integer id, DebitoDTO debitodto) {
     
        // Passar o id para o Debito dto
        debitodto.setId(id);

        // Criar um objeto de mapeamento
        ModelMapper mapper = new ModelMapper();

        //  Converter o Debitodto em Debito
        Debitos debito = mapper.map(debitodto, Debitos.class);

        // Atualizar o Debito no banco de dados.
        debitoRepository.save(debito); 

        // Retornar o Debito atualizado
        return debitodto;
    }
    /**
     * 
     * @return A soma dos valores recebidos
     */
    public  String calcularSomaValoresRecebidos() {
        List<Debitos> debitos = debitoRepository.findAll();
        BigDecimal soma = BigDecimal.ZERO;
        for (Debitos debito : debitos) {
            soma = soma.add(BigDecimal.valueOf(debito.getValorPago()));
        }
        return "O valor total a pagar é "+ soma + " reais.";
    }
}

