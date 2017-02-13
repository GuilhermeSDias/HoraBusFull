package com.example.cpdbm03.horabus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.cpdbm03.horabus.dao.EmpresaDAO;
import com.example.cpdbm03.horabus.modelo.Empresa;


public class FormularioEmpresa extends AppCompatActivity {

    private FormularioEmpresaHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_empresa);

        helper = new FormularioEmpresaHelper(this, campoFoto);

        Intent intent = getIntent();
        Empresa empresa = (Empresa) intent.getSerializableExtra("empresa");
        if(empresa != null) {
            helper.preencheFormularioEmpresa(empresa);
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
                Empresa empresa = helper.pegaEmpresa();

                EmpresaDAO dao = new EmpresaDAO(this);
                if(empresa.getId_empresa() != null) {
                    dao.alteraEmpresa(empresa);
                } else {
                    dao.insereEmpresa(empresa);
                }
                dao.close();

                Toast.makeText(FormularioEmpresa.this, "Empresa salva!",
                        Toast.LENGTH_SHORT).show();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
