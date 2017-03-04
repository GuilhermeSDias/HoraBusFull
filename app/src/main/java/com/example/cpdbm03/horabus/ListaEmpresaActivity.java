package com.example.cpdbm03.horabus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.cpdbm03.horabus.dao.EmpresaDAO;
import com.example.cpdbm03.horabus.modelo.Empresa;

import java.util.List;

public class ListaEmpresaActivity extends AppCompatActivity {

    private ListView listaEmpresas;
    public static long idemp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_empresa);



        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.logo);

        listaEmpresas = (ListView) findViewById(R.id.lista_empresas);
        listaEmpresas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Empresa empresa = (Empresa) listaEmpresas.getItemAtPosition(position);

                Long idemp = empresa.getId_empresa();
                idemp1 = idemp;

                //Toast.makeText(ListaEmpresaActivity.this,idemp.toString(),Toast.LENGTH_SHORT).show();

                Intent intentVaiPtoFormularioEmpresa = new Intent(ListaEmpresaActivity.this, ListaViagensActivity.class);
                intentVaiPtoFormularioEmpresa.putExtra("empresa", empresa);
                startActivity(intentVaiPtoFormularioEmpresa);
            }
        });

        Button  pesquisa = (Button) findViewById(R.id.pesquisa);
        pesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListaEmpresaActivity.this, PesquisaActivity.class));
            }
        });

        Button novaEmpresa = (Button) findViewById(R.id.nova_empresa);
        novaEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vaiProFormularioEmpresa = new Intent(ListaEmpresaActivity.this, FormularioEmpresa.class);
                startActivity(vaiProFormularioEmpresa);
            }
        });

        registerForContextMenu(listaEmpresas);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_empresa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.pesquisa) {

            Intent pesquisa = new Intent(ListaEmpresaActivity.this, PesquisaActivity.class);

            startActivity(pesquisa);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaLista() {
        EmpresaDAO dao = new EmpresaDAO(this);
        List<Empresa> empresas = dao.buscaEmpresas();
        dao.close();

        ArrayAdapter<Empresa> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, empresas);
        listaEmpresas.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Empresa empresa = (Empresa) listaEmpresas.getItemAtPosition(info.position);

                EmpresaDAO dao = new EmpresaDAO(ListaEmpresaActivity.this);
                dao.deletaEmpresa(empresa);
                dao.close();

                carregaLista();
                return false;
            }
        });
    }


}
