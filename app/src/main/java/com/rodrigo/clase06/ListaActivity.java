package com.rodrigo.clase06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ListaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        //OBTENIENDO COMPONENTES
        TextView txt_lista_usuarios = findViewById(R.id.txt_lista_usuarios);
        MaterialButton btn_cerrar_sesion = findViewById(R.id.btn_cerrar_sesion);

        listarUsuarios(txt_lista_usuarios);

        btn_cerrar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListaActivity.this, MainActivity.class);
                startActivity(i);
            }
        });


    }

    public void listarUsuarios(TextView txt_lista){
        try {
            //OBTENIENDO CONTENIDO DEL ARCHIVO
            BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput("user.txt")));
            String contenido="";
            String textoBuffer;
            while((textoBuffer=reader.readLine())!=null){
                contenido=textoBuffer;
            }
            reader.close();
            //GESTIONANDO LA INFORMACIÓN
            String [] usuarios = contenido.split("/");

            for (int i = 0; i < usuarios.length; i++) {
                String [] user = null;
                user = usuarios[i].split(",");

                txt_lista.append("Persona " + (i+1) + "=>" + "Nombres: "+user[0]+" Apellidos: " + user[1] + " Usuario: " + user[2] + " Contraseña: " + user[3] + "\n");
            }

        }catch(FileNotFoundException e){
            e.printStackTrace();
            Toast.makeText(this, "El archivo no se encontró", Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            e.printStackTrace();
            Toast.makeText(this, "Error al leer el archivo", Toast.LENGTH_SHORT).show();
        }
    }
}