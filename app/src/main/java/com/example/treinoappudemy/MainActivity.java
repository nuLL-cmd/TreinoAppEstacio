package com.example.treinoappudemy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String[] lista_treino;
    private String[] lista_letra;
    private int[] lista_image = {
              R.drawable.ic_accessibility_black_24dp
            , R.drawable.ic_accessibility_black_24dp
            , R.drawable.ic_accessibility_black_24dp
            , R.drawable.ic_accessibility_black_24dp
            , R.drawable.ic_accessibility_black_24dp
            , R.drawable.ic_accessibility_black_24dp
            , R.drawable.ic_accessibility_black_24dp
            , R.drawable.ic_accessibility_black_24dp
            , R.drawable.ic_accessibility_black_24dp
            , R.drawable.ic_accessibility_black_24dp
    };

    private DataProvider dataProvider;
    private AdapterCustom adapterCustom;
    private ImageView img_treino;
    private ListView lista_main;
    private TextView txt_escolha;
    List<DataProvider> dataProviders;
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista_main = findViewById(R.id.lista_main);
        img_treino = findViewById(R.id.img_treino);
        txt_escolha = findViewById(R.id.txt_escolha);
        lista_letra = getResources().getStringArray(R.array.lista_letra);
        lista_treino = getResources().getStringArray(R.array.lista_treino);

        loadLista();
        selectTreino();
        abertura();
        //ttsobject();
        img_treino.animate().alpha(1f).setDuration(2000);
        txt_escolha.animate().alpha(1f).setDuration(2000);
    }

    public void loadLista() {
        dataProviders = new ArrayList<>();
        adapterCustom = new AdapterCustom(MainActivity.this, R.layout.layout_celula);
        for (int i = 0; i < lista_treino.length; i++) {
            dataProvider = new DataProvider(lista_letra[i], lista_treino[i], lista_image[i]);
            dataProviders.add(dataProvider);
            adapterCustom.add(dataProvider);
        }

        lista_main.setAdapter(adapterCustom);
    }

    public void selectTreino() {
        lista_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nomeTreino = dataProviders.get(position).getTreino();
                telTreinoe(position, nomeTreino);
            }
        });
    }

    public void telTreinoe(int position, String nome) {
        Intent intent = new Intent(MainActivity.this, TreinoActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("name", nome);
        startActivity(intent);
    }

    public void abertura(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
        alerta.setTitle("Sobre o trabalho");
        alerta.setMessage("Treino App - Estacio \nTurma de ADS B3 \n2º periodo \n Profª Gandalf o Cinzento.");
        alerta.setPositiveButton("Fechar",null);
        alerta.setIcon(R.drawable.gandalf);
        alerta.show();
    }

    public void ttsobject(){
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS)
                    textToSpeech.speak("Carlotinha do sertão trindadense",TextToSpeech.QUEUE_FLUSH,null);
            }
        });
    }
    @Override
    protected void onResume(){
        super.onResume();
    }


}
