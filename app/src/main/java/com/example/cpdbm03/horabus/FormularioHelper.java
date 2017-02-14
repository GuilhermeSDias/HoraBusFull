package com.example.cpdbm03.horabus;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.cpdbm03.horabus.modelo.Viagem;



public class FormularioHelper {
    private final EditText campoOrigem;
    private final EditText campoDestino;
    private final EditText campoSaida;
    private final EditText campoTarifa;
    private final Spinner campoIdEmpresa;
    private final ImageView campoFoto;


    private Viagem viagem;

    public FormularioHelper(FormularioActivity activity) {
        campoOrigem = (EditText) activity.findViewById(R.id.formulario_origem);
        campoDestino = (EditText) activity.findViewById(R.id.formulario_destino);
        campoSaida = (EditText) activity.findViewById(R.id.formulario_saida);
        campoTarifa = (EditText) activity.findViewById(R.id.formulario_tarifa);
        campoIdEmpresa = (Spinner) activity.findViewById(R.id.spinner);
        campoFoto = (ImageView) activity.findViewById(R.id.formulario_foto);
        viagem = new Viagem();
    }


    public Viagem pegaViagem() {
        viagem.setOrigem(campoOrigem.getText().toString());
        viagem.setDestino(campoDestino.getText().toString());
        viagem.setSaida(campoSaida.getText().toString());
        viagem.setTarifa(campoTarifa.getText().toString());
        viagem.setId_empresa(campoIdEmpresa.getSelectedItemId()+1);
        viagem.setCaminhoFoto((String) campoFoto.getTag());

        return viagem;
    }

    public void preencheFormulario(Viagem viagem) {
        campoOrigem.setText(viagem.getOrigem());
        campoDestino.setText(viagem.getDestino());
        campoSaida.setText(viagem.getSaida());
        campoTarifa.setText(viagem.getTarifa());
        carregaImagem(viagem.getCaminhoFoto());
       // campoIdEmpresa.setAdapter(viagem.getId_empresa());

        this.viagem = viagem;
    }

    public void carregaImagem(String caminhoFoto) {
        if (caminhoFoto != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            campoFoto.setImageBitmap(bitmapReduzido);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
            campoFoto.setTag(caminhoFoto);
            carregaImagem(viagem.getCaminhoFoto());
        }
    }

}
