package com.pet502.mascotas502gt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class Register extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener  {

    RequestQueue rq;
    JsonRequest jrq;

    EditText textUser,textEmail, textPasswod,textApellido, textNombre, textCiudad;

    Button btnSesion, btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        textUser = (EditText) findViewById(R.id.TextUsuarios);
        textEmail = (EditText) findViewById(R.id.TextEmail);
        textPasswod= (EditText) findViewById(R.id.TextPassword);
        textApellido=(EditText) findViewById(R.id.TextApellido);
        textNombre=(EditText) findViewById(R.id.TextNombre);
        textCiudad=(EditText) findViewById(R.id.TextCiudad);

        btnSesion = (Button) findViewById(R.id.btnIniciarSession);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        rq = Volley.newRequestQueue(getBaseContext());

        btnSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (v.getContext(),MainActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registrar_usuario();
            }
        });

    }


    @Override
    public void onErrorResponse(VolleyError error) {
        //Toast.makeText(getBaseContext(), "No Se pudó registrar el usuario " +error.toString()+ textUser.getText().toString(), Toast.LENGTH_LONG).show();
        final ProgressDialog loading = ProgressDialog.show(this,"Error","No Se pudó registrar el usuario",false,false);
    }

    @Override
    public void onResponse(JSONObject response) {
        //Toast.makeText(getBaseContext(), "Se ha registrado el usuario " + textUser.getText().toString(), Toast.LENGTH_SHORT).show();
        final ProgressDialog loading = ProgressDialog.show(this,"Correcto","Se ha registrado el usuario",false,false);

        limpiarCajas();
    }

    void limpiarCajas(){
        textUser.setText("");
        textEmail.setText("");
        textPasswod.setText("");
        textApellido.setText("");
        textNombre.setText("");
        textCiudad.setText("");
    }


    private void registrar_usuario(){
        //192.168.1.66(172.29.243.3
        ///http://192.168.43.63:8081/Mascotas3/registrar_usuario.php?usuario=
        String url = "https://lamascotas.com/registrar_usuario.php?usuario="
                +textUser.getText().toString()+
                "&email="+ textEmail.getText().toString() +
                "&password=" + textPasswod.getText().toString()+
                "&apellido=" + textApellido.getText().toString()+
                "&nombre=" + textNombre.getText().toString()+
                "&ciudad=" +textCiudad.getText().toString()
                ;


        jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        rq.add(jrq);
    }
}