package com.example.cpdbm03.horabus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cpdbm03.horabus.dao.BD;
import com.example.cpdbm03.horabus.dao.EmpresaDAO;
import com.example.cpdbm03.horabus.dao.ViagemDAO;
import com.example.cpdbm03.horabus.modelo.Empresa;
import com.example.cpdbm03.horabus.modelo.Viagem;

import java.util.List;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);
        final EmpresaDAO emp = new EmpresaDAO(this);

        final List<Empresa> listemp = emp.buscaEmpresas();

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);


        ArrayAdapter<Empresa> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listemp);
        spinner.setSelection(0, Boolean.parseBoolean(""));
        spinner.setAdapter(adapter);




        Intent intent = getIntent();
        Viagem viagem = (Viagem) intent.getSerializableExtra("viagem");
        if(viagem != null) {
            helper.preencheFormulario(viagem);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:
                Viagem viagem = helper.pegaViagem();

                ViagemDAO dao = new ViagemDAO(this);
                if(viagem.getId() != null) {
                    dao.altera(viagem);
                } else {
                    dao.insere(viagem);
                }
                dao.close();

                Toast.makeText(FormularioActivity.this, "Viagem salva!",Toast.LENGTH_SHORT).show();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
