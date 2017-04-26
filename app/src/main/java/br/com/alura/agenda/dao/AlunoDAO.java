package br.com.alura.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.modelo.Aluno;

public class AlunoDAO extends SQLiteOpenHelper {

    public AlunoDAO(Context context) {
        super(context, "Characters", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Characters (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, caracteristicas TEXT, site TEXT, power REAL, skills TEXT, caminhoFoto TEXT, pais TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "";
        switch (oldVersion) {
            case 2:
                sql = "ALTER TABLE Characters ADD COLUMN pais TEXT";
                db.execSQL(sql);
        }
    }

    public void insere(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = pegaDadosDoAluno(aluno);
        db.insert("Characters", null, dados);
    }

    @NonNull
    private ContentValues pegaDadosDoAluno(Aluno aluno) {
        ContentValues dados = new ContentValues();
        dados.put("nome", aluno.getNome());
        dados.put("pais", aluno.getPais());
        dados.put("caracteristicas", aluno.getCaracteristicas());
        dados.put("skills", aluno.getSkills());
        dados.put("site", aluno.getSite());
        dados.put("power", aluno.getPower());
        dados.put("caminhoFoto", aluno.getCaminhoFoto());
        return dados;
    }

    public List<Aluno> buscaCharacters() {
        String sql = "SELECT * from Characters;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Aluno> Characters = new ArrayList<>();
        while(c.moveToNext()){
            Aluno aluno = new Aluno();
            aluno.setId(c.getLong(c.getColumnIndex("id")));
            aluno.setNome(c.getString(c.getColumnIndex("nome")));
            aluno.setPais(c.getString(c.getColumnIndex("pais")));
            aluno.setCaracteristicas(c.getString(c.getColumnIndex("caracteristicas")));
            aluno.setSkills(c.getString(c.getColumnIndex("skills")));
            aluno.setSite(c.getString(c.getColumnIndex("site")));
            aluno.setPower(c.getDouble(c.getColumnIndex("power")));
            aluno.setCaminhoFoto(c.getString(c.getColumnIndex("caminhoFoto")));
            Characters.add(aluno);
        }
        c.close();
        return Characters;
    }

    public void deleta(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {aluno.getId().toString()};
        db.delete("Characters", "id = ?", params);
    }

    public void altera(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = pegaDadosDoAluno(aluno);
        String[] params = {aluno.getId().toString()};
        db.update("Characters", dados, "id = ?", params);
    }
}