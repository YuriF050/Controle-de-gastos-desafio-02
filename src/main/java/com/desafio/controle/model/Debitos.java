package com.desafio.controle.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import jakarta.persistence.GenerationType;

@Entity
public class Debitos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String descricao;
    private String dataReferencia;
    private String dataPagamento;
    private Double valorDebito;
    private Double valorPago;
    private Double valorDiferenca;
    private String tipoDespesa;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getDataReferencia() {
        return dataReferencia;
    }
    public void setDataReferencia(String dataReferencia) {
        this.dataReferencia = dataReferencia;
    }
    public String getDataPagamento() {
        return dataPagamento;
    }
    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
    public Double getValorDebito() {
        return valorDebito;
    }
    public void setValorDebito(Double valorDebito) {
        this.valorDebito = valorDebito;
    }
    public Double getValorPago() {
        return valorPago;
    }
    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }
    public Double getValorDiferenca() {
        return valorDiferenca;
    }
    public void setValorDiferenca(Double valorDiferenca) {
        this.valorDiferenca = valorDiferenca;
    }
    public String getTipoDespesa() {
        return tipoDespesa;
    }
    public void setTipoDespesa(String tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }
    
    
}
