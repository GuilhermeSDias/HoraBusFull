package com.example.cpdbm03.horabus.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ALVARO on 05/02/2017.
 */

public class BD extends SQLiteOpenHelper {

    public BD(Context context) {

        super(context, "banco", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Empresas(" +
                "id_empresa INTEGER PRIMARY KEY," +
                "nome TEXT NOT NULL," +
                "caminhoFoto TEXT);";
        db.execSQL(sql);

        String sql2 = "CREATE TABLE Viagens(" +
                "id INTEGER PRIMARY KEY," +
                "origem TEXT," +
                "destino TEXT," +
                "saida TEXT," +
                "tarifa TEXT," +
                "id_empresa INTEGER NOT NULL," +
                "FOREIGN KEY(id_empresa) REFERENCES Empresas(id_empresa));";
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS Empresas";
        db.execSQL(sql);
        String sql2 = "DROP TABLE IF EXISTS Viagens";
        db.execSQL(sql2);
        onCreate(db);
    }
}
