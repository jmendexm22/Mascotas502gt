package com.pet502.mascotas502gt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class Mencontadas extends AppCompatActivity implements View.OnClickListener {


    public static final String nombres = "nombre";
    public static final String idUsuario= "idusuario";

    ////////////
    public String idusiario;
    TextView txtBienvenido;
    TextView textIdusuario;
    EditText nombreMascota;
    /////////////
    TextView direccion, lat,lon;
    Button guardar;

    /////cargar imagen///
    private Button btnBuscar;
    private Button btnSubir;

    private ImageView imageView;


    private EditText Mcontacto,Mdireccion;
    private Spinner m_especie,Msexo;

    private Bitmap bitmap;

    private int PICK_IMAGE_REQUEST = 1;

    private String UPLOAD_URL ="https://lamascotas.com/registrar_Mencontradas.php";




    private String KEY_IMAGEN = "foto";
    private String KEY_MESPECIE = "especie";
    private String KEY_SEXO="sexo";
    private String KEY_DIRECCION = "direccion";
    private String KEY_CONTACTO="contacto";
    private String KEY_IDUSUARIO="Idusuario";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mencontadas);

        TextView txtBienvenido = (TextView) findViewById(R.id.vitaUsuario);
        String usuario=getIntent().getStringExtra("nombre");
        txtBienvenido.setText("¡Bienvenido "+ usuario + "!");

        textIdusuario=(TextView)findViewById(R.id.vistaIdUsuario);
        String idusuario=getIntent().getStringExtra("idusuario");
        textIdusuario.setText("¡idusuario "+ idusuario + "!");
        idusiario=idusuario;

        //animallist
        //genero

        ////
        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        btnSubir = (Button) findViewById(R.id.btnSubir);

         //m_especie = (Spinner) findViewById(R.id.Mespecie);
         //Msexo = (Spinner) findViewById(R.id.Msexo);

        m_especie = findViewById(R.id.Mespecie);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.animallist, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        m_especie.setAdapter(adapter);

        Msexo = findViewById(R.id.Msexo);
        ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(this, R.array.genero, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_item);
        Msexo.setAdapter(adapter1);


        Mcontacto = (EditText) findViewById(R.id.Mcontacto);
         Mdireccion = (EditText) findViewById(R.id.Mdireccion);

        imageView  = (ImageView) findViewById(R.id.imageView);

        /////GPS//////////
        /////////////////////gps
        btnBuscar.setOnClickListener(this);
        btnSubir.setOnClickListener(this);

    }

    public String getStringImagen(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    private void uploadImage(){
        //Mostrar el diálogo de progreso
        final ProgressDialog loading = ProgressDialog.show(this,"Subiendo...","Espere por favor...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Descartar el diálogo de progreso
                        loading.dismiss();
                        //Mostrando el mensaje de la respuesta
                        Toast.makeText(Mencontadas.this, s , Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Descartar el diálogo de progreso
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(Mencontadas.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Convertir bits a cadena
                String imagen = getStringImagen(bitmap);

                //Obtener el nombre de la imagen
                String especie = m_especie.getSelectedItem().toString().trim();
                String sexo = Msexo.getSelectedItem().toString().trim();
                String direccion = Mdireccion.getText().toString().trim();
                String contacto = Mcontacto.getText().toString().trim();
                String Idusuario =  idusiario.toString().trim();
                //Creación de parámetros
                Map<String,String> params = new Hashtable<String, String>();


                //Agregando de parámetros
                params.put(KEY_MESPECIE, especie);
                params.put(KEY_SEXO, sexo);
                params.put(KEY_CONTACTO, contacto);
                params.put(KEY_DIRECCION, direccion);
                params.put(KEY_IMAGEN, imagen);
                params.put(KEY_IDUSUARIO,Idusuario);


                //Parámetros de retorno
                return params;
            }
        };

        //Creación de una cola de solicitudes
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Agregar solicitud a la cola
        requestQueue.add(stringRequest);
    }


    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Imagen"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Cómo obtener el mapa de bits de la Galería
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Configuración del mapa de bits en ImageView
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onClick(View v) {

        if(v == btnBuscar){
            showFileChooser();
        }

        if(v == btnSubir){
            uploadImage();
        }
    }


}