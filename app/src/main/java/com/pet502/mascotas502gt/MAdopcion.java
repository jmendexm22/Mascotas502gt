  package com.pet502.mascotas502gt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
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

  public class MAdopcion extends AppCompatActivity implements View.OnClickListener {

      public static final String nombres = "nombre";
      public static final String idUsuario= "idusuario";


      public String idusiario;
      public String r,s;

      private Bitmap bitmap;
      private int PICK_IMAGE_REQUEST = 1;
      private ImageView imageView;
      private  Button btnBuscarImagen ;
      private  Button btnSubirImg;


      private String UPLOAD_URL ="https://lamascotas.com/registrar_Madopcion.php";

      private String KEY_IMAGEN ="foto";
      private String KEY_RAZA ="raza";
      private String KEY_SEXO="sexo";
      private String KEY_EDAD = "edad";
      private String KEY_CONTACTO="contacto";
      private String KEY_IDUSUARIO="Idusuario";



      private Spinner spinnerLenguages;
      private Spinner SpinnerSexo;
      private Spinner Slenguajes;
      private EditText txtEdad;
      private EditText txtTelefono;
      private ImageView imgAdopcion;
      private TextView txtBienvenido;
      private TextView textIdusuario;

      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_madopcion);

          txtBienvenido = (TextView) findViewById(R.id.vistaUsuario);
          String usuario=getIntent().getStringExtra("nombre");
          txtBienvenido.setText("¡Bienvenido "+ usuario + "!");

          textIdusuario=(TextView)findViewById(R.id.IdUsuario);
          String idusuario=getIntent().getStringExtra("idusuario");
          textIdusuario.setText("¡idusuario "+ idusuario + "!");
          idusiario=idusuario;

        spinnerLenguages = findViewById(R.id.spinner_languages);
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.animallist, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerLenguages.setAdapter(adapter);



        SpinnerSexo = findViewById(R.id.spinnersexo);
        ArrayAdapter<CharSequence>adapter1=ArrayAdapter.createFromResource(this, R.array.genero, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_item);
        SpinnerSexo.setAdapter(adapter1);

        ///Slenguajes = (Spinner) findViewById(R.id .spinner4);
        txtEdad = (EditText) findViewById(R.id.txtEdad);
        txtTelefono = (EditText) findViewById(R.id.txtTelefono);
        imageView = (ImageView) findViewById(R.id.imgAdoption);

        ////
  //        String[] lenguajes1 = {"Seleccione","Ruby","Java",".NET","Python","PHP","JavaScript","GO"};
//          Slenguajes.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,lenguajes1));
        /////

        btnBuscarImagen = (Button) findViewById(R.id.btnBuscarImagen);
        btnSubirImg = (Button) findViewById(R.id.btnSubirImg);

        btnBuscarImagen.setOnClickListener(this);
        btnSubirImg.setOnClickListener(this);


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
                          Toast.makeText(MAdopcion.this, s , Toast.LENGTH_LONG).show();
                      }
                  },
                  new Response.ErrorListener() {
                      @Override
                      public void onErrorResponse(VolleyError volleyError) {
                          //Descartar el diálogo de progreso
                          loading.dismiss();

                          //Showing toast
                          Toast.makeText(MAdopcion.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                      }
                  }){
              @Override
              protected Map<String, String> getParams() throws AuthFailureError {
                  //Convertir bits a cadena
                  String imagen = getStringImagen(bitmap);


                  //Obtener el nombre de la imagen
                  String raza = spinnerLenguages.getSelectedItem().toString();
                  String sexo = SpinnerSexo.getSelectedItem().toString();
                  String edad = txtEdad.getText().toString().trim();
                  String contacto = txtTelefono.getText().toString().trim();
                  String Idusuario =  idusiario.toString().trim();

                  //Creación de parámetros
                  Map<String,String> params = new Hashtable<String, String>();


                  //Agregando de parámetros
                  params.put(KEY_RAZA,raza);
                  params.put(KEY_SEXO,sexo);
                  params.put(KEY_EDAD,edad);
                  params.put(KEY_CONTACTO,contacto);
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

          if(v == btnBuscarImagen){
              showFileChooser();
          }
          if(v == btnSubirImg){
              uploadImage();
          }
      }


  }