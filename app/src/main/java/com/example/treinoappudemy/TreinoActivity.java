package com.example.treinoappudemy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TreinoActivity extends AppCompatActivity {
    private Toolbar toolbar_treino;
    private String nomeTreino;
    private int positionTreino;
    private FloatingActionButton fb_iniciar;
    private String[] lista1;
    private String[] lista2;
    private String[] listaDefault;
    private ArrayAdapter<String> adapter;
    private ListView lista_treino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treino);

        toolbar_treino = findViewById(R.id.toolbar_treino);
        lista_treino = findViewById(R.id.lista_treino);

        getDadosIntent();
        fbAcao();
        tipoLista(positionTreino);

        setSupportActionBar(toolbar_treino);
        toolbar_treino.setTitle("Treino " + nomeTreino);


    }

    public void getDadosIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            positionTreino = bundle.getInt("position");
            nomeTreino = bundle.getString("name");
            Toast.makeText(this, "Bem vindo ao treino: " + nomeTreino, Toast.LENGTH_LONG).show();
        }
    }

    public void fbAcao() {
        fb_iniciar = findViewById(R.id.fb_iniciar);
        fb_iniciar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startTreino(positionTreino);
            }
        });
    }

    public void tipoLista(int positionTreino) {
        switch (positionTreino) {
            case 0:
                lista1 = getResources().getStringArray(R.array.lista1);
                populaLista(lista1);
                break;
            case 1:
                lista2 = getResources().getStringArray(R.array.lista2);
                populaLista(lista2);
                break;
            default:
                listaDefault = getResources().getStringArray(R.array.listaDefault);
                populaLista(listaDefault);
                break;

        }
    }

    public void populaLista(String[] lista) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        lista_treino.setAdapter(adapter);
        clickLista(lista);
    }

    public void clickLista(final String[] lista) {
        lista_treino.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                loadVideo(lista[position], position);

            }
        });
    }

    public void loadVideo(String video, int position) {
        Intent intent = new Intent(TreinoActivity.this, VideoActivity.class);
        intent.putExtra("video", video);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    public void startTreino(int position){
        Intent intent = new Intent(TreinoActivity.this, RunActivity.class);
        intent.putExtra("position",position);
        startActivity(intent);
    }


}
