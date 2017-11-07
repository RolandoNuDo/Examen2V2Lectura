package com.rolando.examen2v2lectura;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    EditText txt;
    Button act;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (EditText) findViewById(R.id.editText);
        act = (Button) findViewById(R.id.button);
        act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarDesdeSD();
            }
        });
        cargarDesdeSD();
    }
    public void cargarDesdeSD(){
        try {

            File archivo = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),"archivo.txt");
            if(archivo.exists()){
                Toast.makeText(MainActivity.this, "si encontre el archivo", Toast.LENGTH_SHORT).show();
            }
            if(hasExternalStorage()&&archivo.exists()) {

                FileInputStream stream = new FileInputStream(archivo);
                InputStreamReader lector = new InputStreamReader(stream);
                BufferedReader contenido = new BufferedReader(lector);
                String linea, mensaje= "";
                while ((linea = contenido.readLine()) != null){
                    mensaje += linea;

                }
                lector.close();
                txt.setText(mensaje);
            }
        }catch (Exception e){

        }
    }
    public boolean hasExternalStorage(){
        String status= Environment.getExternalStorageState();
        if(status.equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(MainActivity.this, "Si tienes sd", Toast.LENGTH_SHORT).show();
            return true;

        }
        return false;
    }
}
