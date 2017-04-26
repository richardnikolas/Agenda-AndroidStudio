package br.com.alura.agenda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import br.com.alura.agenda.modelo.Aluno;

public class FormularioHelper {
    private final EditText campoNome;
    private final EditText campoPais;
    private final EditText campoMatricula;
    private final EditText campoTelefone;
    private final EditText campoSite;
    private final RatingBar campoNota;
    private final ImageView campoFoto;


    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity) {
        campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        campoPais = (EditText) activity.findViewById(R.id.formulario_pais);
        campoMatricula = (EditText) activity.findViewById(R.id.formulario_matricula);
        campoTelefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        campoSite = (EditText) activity.findViewById(R.id.formulario_site);
        campoNota = (RatingBar) activity.findViewById(R.id.formulario_nota);
        campoFoto = (ImageView) activity.findViewById(R.id.formulario_foto);
        aluno = new Aluno();
    }

    public Aluno pegaAluno() {
        aluno.setNome(campoNome.getText().toString());
        aluno.setPais(campoPais.getText().toString());
        aluno.setCaracteristicas(campoMatricula.getText().toString());
        aluno.setSkills(campoTelefone.getText().toString());
        aluno.setSite(campoSite.getText().toString());
        aluno.setPower((double) campoNota.getProgress());
        aluno.setCaminhoFoto((String) campoFoto.getTag());
        return aluno;
    }

    public void preencheFormulario(Aluno aluno) {
        campoNome.setText(aluno.getNome());
        campoPais.setText(aluno.getPais());
        campoMatricula.setText(aluno.getCaracteristicas());
        campoTelefone.setText(aluno.getSkills());
        campoSite.setText(aluno.getSite());
        campoNota.setProgress((int) aluno.getPower());
        carregaImagem(aluno.getCaminhoFoto());
        this.aluno = aluno;
    }

    public void carregaImagem(String caminhoFoto) {
        if(caminhoFoto != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzida = Bitmap.createScaledBitmap(bitmap, 400, 400, true);
            campoFoto.setImageBitmap(bitmapReduzida);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
            campoFoto.setTag(caminhoFoto);
        }
    }
}
