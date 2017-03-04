package com.example.cpdbm03.horabus.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.cpdbm03.horabus.modelo.Empresa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CPDBM03 on 04/02/2017.
 */

public class EmpresaDAO extends BD {

    public EmpresaDAO(Context context) {
        super(context);
    }

    public void insereEmpresa(Empresa empresa) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDaEmpresa(empresa);

        db.insert("Empresas", null, dados);
    }

    public List<Empresa> buscaEmpresas() {
        String sql = "SELECT * FROM Empresas";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Empresa> empresas = new ArrayList<>();

        while(c.moveToNext()) {
            Empresa empresa = new Empresa();
            empresa.setId_empresa(c.getLong(c.getColumnIndex("id_empresa")));
            empresa.setNome(c.getString(c.getColumnIndex("nome")));


            empresas.add(empresa);
        }
        c.close();

        return empresas;
    }

    public void alteraEmpresa(Empresa empresa) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDaEmpresa(empresa);

        String[] param = {empresa.getId_empresa().toString()};
        db.update("Empresas", dados, "id_empresa = ?", param);
    }

    public void deletaEmpresa(Empresa empresa) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {empresa.getId_empresa().toString()};
        db.delete("Empresas", "id_empresa = ?", params);
    }

    @NonNull
    private ContentValues pegaDadosDaEmpresa(Empresa empresa) {
        ContentValues dados = new ContentValues();
        dados.put("nome", empresa.getNome());


        return dados;
    }

}
