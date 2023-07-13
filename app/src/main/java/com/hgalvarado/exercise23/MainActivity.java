package com.hgalvarado.exercise23;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView imagenCapturada;
    ImageButton imgTomarFoto;
    Button btnguardarFoto,btnverlistafotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagenCapturada = (ImageView) findViewById(R.id.imagenCaputar);
        imgTomarFoto = (ImageButton) findViewById(R.id.imgBtnTomarFotografia);
        btnguardarFoto = (Button) findViewById(R.id.btnGuardarFotografia);
        btnverlistafotos = (Button) findViewById(R.id.btnListaFotografias);

        imagenCapturada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"TOMAR FOTOGRAFIA",Toast.LENGTH_LONG).show();
            }
        });
        btnguardarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"TOMAR FOTOGRAFIA",Toast.LENGTH_LONG).show();
            }
        });
        btnverlistafotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"TOMAR FOTOGRAFIA",Toast.LENGTH_LONG).show();
            }
        });

    }

}