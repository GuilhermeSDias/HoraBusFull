package com.example.cpdbm03.horabus.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.cpdbm03.horabus.FormularioActivity;
import com.example.cpdbm03.horabus.modelo.Viagem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CPDBM03 on 03/02/2017.
 */

public class ViagemDAO extends BD{


    public ViagemDAO(Context context) {
        super(context);
    }

    public ContentValues insere(Viagem viagem) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDaViagem(viagem);

        db.insert("Viagens", null, dados);

        return dados;
    }

    public List<Viagem> buscaViagens() {
        String sql = "SELECT * FROM Viagens";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Viagem> viagens = new ArrayList<>();

        while(c.moveToNext()) {
            Viagem viagem = new Viagem();
            viagem.setId(c.getLong(c.getColumnIndex("id")));
            viagem.setOrigem(c.getString(c.getColumnIndex("origem")));
            viagem.setDestino(c.getString(c.getColumnIndex("destino")));
            viagem.setSaida(c.getString(c.getColumnIndex("saida")));
            viagem.setTarifa(c.getString(c.getColumnIndex("tarifa")));
            viagem.setId_empresa(c.getLong(c.getColumnIndex("id_empresa")));
            viagem.setCaminhoFoto(c.getString(c.getColumnIndex("caminhoFoto")));

            viagens.add(viagem);
        }
        c.close();

        return viagens;
    }

    public void altera(Viagem viagem) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDaViagem(viagem);

        String[] params = {viagem.getId().toString()};
        db.update("Viagens", dados, "id = ?", params);
    }

    public void deleta(Viagem viagem) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {viagem.getId().toString()};
        db.delete("Viagens", "id = ?", params);
    }

    @NonNull
    private ContentValues pegaDadosDaViagem(Viagem viagem) {
        ContentValues dados = new ContentValues();
        dados.put("origem", viagem.getOrigem());
        dados.put("destino", viagem.getDestino());
        dados.put("saida", viagem.getSaida());
        dados.put("tarifa", viagem.getTarifa());
        dados.put("id_empresa", viagem.getId_empresa());
        dados.put("caminhoFoto", viagem.getCaminhoFoto());



        return dados;
    }

    public List<Viagem> filtroViagens(int v) {


        String sql = "SELECT * FROM Viagens where id_empresa = "+ v +";";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Viagem> viagens = new ArrayList<>();

        while(c.moveToNext()) {
            Viagem viagem = new Viagem();
            viagem.setId(c.getLong(c.getColumnIndex("id")));
            viagem.setOrigem(c.getString(c.getColumnIndex("origem")));
            viagem.setDestino(c.getString(c.getColumnIndex("destino")));
            viagem.setSaida(c.getString(c.getColumnIndex("saida")));
            viagem.setTarifa(c.getString(c.getColumnIndex("tarifa")));
            viagem.setId_empresa(c.getLong(c.getColumnIndex("id_empresa")));

            viagens.add(viagem);


        }
        c.close();


        return viagens;



    }




}
