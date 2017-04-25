package br.com.alura.agenda.converter;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

import br.com.alura.agenda.modelo.Aluno;

public class AlunoConverter {

    public String converterParaJSON(List<Aluno> alunos) throws JSONException {
        JSONStringer js = new JSONStringer();

        try {
            js.object().key("list").array().object().key("character").array();
            for (Aluno character: alunos){
                js.object();
                js.key("nome").value(character.getNome());
                js.key("power").value(character.getPower());
                js.endObject();
            }
            js.endArray().endObject().endArray().endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return js.toString();
    }
}
