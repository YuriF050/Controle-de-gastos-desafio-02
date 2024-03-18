package com.desafio.controle.view.model;

public class CreditoRequest {
    
    private String descricao;
    private String dataReferencia;
    private String dataRecebimento;
    private double valorCredito;
    private double valorRecebido; 
    private double valorDiferenca;
    private String tipoRecebimento;

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
