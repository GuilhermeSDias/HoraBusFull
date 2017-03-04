package com.example.cpdbm03.horabus.modelo;

import java.io.Serializable;

/**
 * Created by CPDBM03 on 04/02/2017.
 */

public class Empresa implements Serializable {


    private Long id_empresa;
    private String nome;


    public Long getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(Long id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return  getNome();
    }


}
