package com.rodrigo.clase06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //OBTENIENDO COMPONENTES
        MaterialButton btn_inicio_sesion = findViewById(R.id.btn_inicio_sesion);
        MaterialButton btn_registro_usuario = findViewById(R.id.btn_registro_usuario);

        TextInputEditText input_usuario = findViewById(R.id.input_usuario);
        TextInputEditText input_contrasena = findViewById(R.id.input_contrasena);

        btn_inicio_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario, contrasena;

                usuario = String.valueOf(input_usuario.getText());
                contrasena = String.valueOf(input_contrasena.getText());

                iniciar_sesion(usuario, contrasena);
            }
        });

        btn_registro_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(i);

            }
        });

    }

    public void iniciar_sesion(String usuario, String contrasena){
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

                if( usuario.toLowerCase().equalsIgnoreCase(user[2]) && contrasena.toLowerCase().equalsIgnoreCase(user[3]) ){
                    Toast.makeText(this, "Credenciales correctas", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, ListaActivity.class);
                    startActivity(intent);
                }else{

                }
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