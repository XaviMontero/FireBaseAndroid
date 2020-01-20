package com.example.proyectofinal.actecar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinal.R;
import com.example.proyectofinal.model.Cliente;
import com.example.proyectofinal.model.Pago;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.valdesekamdem.library.mdtoast.MDToast;

import io.realm.Realm;
import io.realm.RealmResults;

public class ClienteActivity extends AppCompatActivity implements View.OnClickListener {
    private Realm realm =Realm.getDefaultInstance();
    private EditText nombreCliente ;
    private EditText cedulaCliente;
    private EditText direccionCliente;
    private EditText telefonoCliente;
    private TextView buscar;
    private TextView totales;
    private Button registrarSiguiente;
    private Cliente cliente;
    private Double total;
    private Double latitud;
    private Double logitud;
    MDToast mdToast;

    int PERMISSION_ID = 44;
    FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        referencia();
        clik();

        getLastLocation();

    }

    private void clik() {
        buscar.setOnClickListener(this);
        registrarSiguiente.setOnClickListener(this);
        totales.setText("Su total es "+String.valueOf(total));
    }

    private void referencia() {
        nombreCliente = findViewById(R.id.etNombreCliente);
        cedulaCliente =   findViewById(R.id.etCedulaCliente);
        direccionCliente = findViewById(R.id.etDireccionCliente);
        telefonoCliente = findViewById(R.id.etTelefonoCliente);
        buscar = findViewById(R.id.etBuscar);
        registrarSiguiente = findViewById(R.id.siguiente);
        Intent myIntent = getIntent(); // gets the previously created intent
        total = myIntent.getDoubleExtra("total",0.0);
        totales= findViewById(R.id.totales);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.siguiente:
                if(validLatiLogit()){
                if(buscarCliente(cedulaCliente.getText().toString())){
                   Cliente clienteGuardar = new Cliente();
                   clienteGuardar.setCedula(cedulaCliente.getText().toString());
                   clienteGuardar.setNombre(nombreCliente.getText().toString());
                   clienteGuardar.setDireccion(direccionCliente.getText().toString());
                   clienteGuardar.setTelefono(telefonoCliente.getText().toString());
                    realm.beginTransaction();
                    realm.insert(clienteGuardar);
                    realm.commitTransaction();

                      mdToast = MDToast.makeText(this, "Listo " ,  1000, 1);
                    mdToast.show();

                    Pago pago = new Pago();
                         pago.setId(contador());
                        pago.setCedula(cedulaCliente.getText().toString());
                        pago.setTotal(total);
                        pago.setLatitud(latitud);
                        pago.setLongitud(logitud);
                        realm.beginTransaction();
                        realm.insert(pago);
                        realm.commitTransaction();

                      mdToast = MDToast.makeText(this, "Pago Con exito total es " ,  1000, 1);
                    mdToast.show();

                    goToCompras();
                }else{
                    if(validLatiLogit()){
                        Pago pago = new Pago();
                        pago.setId(contador());
                        pago.setCedula(cedulaCliente.getText().toString());
                        pago.setTotal(total);
                        pago.setLatitud(latitud);
                        pago.setLongitud(logitud);
                        realm.beginTransaction();
                        realm.insert(pago);
                        realm.commitTransaction();

                        mdToast = MDToast.makeText(this, "Pago Con exito total es  " +total +latitud+" "+logitud,  1000, 1);
                        mdToast.show();

                        goToCompras();
                    }else{
                        Toast.makeText(getApplicationContext(), "Error con Logitud  y latitud  " ,
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                }
                }else{
                    Toast.makeText(getApplicationContext(), "Error con Logitud  y latitud  " ,
                            Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            case R.id.etBuscar:
                rellenar();
                break;
                default:

        }

    }

    private boolean validLatiLogit() {
        if(latitud!= null &&logitud!=null)
        {
            return  true;
        }else{
            return false;
        }
    }

    private void goToCompras() {
        Intent myIntent = new Intent(this, ProductosActivity.class);
        myIntent.putExtra("total",0.0);
        startActivity(myIntent);
    }

    private void rellenar() {
        if(!buscarCliente(cedulaCliente.getText().toString())){
            RealmResults<Cliente> result2 = realm.where(Cliente.class).equalTo("cedula",cedulaCliente.getText().toString()).findAll();
            Cliente retorno = result2.get(0);
            nombreCliente.setText(retorno.getNombre());
            cedulaCliente.setText(retorno.getCedula());
            direccionCliente.setText(retorno.getDireccion());
            telefonoCliente.setText(retorno.getTelefono());

        }else{
            mdToast = MDToast.makeText(this, "Datos en blanco " +total +latitud+" "+logitud,  1000, 2);
            mdToast.show();
         }
    }

    private boolean buscarCliente(String cedula) {
        RealmResults<Cliente> result2 = realm.where(Cliente.class).equalTo("cedula",cedula).findAll();


        return result2.size() == 0;
    }


    //Get Location

    @SuppressLint("MissingPermission")
    private void getLastLocation(){
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                Location location = task.getResult();
                                if (location == null) {
                                    requestNewLocationData();
                                } else {
                                        latitud= location.getLatitude();
                                        logitud=location.getLongitude();
                                   /* latTextView.setText(location.getLatitude()+"");
                                    lonTextView.setText(location.getLongitude()+"");*/
                                }
                            }
                        }
                );
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }


    @SuppressLint("MissingPermission")
    private void requestNewLocationData(){

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );

    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();

            latitud= mLastLocation.getLatitude();
            logitud=mLastLocation.getLongitude();
        }
    };

    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }
    private long contador() {
        RealmResults<Pago> result2 = realm.where(Pago.class).findAll();
        return result2.size()+1;
    }

    @Override
    public void onResume(){
        super.onResume();
        if (checkPermissions()) {
            getLastLocation();
        }

    }
}
