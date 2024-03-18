package com.desafio.controle.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.controle.model.Creditos;
import com.desafio.controle.model.exception.ResourceNotFoundException;
import com.desafio.controle.repository.CreditoRepository;
import com.desafio.controle.shared.CreditoDTO;

@Service
public class CreditoServices {
    
    @Autowired
    private CreditoRepository creditoRepository;

    /**
     * 
     * @return Retorna todos os creditos
     */
    public List<CreditoDTO> obterTodos() {

        List<Creditos> creditos = creditoRepository.findAll();
        
        return creditos.stream()
        .map(credito -> new ModelMapper().map(credito, CreditoDTO.class))
        .collect(Collectors.toList());

    }
    /**
     * 
     * @param id id do credito a ser requisitado
     * @return Retorna o credito requisitado pelo id
     * @throws - Caso não ache o id dispara uma mensagem
     */
    public Optional<CreditoDTO> obterPorId(Integer id) {
        // obtendo optinal de credito pelo ID
        Optional<Creditos> credito = creditoRepository.findById(id);
        //  se nao achar avisa
        if(credito.isEmpty()){
            throw new ResourceNotFoundException("Credito com o id "+ id + " não encontrado!");
        }
        //  Convertendo meu opitinal para um DTO
        CreditoDTO dto = new ModelMapper().map(credito.get(), CreditoDTO.class);

        // Criando e retornando um optinal de creditodto
        return Optional.of(dto);
    }

    /**
     * 
     * @param creditodto Adiciona um credito de acordo com o modelo base utilizando um DTO
     * @return Retorna o credito adicionado
     */
    public CreditoDTO adicionar(CreditoDTO creditodto) {
        // Removendo Id para conseguir fazer o cadastro
        creditodto.setId(null);
        
        // Criar um mapeamento
        ModelMapper mapper = new ModelMapper();

        // Converter o creditoDTO em um produto 
        Creditos credito = mapper.map(creditodto, Creditos.class);

        // Salvar o Credito no banco
        credito = creditoRepository.save(credito);

        creditodto.setId(credito.getId());

        // retornar o creditoDTO atualizado

        return creditodto;
    }
    /**
     * 
     * @param id Deleta o credito com o id mencionado no path
     */
    public void deletar(Integer id) {
        // Verificar se o produto existe
        Optional <Creditos> credito = creditoRepository.findById(id);
        //  Se nao existir lança um exception
        if(credito.isEmpty()){
            throw new ResourceNotFoundException("Não foi possivel deletar o crédito com id: "+ id + " - Produto não existe!");
        }
        creditoRepository.deleteById(id);
    }

    /**
     * 
     * @param id Id do credito a ser atualizado
     * @param creditodto Atualiza um credito de acordo com o modelo base utilizando um DTO
     * @return Retorna o credito atualizado 
     */
    public CreditoDTO atualizar(Integer id, CreditoDTO creditodto) {
     
        // Passar o id para o credito dto
        creditodto.setId(id);

        // Criar um objeto de mapeamento
        ModelMapper mapper = new ModelMapper();

        //  Converter o creditodto em Credito
        Creditos credito = mapper.map(creditodto, Creditos.class);

        // Atualizar o credito no banco de dados.
        creditoRepository.save(credito); 

        // Retornar o credito atualizado
        return creditodto;

    }

    /**
     * 
     * @return A soma dos valores recebidos
     */
    public  String calcularSomaValoresRecebidos() {
        List<Creditos> creditos = creditoRepository.findAll();
        BigDecimal soma = BigDecimal.ZERO;
        for (Creditos credito : creditos) {
            soma = soma.add(BigDecimal.valueOf(credito.getValorRecebido()));
        }
        return "O valor total a receber é "+ soma + " reais.";
    }

    
}
