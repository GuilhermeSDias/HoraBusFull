package com.example.cpdbm03.horabus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.cpdbm03.horabus.adapter.AdapterLista;
import com.example.cpdbm03.horabus.dao.BD;
import com.example.cpdbm03.horabus.modelo.Viagem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guilherme Dias on 04/03/2017.
 */

public class PesquisaActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.pesquisa);
//
//        toolbar.setLogo(R.drawable.ic_bus);
//        toolbar.setTitle("Consultar Rotas");


        final String[] ORIGEM = new String[1];
        final String[] DESTINO = new String[1];

        final BD bd = new BD(this);

        Spinner origem = (Spinner) findViewById(R.id.origem);
        final Spinner destino = (Spinner) findViewById(R.id.destino);
        final Spinner empresa = (Spinner) findViewById(R.id.empresa);

        final ListView ListConsulta = (ListView) findViewById(R.id.consulta_list);

        List<String> ListDados = new ArrayList<>();

        ListDados = BD.SpinnerBuscaOrigem();
        ListDados.add(0, "Selecione a Origem...");


        ArrayAdapter<String> adaptador;
        adaptador = new ArrayAdapter<String>(PesquisaActivity.this, android.R.layout.simple_list_item_1, ListDados);
        origem.setAdapter(adaptador);

        origem.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        Object item = parent.getItemAtPosition(pos);
                        ORIGEM[0] = item.toString();
                        if(!(ORIGEM[0].equals("Selecione a Origem..."))){
                            List<String> ListDados = new ArrayList<>();
                            ListDados = BD.SpinnerBuscaDestino(ORIGEM[0]);
                            ListDados.add(0, "Selecione o Destino...");
                            ArrayAdapter<String> adaptador;
                            adaptador = new ArrayAdapter<String>(PesquisaActivity.this, android.R.layout.simple_list_item_1, ListDados);
                            destino.setAdapter(adaptador);
                        }
                    }
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

        destino.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        Object item = parent.getItemAtPosition(pos);
                        DESTINO[0] = item.toString();
                        if(!(DESTINO[0].equals("Selecione o Destino..."))){
                            List<String> ListDados = new ArrayList<>();

                            ListDados = BD.SpinnerBuscaEmpresa(ORIGEM[0],DESTINO[0]);
                            ListDados.add(0, "Selecione a Empresa...");

                            ArrayAdapter<String> adaptador;
                            adaptador = new ArrayAdapter<String>(PesquisaActivity.this, android.R.layout.simple_list_item_1, ListDados);


                            empresa.setAdapter(adaptador);

                            ArrayList<Viagem> ListaDados = new ArrayList<Viagem>();
                            ListaDados = BD.BuscarFiltrado(ORIGEM[0],DESTINO[0]);

                            AdapterLista adapterLista = new AdapterLista(PesquisaActivity.this, ListaDados,true);
                            //  adaptador = new ArrayAdapter<String>(Consulta.this, android.R.layout.simple_list_item_1, ListDados);


                            ListConsulta.setAdapter(adapterLista);
                        }
                    }
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

        empresa.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        Object item = parent.getItemAtPosition(pos);
                        String EMPRESA = item.toString();
                        if(!(EMPRESA.equals("Selecione a Empresa..."))){

                            ArrayList<Viagem> ListaViagens = new ArrayList<Viagem>();

                            ListaViagens = BD.BuscarFiltradoEmpresa(ORIGEM[0],DESTINO[0],EMPRESA);

                            AdapterLista adapterLista = new AdapterLista(PesquisaActivity.this, ListaViagens, false);

                            ListConsulta.setAdapter(adapterLista);
                        }
                    }
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
    }

}
