package com.example.cpdbm03.horabus.modelo;

import java.io.Serializable;

/**
 * Created by CPDBM03 on 03/02/2017.
 */

public class Viagem implements Serializable {

    private Long id;
    private String origem;
    private String destino;
    private String saida;
    private String tarifa;
    private Long id_empresa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getSaida() {
        return saida;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }

    public String getTarifa() {
        return tarifa;
    }

    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }

    public Long getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(Long id_empresa) {
        this.id_empresa = id_empresa;
    }

    @Override
    public String toString() {
        return  getOrigem() + "->" + getDestino() + "\n"
                + getSaida() + "\n"
                + getTarifa() + "\n"
                + getId_empresa();
    }


}
