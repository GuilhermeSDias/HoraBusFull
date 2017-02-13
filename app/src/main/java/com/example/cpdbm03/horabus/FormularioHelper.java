package com.example.cpdbm03.horabus;

import android.widget.EditText;
import android.widget.Spinner;

import com.example.cpdbm03.horabus.modelo.Viagem;



public class FormularioHelper {
    private final EditText campoOrigem;
    private final EditText campoDestino;
    private final EditText campoSaida;
    private final EditText campoTarifa;
    private final Spinner campoIdEmpresa;


    private Viagem viagem;

    public FormularioHelper(FormularioActivity activity) {
        campoOrigem = (EditText) activity.findViewById(R.id.formulario_origem);
        campoDestino = (EditText) activity.findViewById(R.id.formulario_destino);
        campoSaida = (EditText) activity.findViewById(R.id.formulario_saida);
        campoTarifa = (EditText) activity.findViewById(R.id.formulario_tarifa);
        campoIdEmpresa = (Spinner) activity.findViewById(R.id.spinner);
        viagem = new Viagem();
    }


    public Viagem pegaViagem() {
        viagem.setOrigem(campoOrigem.getText().toString());
        viagem.setDestino(campoDestino.getText().toString());
        viagem.setSaida(campoSaida.getText().toString());
        viagem.setTarifa(campoTarifa.getText().toString());
        viagem.setId_empresa(campoIdEmpresa.getSelectedItemId()+1);

        return viagem;
    }

    public void preencheFormulario(Viagem viagem) {
        campoOrigem.setText(viagem.getOrigem());
        campoDestino.setText(viagem.getDestino());
        campoSaida.setText(viagem.getSaida());
        campoTarifa.setText(viagem.getTarifa());
       // campoIdEmpresa.setAdapter(viagem.getId_empresa());

        this.viagem = viagem;
    }



}
