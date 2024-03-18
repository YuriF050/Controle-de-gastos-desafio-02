package com.desafio.controle.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Creditos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String descricao;
    private String dataReferencia;
    private String dataRecebimento;
    private double valorCredito;
    private double valorRecebido; 
    private double valorDiferenca;
    private String tipoRecebimento;

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
public String getDataRecebimento() {
    return dataRecebimento;
}
public void setDataRecebimento(String dataRecebimento) {
    this.dataRecebimento = dataRecebimento;
}
public double getValorCredito() {
    return valorCredito;
}
public void setValorCredito(double valorCredito) {
    this.valorCredito = valorCredito;
}
public double getValorRecebido() {
    return valorRecebido;
}
public void setValorRecebido(double valorRecebido) {
    this.valorRecebido = valorRecebido;
}
public double getValorDiferenca() {
    return valorDiferenca;
}
public void setValorDiferenca(double valorDiferenca) {
    this.valorDiferenca = valorDiferenca;
}
public String getTipoRecebimento() {
    return tipoRecebimento;
}
public void setTipoRecebimento(String tipoRecebimento) {
    this.tipoRecebimento = tipoRecebimento;
}
}
