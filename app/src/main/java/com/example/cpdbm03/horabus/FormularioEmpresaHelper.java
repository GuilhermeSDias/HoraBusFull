package com.example.cpdbm03.horabus;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.cpdbm03.horabus.modelo.Empresa;


/**
 * Created by ALVARO on 04/02/2017.
 */

public class FormularioEmpresaHelper {

    private final EditText campoNome;
    private Empresa empresa;
    private final ImageView campoFoto;

    public FormularioEmpresaHelper(FormularioEmpresa activity) {
        campoNome = (EditText) activity.findViewById(R.id.formulario_nomeEmpresa);
        campoFoto = (ImageView) activity.findViewById(R.id.formulario_foto);
        empresa = new Empresa();
    }


    public Empresa pegaEmpresa() {
        empresa.setNome(campoNome.getText().toString());
        empresa.setCaminhoFoto((String) campoFoto.getTag());
        return empresa;
    }

    public void preencheFormularioEmpresa(Empresa empresa) {
        campoNome.setText(empresa.getNome());
        carregaImagem(empresa.getCaminhoFoto());
        this.empresa = empresa;
    }

    public void carregaImagem(String caminhoFoto) {
        if (caminhoFoto != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            campoFoto.setImageBitmap(bitmapReduzido);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
            carregaImagem(empresa.getCaminhoFoto());
        }
    }
}
