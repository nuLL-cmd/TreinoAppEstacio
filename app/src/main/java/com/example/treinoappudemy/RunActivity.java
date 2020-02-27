package com.example.treinoappudemy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class RunActivity extends AppCompatActivity {
    private int selectTreino;
    int exercAtual;
    private String[] lista_exec_rum;
    private TextView txt_atividade_run;
    private TextView txt_next_atividade_run;
    private TextView txt_time;
    private TextView txt_iniciar_run;
    private TextView txt_lbl_next;
    private ProgressBar progressBar;
    private ImageButton fb_iniciar_run;
    private int lista_tempos[];
    private int tempoDescanso = 10;
    private boolean estado = false;
    private ImageView img_fim;
    private CountDownTimer countDownTimer;
    private int tempoExercicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);

        txt_time = findViewById(R.id.txt_time);
        txt_next_atividade_run = findViewById(R.id.txt_next_atividade_run);
        txt_atividade_run = findViewById(R.id.txt_atividade_run);
        fb_iniciar_run = findViewById(R.id.fb_iniciar_run);
        txt_iniciar_run = findViewById(R.id.txt_iniciar_run);
        txt_lbl_next = findViewById(R.id.txt_lbl_next);
        img_fim = findViewById(R.id.img_fim);

        getData();
        selectLista(selectTreino);

        lista_tempos = new int[]{50, 60, 40, 40, 50};
        exercAtual = 0;
        tempoExercicio = lista_tempos[exercAtual];
        txt_time.setText(String.valueOf(tempoExercicio));

        setTextContinuos();
    }

    public void getData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            selectTreino = bundle.getInt("position");
        }
    }

    public void selectLista(int position) {
        switch (position) {
            case 0:
                lista_exec_rum = getResources().getStringArray(R.array.lista1);
                break;
            case 1:
                lista_exec_rum = getResources().getStringArray(R.array.lista2);
                break;
            default:
                lista_exec_rum = getResources().getStringArray(R.array.listaDefault);
                break;
        }
    }

    public void play() {
        verifyColor();
        countDownTimer = new CountDownTimer(tempoExercicio * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                attTimer((int) (millisUntilFinished) / 1000);
                timeOut((int) millisUntilFinished);
            }

            @Override
            public void onFinish() {
                if (exercAtual < lista_exec_rum.length-1){
                    fb_iniciar_run.setEnabled(false);
                    exercAtual++;
                     tempoExercicio = lista_tempos[exercAtual];
                    setTextRest();
                    rest();
                }else
                    theEnd();
     
            }

        }.start();
    }

    private void theEnd() {
        txt_iniciar_run.setText("Exercicios finalizados");

        txt_time.animate().alpha(0f).setDuration(1000);
        txt_atividade_run.animate().alpha(0f).setDuration(1000);
        txt_next_atividade_run.animate().alpha(0f).setDuration(1000);
        txt_lbl_next.animate().alpha(0f).setDuration(1000);
        fb_iniciar_run.animate().alpha(0f).setDuration(1000);

        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setIcon(R.drawable.gandalf);
        alerta.setTitle("Fim..");
        alerta.setMessage("Exercicios finalizados!! \nObrigado!!");
        alerta.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                NavUtils.navigateUpFromSameTask(RunActivity.this);
            }
        });

        alerta.setCancelable(false);
        alerta.show();


    }

    public void pause() {
        countDownTimer.cancel();
    }

    public void onClick(View view) {
        if (estado == false) {
            fb_iniciar_run.setImageResource(R.drawable.ic_stop_black_24dp);
            estado = true;
            play();
        } else {
            fb_iniciar_run.setImageResource(R.drawable.ic_play);
            estado = false;
            pause();
        }
    }

    public void attTimer(int second) {
        txt_time.setText(String.valueOf(second+1));
    }

    public void setTextContinuos() {
        txt_atividade_run.setText(lista_exec_rum[exercAtual]);
        txt_iniciar_run.setText("Exercicio " + (exercAtual + 1) + " de " + (lista_exec_rum.length));
        if (exercAtual < lista_exec_rum.length-1) {
            txt_next_atividade_run.setText(lista_exec_rum[exercAtual + 1]);
        }else{
            txt_next_atividade_run.setText("..");
        }
    }

    public void rest() {
        verifyColor();
        countDownTimer = new CountDownTimer(tempoDescanso * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                attTimer((int) millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                setTextContinuos();
                fb_iniciar_run.setEnabled(true);
                play();

            }
        }.start();
    }

    public void setTextRest() {
        txt_atividade_run.setText("Descansar por:");
    }

    public void timeOut(int millisUntilFinished) {
        if (millisUntilFinished < 10000) {
            if (txt_time.getCurrentTextColor() == getResources().getColor(R.color.colorTimeIn)) {
                txt_time.setTextColor(getResources().getColor(R.color.colorTimeOut));
            } else
                txt_time.setTextColor(getResources().getColor(R.color.colorTimeIn));
        }

    }

    public void verifyColor() {
        if (txt_time.getCurrentTextColor() == getResources().getColor(R.color.colorTimeOut)) {
            txt_time.setTextColor(getResources().getColor(R.color.colorTimeIn));
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer = null;
    }
}
