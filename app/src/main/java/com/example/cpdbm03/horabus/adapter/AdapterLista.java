package com.example.cpdbm03.horabus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cpdbm03.horabus.ListaEmpresaActivity;
import com.example.cpdbm03.horabus.R;
import com.example.cpdbm03.horabus.modelo.Viagem;

import java.util.ArrayList;

/**
 * Created by Guilherme Dias on 28/02/2017.
 */

public class AdapterLista extends BaseAdapter {
    private Context context;
    private ArrayList<Viagem> viagens;
    private boolean tipo;

    public AdapterLista(Context contx, ArrayList<Viagem> viagens, boolean tipo){
        this.viagens = viagens;
        this.context = contx;
        this.tipo = tipo;
    }

public AdapterLista(ListView listaEmpresas, ListaEmpresaActivity listaEmpresaActivity) {
    }

    @Override
    public int getCount() {
        return viagens.size();
    }

    @Override
    public Object getItem(int i) {
        return viagens.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.activity_list_customer, viewGroup, false);
        if(view != null){
            TextView origem_destino = (TextView) view.findViewById(R.id.v_txt_origem_destino);
            TextView valor = (TextView) view.findViewById(R.id.v_txt_valor);
            TextView hora = (TextView) view.findViewById(R.id.v_txt_hora);
            TextView empresa = (TextView) view.findViewById(R.id.v_txt_empresa);

            Viagem dados = viagens.get(i);
            origem_destino.setText(dados.getOrigem() + " -> " + dados.getDestino());
            valor.setText(dados.getTarifa() != null ? "R$ "+String.format("%.2f", dados.getTarifa()) : null);
            hora.setText(dados.getSaida());
            empresa.setText(tipo ? dados.getId_empresa().toString() : null);
        }
        return view;
    }
}
