package com.pet502.mascotas502gt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class MainEncontradas extends AppCompatActivity {


    private  static  String url="https://lamascotas.com/M_encontradas.php";

    List<M_encontradas>mecontradasList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_encontradas);

        recyclerView=findViewById(R.id.recy2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mecontradasList=new ArrayList<>();

        cargarImagen();

    }


    private void cargarImagen() {
        StringRequest stringRequest =new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject Encontradas = array.getJSONObject(i);

                        mecontradasList.add(new M_encontradas(
                                Encontradas.getInt("idMEncontradas"),
                                Encontradas.getString("genero"),
                                Encontradas.getString("sexo"),
                                Encontradas.getString("direccion"),
                                Encontradas.getString("contacto"),
                                Encontradas.getString("img"),
                                Encontradas.getString("Usuario_idUsuario")
                        ));
                    }
                    Adapter2 adapter = new Adapter2(MainEncontradas.this, mecontradasList);
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