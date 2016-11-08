package com.example.jp.simondice;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    Button iniciar;
    Handler retardo1=new Handler();
    Handler retardo2=new Handler();
    Button[]botones;
    MediaPlayer sonido[];
    int numeros[];
    int ordenJugador[];
    int botonesPulsados=0;
    private int tiempoEncendido=1000;
    private int tiempoApagado=1500;
    boolean enabledPlay = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ordenJugador=new int[4];
        numeros=new int[4];
        botones=new Button[4];
        iniciar=(Button)findViewById(R.id.bplay);
        botones[0] = (Button) findViewById(R.id.bazul);
        botones[1] = (Button) findViewById(R.id.brojo);
        botones[2] = (Button) findViewById(R.id.bverde);
        botones[3] = (Button) findViewById(R.id.bamarillo);
        sonido=new MediaPlayer[4];
        sonido[0]=MediaPlayer.create(this,R.raw.Moneda);
        sonido[1]=MediaPlayer.create(this,R.raw.saltolargo);
        sonido[2]=MediaPlayer.create(this,R.raw.Vaja);
        sonido[3]=MediaPlayer.create(this,R.raw.Ladrilloroto);

    }


    public void clickar(View v) {
        int indice;
        int id = v.getId();
        final Button b = (Button)v;

        if (id == R.id.bazul) {

            v.setBackgroundResource(R.color.azulfuerte);
            sonido[0].start();
            indice=0;

        } else if (id == R.id.brojo) {


            v.setBackgroundResource(R.color.rojofuerte);
            sonido[1].start();
            indice=1;

        } else if (id == R.id.bverde) {


            v.setBackgroundResource(R.color.verdefuerte);
            sonido[2].start();
            indice=2;

        } else {

            v.setBackgroundResource(R.color.amarillofuerte);
            sonido[3].start();
            indice=3;

        }
        Handler handler=new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                resetear(b.getId());
            }
        },500);

        if(enabledPlay){

            ordenJugador[botonesPulsados]=indice;
            botonesPulsados++;

            if(botonesPulsados==4){

                check();

            }


        }

    }


    public void iniciar(View v) {
        enabledPlay=true;
        for (int i = 0; i < numeros.length; i++) {

            numeros[i] = (int) (Math.random() * 4);
           final Button b=botones[numeros[i]];
            System.out.println(numeros[i]);

            if(b.getId()==R.id.bazul){
                retardo1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        b.setBackgroundResource(R.color.azulfuerte);
                        sonido[0].start();
                    }
                },tiempoEncendido);



            }else if(b.getId()==R.id.brojo){

                retardo1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        b.setBackgroundResource(R.color.rojofuerte);
                        sonido[1].start();
                    }
                },tiempoEncendido);
            }else if(b.getId()==R.id.bverde){

                retardo1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        b.setBackgroundResource(R.color.verdefuerte);
                        sonido[2].start();
                    }
                },tiempoEncendido);
            }else {

                retardo1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        b.setBackgroundResource(R.color.amarillofuerte);
                        sonido[3].start();
                    }
                },tiempoEncendido);
            }

            final Button boton= b;

            retardo2.postDelayed(new Runnable() {
                @Override
                public void run() {
                   resetear(boton.getId());
                }
            },tiempoApagado);

            tiempoEncendido+=1500;
            tiempoApagado+=1000;


        }
        tiempoEncendido=1500;
        tiempoApagado=1000;
    
    }

    public void resetear(int id) {



        if (id == R.id.bazul) {

           botones[0].setBackgroundResource(R.color.azul);


        } else if (id == R.id.brojo) {


            botones[1].setBackgroundResource(R.color.rojo);


        } else if (id == R.id.bverde) {


            botones[2].setBackgroundResource(R.color.verde);


        } else {

            botones[3].setBackgroundResource(R.color.amarillo);


        }


    }

public void check(){

    int acertados=0;

    for(int i=0;i<numeros.length;i++){

        if(numeros[i]!=ordenJugador[i]){


            Toast.makeText(this, "Has perdido", Toast.LENGTH_SHORT).show();
        }else{

            acertados++;

        }


        if(acertados==4){
            Toast.makeText(this, "Has ganado", Toast.LENGTH_SHORT).show();
        }
        enabledPlay=false;
        botonesPulsados=0;
        numeros=new int[4];
        ordenJugador=new int[4];

    }




}


}







