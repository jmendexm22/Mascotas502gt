package com.pet502.mascotas502gt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainAdopcion extends AppCompatActivity {

    private  static  String url="https://lamascotas.com/listarAdopcion.php";

    List<Adopcion>adopcionList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_adopcion);

        recyclerView=findViewById(R.id.recy3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adopcionList=new ArrayList<>();

        cargarImagen();

    }

    private void cargarImagen() {
        StringRequest stringRequest =new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject Adopcion = array.getJSONObject(i);

                        adopcionList.add(new Adopcion(
                                Adopcion.getInt("idMAdopcion"),
                                Adopcion.getString("raza"),
                                Adopcion.getString("sexo"),
                                Adopcion.getString("edad"),
                                Adopcion.getString("contacto"),
                                Adopcion.getString("img"),
                                Adopcion.getString("Usuario_idUsuario")
                        ));
                    }
                    Adapter3 adapter = new Adapter3(MainAdopcion.this, adopcionList);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(stringRequest);
    }

}