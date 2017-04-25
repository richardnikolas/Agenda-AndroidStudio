package br.com.alura.agenda;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;

import java.util.List;

import br.com.alura.agenda.converter.AlunoConverter;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.modelo.Aluno;

public class EnviaAlunosTask extends AsyncTask<Void, Void, String> {
    private Context context;
    private ProgressDialog dialog;

    public EnviaAlunosTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(context, "Aguarde", "Calculando poder...", true, true);
    }

    @Override
    protected String doInBackground(Void... params) {
        AlunoDAO dao = new AlunoDAO(context);
        List<Aluno> characters = dao.buscaCharacters();
        dao.close();

        AlunoConverter conversor = new AlunoConverter();
        String json = null;
        try {
            json = conversor.converterParaJSON(characters);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        WebClient client = new WebClient();
        String resposta = client.post(json);
        return resposta;
    }

    @Override
    protected void onPostExecute(String resposta) {
        dialog.dismiss();
        Toast.makeText(context, resposta, Toast.LENGTH_LONG).show();
    }
}
