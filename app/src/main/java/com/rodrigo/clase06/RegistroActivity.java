package com.rodrigo.clase06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //OBTENIENDO COMPONENTES
        MaterialButton btn_registrar_usaurio = findViewById(R.id.btn_registrar_usuario);
        TextInputEditText input_nombres = findViewById(R.id.input_nombres);
        TextInputEditText input_apellidos = findViewById(R.id.input_apellidos);
        TextInputEditText input_usuario = findViewById(R.id.input_usuario);
        TextInputEditText input_contrasena1 = findViewById(R.id.input_contrasena1);
        TextInputEditText input_contrasena2 = findViewById(R.id.input_contrasena2);

        btn_registrar_usaurio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contrasena1, contrasena2, nombres, apellidos, usuario;

                contrasena1 = String.valueOf(input_contrasena1.getText());
                contrasena2 = String.valueOf(input_contrasena2.getText());
                nombres = String.valueOf(input_nombres.getText());
                apellidos = String.valueOf(input_apellidos.getText());
                usuario = String.valueOf(input_usuario.getText());

                if(contrasena1.toLowerCase().equalsIgnoreCase(contrasena2.toLowerCase())){
                    registrarUsuario(nombres.toLowerCase(), apellidos.toLowerCase(), usuario.toLowerCase(), contrasena1.toLowerCase());
                }else{
                    Toast.makeText(RegistroActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void registrarUsuario(String nombres, String apellidos, String usuario, String contrasena){
        try {
            //ESCRIBIRNEDO DATOS EN EL ARCHIVO
            OutputStreamWriter out = new OutputStreamWriter((openFileOutput("user.txt", Context.MODE_APPEND)));
            out.write(nombres+",");
            out.write(apellidos+",");
            out.write(usuario+",");
            out.write(contrasena+"/");
            out.close();
            Toast.makeText(this,"Usuario Registrado", Toast.LENGTH_SHORT).show();
            //VIAJANDO AL ACTIVITY LOGIN
            Intent i = new Intent(RegistroActivity.this, MainActivity.class);
            startActivity(i);

        } catch (FileNotFoundException e){
            e.printStackTrace();
            Toast.makeText(this, "El archivo no existe", Toast.LENGTH_SHORT).show();
        } catch (IOException e){
            e.printStackTrace();
            Toast.makeText(this, "Error al escribir en el archivo", Toast.LENGTH_SHORT).show();
        }
    }
}