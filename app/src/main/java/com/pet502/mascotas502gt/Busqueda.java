package com.pet502.mascotas502gt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
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
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Busqueda extends AppCompatActivity implements View.OnClickListener {

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


    ///////////////////



 //////////////////////////////////

    /////cargar imagen///
    private Button btnBuscar;
    private Button btnSubir;
    private Spinner spinnerEspecie;
    private Spinner Spinner_Genero;

    private ImageView imageView;
    private EditText editTextName;
    private EditText textspecie,textgenero,textedad,textcontacto,textcorreo;
    private Bitmap bitmap;

    private int PICK_IMAGE_REQUEST = 1;

    private String UPLOAD_URL ="https://lamascotas.com/registrar_busqueda.php";

    private String KEY_NOMBRE = "nombre";
    private String KEY_ESPECIE = "especie";
    private String KEY_GENERO = "genero";
    private String KEY_EDAD = "edad";
    private String KEY_IMAGEN = "foto";
    private String KEY_DIRECCION="direccion";
    private String KEY_CONTACTO = "contacto";

    private String KEY_CORREO = "correo";
    private String KEY_LAT="Lat";
    private String KEY_LON="Lon";
    private String KEY_IDUSUARIO="Idusuario";

    ////fin de cargar imagen////



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);

        TextView txtBienvenido = (TextView) findViewById(R.id.vitaUsuario);
        String usuario=getIntent().getStringExtra("nombre");
        txtBienvenido.setText("¡Bienvenido "+ usuario + "!");

        textIdusuario=(TextView)findViewById(R.id.vistaIdUsuario);
        String idusuario=getIntent().getStringExtra("idusuario");
        textIdusuario.setText("¡idusuario "+ idusuario + "!");
        idusiario=idusuario;

        ////////////////////

        spinnerEspecie = findViewById(R.id.spinnerespecie);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.animallist, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerEspecie.setAdapter(adapter);

        Spinner_Genero = findViewById(R.id.spinnerGenero);
        ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(this, R.array.genero, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_item);
        Spinner_Genero.setAdapter(adapter1);


        //////////////////////
       //// nombreMascota =(EditText)findViewById(R.id.TextNombreMascota);
        //////

        //direccion=(TextView)findViewById(R.id.txtDireccion);
        //lat=(TextView)findViewById(R.id.txtLat);
        //lon=(TextView)findViewById(R.id.txtLon);
        ///btnGuardar=(Button)findViewById(R.id.btnGuardar);

        ////
        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        btnSubir = (Button) findViewById(R.id.btnSubir);
        editTextName = (EditText) findViewById(R.id.TextNombreMascota);
        imageView  = (ImageView) findViewById(R.id.imageView);


        //textspecie = (EditText) findViewById(R.id.especie1);
        //textgenero = (EditText) findViewById(R.id.genero);
        textedad = (EditText) findViewById(R.id.edad1);
        textcontacto = (EditText) findViewById(R.id.contacto1);
        textcorreo = (EditText) findViewById(R.id.correo1);


        /////GPS//////////
        direccion=(TextView)findViewById(R.id.txtDireccion);
        lat=(TextView)findViewById(R.id.txtLat);
        lon=(TextView)findViewById(R.id.txtLon);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        } else {
            locationStart();
        }
        /////////////////////gps




        btnBuscar.setOnClickListener(this);
        btnSubir.setOnClickListener(this);
        ////


    }


    //////////////////////GPS/////////////////////////
    private void locationStart() {
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localizacion Local = new Localizacion();
        Local.setMainActivity(this);
        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) Local);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) Local);

        lat.setText("Localización agregada");
        direccion.setText("");
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationStart();
                return;
            }
        }
    }

    public void setLocation(Location loc) {
        //Obtener la direccion de la calle a partir de la latitud y la longitud
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(
                        loc.getLatitude(), loc.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address DirCalle = list.get(0);
                    direccion.setText(DirCalle.getAddressLine(0));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public class Localizacion implements LocationListener {
        Busqueda activity_registro_busqueda;

        public Busqueda getMainActivity() {
            return activity_registro_busqueda;
        }

        public void setMainActivity(Busqueda mainActivity) {
            this.activity_registro_busqueda = mainActivity;
        }

        @Override
        public void onLocationChanged(Location loc) {
            // Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
            // debido a la deteccion de un cambio de ubicacion

            loc.getLatitude();
            loc.getLongitude();

            String Text1 = String.valueOf(loc.getLatitude());
            String Text2 = String.valueOf(loc.getLongitude());
            lat.setText(Text1);
            lon.setText(Text2);
            this.activity_registro_busqueda.setLocation(loc);
        }

        @Override
        public void onProviderDisabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es desactivado
            lat.setText("GPS Desactivado");
        }

        @Override
        public void onProviderEnabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es activado
            lat.setText("GPS Activado");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.AVAILABLE:
                    Log.d("debug", "LocationProvider.AVAILABLE");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                    break;
            }
        }
    }

    ///////////////////////////////////////GPS......//
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
                        Toast.makeText(Busqueda.this, s , Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Descartar el diálogo de progreso
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(Busqueda.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Convertir bits a cadena
                String imagen = getStringImagen(bitmap);

                //Obtener el nombre de la imagen
                String nombre = editTextName.getText().toString().trim();
                String especie = spinnerEspecie.getSelectedItem().toString();
                String genero = Spinner_Genero.getSelectedItem().toString();
                String edad = textedad.getText().toString().trim();
                String contacto = textcontacto.getText().toString().trim();
                String correo = textedad.getText().toString().trim();
                String direcciones = direccion.getText().toString().trim();
                String Lat = lat.getText().toString().trim();
                String Lon = lon.getText().toString().trim();
                String Idusuario =  idusiario.toString().trim();
                //Creación de parámetros
                Map<String,String> params = new Hashtable<String, String>();


                //Agregando de parámetros
                params.put(KEY_NOMBRE,nombre);
                params.put(KEY_ESPECIE,especie);
                params.put(KEY_GENERO,genero);
                params.put(KEY_EDAD,edad);
                params.put(KEY_DIRECCION,direcciones);
                params.put(KEY_IMAGEN,imagen);
                params.put(KEY_CONTACTO,contacto);
                params.put(KEY_CORREO,correo);
                params.put(KEY_LAT,Lat);
                params.put(KEY_LON,Lon);
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