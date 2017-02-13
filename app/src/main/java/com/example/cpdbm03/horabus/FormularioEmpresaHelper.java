package com.example.cpdbm03.horabus;

import android.widget.EditText;

import com.example.cpdbm03.horabus.modelo.Empresa;


/**
 * Created by ALVARO on 04/02/2017.
 */

public class FormularioEmpresaHelper {

    private final EditText campoNome;
    private Empresa empresa;

    public FormularioEmpresaHelper(FormularioEmpresa activity) {
        campoNome = (EditText) activity.findViewById(R.id.formulario_nomeEmpresa);
        empresa = new Empresa();
    }


    public Empresa pegaEmpresa() {
        empresa.setNome(campoNome.getText().toString());
        return empresa;
    }

    public void preencheFormularioEmpresa(Empresa empresa) {
        campoNome.setText(empresa.getNome());
        this.empresa = empresa;
    }
}
