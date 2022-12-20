package com.pet502.mascotas502gt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {

    EditText rusuario,rpassword;



    RequestQueue rq;
    JsonRequest jrq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rusuario=(EditText) findViewById(R.id.txtusuario);
        rpassword=(EditText) findViewById(R.id.txtPassword);
        Button btnConsultar = (Button) findViewById(R.id.btnSession);
        Button btnRegistrar = (Button) findViewById(R.id.btnRegister);
        rq = Volley.newRequestQueue(getBaseContext());



        //////boton consultar
        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iniciar_session();


            }
        });
            //////btn registrar
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (v.getContext(), Register.class);
                startActivityForResult(intent, 0);
            }
        });

    }
    @Override
    public void onErrorResponse(VolleyError error) {

        //Toast.makeText(getBaseContext(),"No se encontro el usuario "+ error.toString(),Toast.LENGTH_SHORT).show();
        final ProgressDialog loading = ProgressDialog.show(this,"Usuario","No encontrado",true,true);

        // Log.i("Error",error.toString());
        Log.d("Error.Response", error.toString());
    }



    @Override
    public void onResponse(JSONObject response) {
        User usuario = new User();
        ///Toast.makeText(getBaseContext(),"se ha encontado el usuario"+ rusuario.toString(),Toast.LENGTH_SHORT).show();
        final ProgressDialog loading = ProgressDialog.show(this,"Usuario","Espere por favor...",true,true);

        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;
        try {
            jsonObject = jsonArray.getJSONObject(0);
            usuario.setIdUsuario(jsonObject.optString("idusuario"));
            usuario.setUsuario(jsonObject.optString("usuario"));
            usuario.setPassword(jsonObject.optString("password"));
            usuario.setNombre(jsonObject.optString("nombre"));

        }catch (JSONException e){
            e.printStackTrace();
        }

        Intent intentos = new Intent(getBaseContext(), MenuPrincipal.class);
        intentos.putExtra(MenuPrincipal.idUsuario, usuario.getIdUsuario());
        intentos.putExtra(MenuPrincipal.nombres, usuario.getNombre());
/*
        Intent intentos1 = new Intent(getContext(),RegistroBusqueda.class);
        intentos1.putExtra(RegistroBusqueda.idUsuario, usuario.getIdUsuario());
        intentos1.putExtra(RegistroBusqueda.nombres, usuario.getNombre());
*/
        startActivity(intentos);
        //startActivity(intentos1);



    }

    public void ActividadRegistroBusqueda(){
        User usuario = new User();
        //Toast.makeText(getBaseContext(),"se ha encontado el usuario "+ rusuario.getText().toString(),Toast.LENGTH_SHORT).show();
        final ProgressDialog loading = ProgressDialog.show(this,"Usuario","Espere por favor...",false,false);

        Intent intentos1 = new Intent(getBaseContext(),Register.class);
        //intentos1.putExtra(Register.idUsuario, usuario.getIdUsuario());
        //intentos1.putExtra(Register.nombres, usuario.getNombre());
        startActivity(intentos1);
    }


    private  void iniciar_session(){
        ////http://192.168.43.63:8081/Mascotas3/validar_usuario.php?usuario=
        String url="https://lamascotas.com/validar_usuario.php?usuario="+rusuario.getText().toString()+
                "&password="+rpassword.getText().toString();
        jrq = new JsonObjectRequest(Request.Method.GET,url, null, this,this);
        rq.add(jrq);
    }

   }

