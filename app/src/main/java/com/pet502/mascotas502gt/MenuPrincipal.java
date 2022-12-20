package com.pet502.mascotas502gt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuPrincipal extends AppCompatActivity {

    public static final String nombres = "nombre";
    public static final String idUsuario= "idusuario";
    TextView txtBienvenido;
    TextView textIdusuario;
    private String nombre, idusuario;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        txtBienvenido=(TextView)findViewById(R.id.txtBienvenido);
        String usuario=getIntent().getStringExtra("nombre");
        txtBienvenido.setText("¡Bienvenido "+ usuario + "!");

        textIdusuario=(TextView)findViewById(R.id.txtIdUsuario);
        String idusuario=getIntent().getStringExtra("idusuario");
        textIdusuario.setText("¡idusuario "+ idusuario + "!");

        url="https://lamascotas.com/indexgrafica.php";


        Button btn = (Button) findViewById(R.id.generarAlerta);
        Button btnMencotradas = (Button) findViewById(R.id.btnMencontradas);
        Button M_econtradas = (Button) findViewById(R.id.Mas_encotra);
        Button marcadores =(Button) findViewById(R.id.btnMacadores);


        Button MainMascotas = (Button) findViewById(R.id.MainMascotas);
        Button BtnAdopcion = (Button) findViewById(R.id.btnAdopcion);
        Button listaAdopcion = (Button) findViewById(R.id.btnAdop);
        Button btnlinkgrafica = (Button) findViewById(R.id.btnGrafia);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre,idusuario;

                nombre=getIntent().getStringExtra("idusuario");
                idusuario=getIntent().getStringExtra("idusuario");

                Intent intent = new Intent(v.getContext(),Busqueda.class);
                intent.putExtra(Busqueda.idUsuario, nombre);
                intent.putExtra(Busqueda.nombres, idusuario);
                startActivity(intent);
            }
        });

        marcadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent intent = new Intent(v.getContext(),MapsActivity.class);
                startActivity(intent);

            }
        });

        MainMascotas.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),MainMascotas.class);
                startActivity(intent);
            }
        });

        btnMencotradas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre,idusuario;

                nombre=getIntent().getStringExtra("idusuario");
                idusuario=getIntent().getStringExtra("idusuario");

                Intent intent = new Intent(v.getContext(),Mencontadas.class);
                intent.putExtra(Busqueda.idUsuario, nombre);
                intent.putExtra(Busqueda.nombres, idusuario);
                startActivity(intent);
            }
        });

        M_econtradas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),MainEncontradas.class);
                startActivity(intent);
            }
        });

        BtnAdopcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre,idusuario;

                nombre=getIntent().getStringExtra("idusuario");
                idusuario=getIntent().getStringExtra("idusuario");
                Intent intent = new Intent(v.getContext(),MAdopcion.class);
                intent.putExtra(Busqueda.idUsuario, nombre);
                intent.putExtra(Busqueda.nombres, idusuario);
                startActivity(intent);
            }
        });

        listaAdopcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),MainAdopcion.class);
                startActivity(intent);
            }
        });

        btnlinkgrafica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(url);

                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });


    }


}