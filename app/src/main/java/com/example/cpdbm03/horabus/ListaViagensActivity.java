package com.example.cpdbm03.horabus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.cpdbm03.horabus.dao.ViagemDAO;
import com.example.cpdbm03.horabus.modelo.Viagem;

import java.util.List;

public class ListaViagensActivity extends AppCompatActivity {

    private ListView listaViagens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_viagens);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.logo);

        listaViagens = (ListView) findViewById(R.id.lista_viagens);
        listaViagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Viagem viagem = (Viagem) listaViagens.getItemAtPosition(position);

                Intent intentVaiPtoFormulario = new Intent(ListaViagensActivity.this, FormularioActivity.class);
                intentVaiPtoFormulario.putExtra("viagem", viagem);
                startActivity(intentVaiPtoFormulario);
            }
        });

        Button pesquisa = (Button) findViewById(R.id.pesquisa);
        pesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListaViagensActivity.this, PesquisaActivity.class));
            }
        });

        Button novaViagem = (Button) findViewById(R.id.nova_viagem);
        novaViagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vaiProFormulario = new Intent(ListaViagensActivity.this, FormularioActivity.class);
                startActivity(vaiProFormulario);
            }
        });

        registerForContextMenu(listaViagens);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    private void carregaLista() {
        ViagemDAO dao = new ViagemDAO(this);

        List<Viagem> viagens = dao.filtroViagens((int) ListaEmpresaActivity.idemp1);
        dao.close();

        ArrayAdapter<Viagem> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, viagens);
        listaViagens.setAdapter(adapter);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Viagem viagem = (Viagem) listaViagens.getItemAtPosition(info.position);

                ViagemDAO dao = new ViagemDAO(ListaViagensActivity.this);
                dao.deleta(viagem);
                dao.close();

                carregaLista();
                return false;
            }
        });
    }
}
