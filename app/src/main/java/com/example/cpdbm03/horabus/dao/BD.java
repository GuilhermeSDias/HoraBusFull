package com.example.cpdbm03.horabus.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cpdbm03.horabus.modelo.Viagem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ALVARO on 05/02/2017.
 */

public class BD extends SQLiteOpenHelper {
    public BD(Context context) {

        super(context, "banco", null, 1);
    }
     static SQLiteDatabase db;

    {
        db = getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Empresas(" +
                "id_empresa INTEGER PRIMARY KEY," +
                "nome TEXT NOT NULL);";
        db.execSQL(sql);

        String sql2 = "CREATE TABLE Viagens(" +
                "id INTEGER PRIMARY KEY," +
                "origem TEXT," +
                "destino TEXT," +
                "saida TEXT," +
                "tarifa TEXT," +
                "caminhoFoto TEXT,"+
                "id_empresa INTEGER NOT NULL," +
                "FOREIGN KEY(id_empresa) REFERENCES Empresas(id_empresa));";
        db.execSQL(sql2);
    }

    public static List<String> SpinnerBuscaOrigem(){

        String sql = "SELECT DISTINCT origem FROM Viagens;";
        Cursor c = db.rawQuery(sql, null);
        List<String> origens = new ArrayList<>();
        while (c.moveToNext()) {
            origens.add(c.getString(c.getColumnIndex("origem")));
        }
        c.close();
        return origens;
    }
    public static List<String> SpinnerBuscaDestino(String Origem){
//        db = getWritableDatabase();
        String sql = "SELECT DISTINCT destino FROM Viagens where origem = '"+Origem+"';";
        Cursor c = db.rawQuery(sql, null);
        List<String> origens = new ArrayList<>();
        while (c.moveToNext()) {
            origens.add(c.getString(c.getColumnIndex("destino")));
        }
        c.close();
        return origens;
    }

    public static ArrayList<Viagem> BuscarFiltrado(String Origem, String Destino){
//        db = getWritableDatabase();
        String sql = "SELECT * FROM Viagens where origem = '"+Origem+"' and destino ='"+Destino+"';";
        Cursor c = db.rawQuery(sql, null);

        ArrayList<Viagem> viagens = new ArrayList<>();
        while (c.moveToNext()) {
            Viagem viagem = new Viagem();

            viagem.setId(c.getLong(c.getColumnIndex("id")));
            viagem.setOrigem(c.getString(c.getColumnIndex("origem")));
            viagem.setDestino(c.getString(c.getColumnIndex("destino")));
            viagem.setSaida(c.getString(c.getColumnIndex("saida")));
            viagem.setTarifa(c.getDouble(c.getColumnIndex("tarifa")));
            viagem.setId_empresa(c.getLong(c.getColumnIndex("id_empresa")));
            viagens.add(viagem);
        }
        c.close();
        return viagens;
    }

    public static ArrayList<Viagem> BuscarFiltradoEmpresa(String Origem, String Destino, String Empresa){
//        db = getWritableDatabase();
        String sql = "SELECT * FROM Viagens where origem = '"+Origem+"' and destino ='"+Destino+"' and id_empresa = '"+Empresa+"';";
        Cursor c = db.rawQuery(sql, null);

        ArrayList<Viagem> viagens = new ArrayList<>();
        while (c.moveToNext()) {
            Viagem viagem = new Viagem();
            viagem.setId(c.getLong(c.getColumnIndex("id")));
            viagem.setOrigem(c.getString(c.getColumnIndex("origem")));
            viagem.setDestino(c.getString(c.getColumnIndex("destino")));
            viagem.setSaida(c.getString(c.getColumnIndex("saida")));
            viagem.setTarifa(c.getDouble(c.getColumnIndex("tarifa")));
            viagem.setId_empresa(c.getLong(c.getColumnIndex("id_empresa")));
            viagens.add(viagem);
        }
        c.close();
        return viagens;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS Empresas";
        db.execSQL(sql);
        String sql2 = "DROP TABLE IF EXISTS Viagens";
        db.execSQL(sql2);
        onCreate(db);
    }

    public static List<String> SpinnerBuscaEmpresa(String Origem, String Destino){
//        db = getWritableDatabase();
        String sql = "SELECT DISTINCT id_empresa FROM Viagens where origem = '"+Origem+"' and destino ='"+Destino+"';";
        Cursor c = db.rawQuery(sql, null);
        List<String> origens = new ArrayList<>();
        while (c.moveToNext()) {
            origens.add(c.getString(c.getColumnIndex("id_empresa")));
        }
        c.close();
        return origens;
    }

}
