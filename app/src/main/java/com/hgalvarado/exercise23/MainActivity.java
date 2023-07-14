package com.hgalvarado.exercise23;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.hgalvarado.exercise23.Configuration.Photograh;
import com.hgalvarado.exercise23.Configuration.SQLiteConexion;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
    ImageView imagenCapturada;
    ImageButton btnImgTomarFoto;
    Button btnguardarFoto,btnverlistafotos;
    EditText txtDescripcion;

    SQLiteConexion conexion;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagenCapturada = (ImageView) findViewById(R.id.imagenCaputar);
        btnImgTomarFoto = (ImageButton) findViewById(R.id.imgBtnTomarFotografia);
        btnguardarFoto = (Button) findViewById(R.id.btnGuardarFotografia);
        btnverlistafotos = (Button) findViewById(R.id.btnListaFotografias);
        txtDescripcion =(EditText) findViewById(R.id.txtDescripcionCaptura);

        btnImgTomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCameraPermission();
            }
        });
        btnguardarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarFotos();
            }
        });
        btnverlistafotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"TOMAR FOTOGRAFIA",Toast.LENGTH_LONG).show();
            }
        });

    }
    private void checkCameraPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.CAMERA);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.i("Mensaje", "No se tiene permiso para la camara!.");
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, 225);
        } else {
            Log.i("Mensaje", "Tienes permiso para usar la camara.");
            OpenCamera();
        }
    }
    private void OpenCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
       if (intent.resolveActivity(getPackageManager())!=null){
           startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
       }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imagenCapturada.setImageBitmap(imageBitmap);
        }
    }

    public void guardarFotos() {
        BitmapDrawable drawable = (BitmapDrawable) imagenCapturada.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        conexion = new SQLiteConexion(this,Photograh.nameDataBase,null,Photograh.versionDataBase);
        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Photograh.columnphoto, byteArray);
        values.put(Photograh.columndescription, txtDescripcion.getText().toString());
        db.insert(Photograh.tableName, null, values);
        Toast.makeText(getApplicationContext(), "Registro ingresado: " + values.toString(),Toast.LENGTH_LONG).show();
        CleanScreen();
        db.close();
    }
    private void CleanScreen() {
//        imagenCapturada.setImageResource(R.drawable.imagenpordefecto);
        txtDescripcion.setText("");
    }
}