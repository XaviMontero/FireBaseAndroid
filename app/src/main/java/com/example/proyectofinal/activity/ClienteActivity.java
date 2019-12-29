package com.example.proyectofinal.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinal.R;
import com.example.proyectofinal.model.Cliente;
import com.example.proyectofinal.model.Pago;
import com.example.proyectofinal.model.User;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        referencia();
        clik();

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

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.siguiente:
                if(buscarCliente(cedulaCliente.getText().toString())){
                   Cliente clienteGuardar = new Cliente();
                   clienteGuardar.setCedula(cedulaCliente.getText().toString());
                   clienteGuardar.setNombre(nombreCliente.getText().toString());
                   clienteGuardar.setDireccion(direccionCliente.getText().toString());
                   clienteGuardar.setTelefono(telefonoCliente.getText().toString());
                    realm.beginTransaction();
                    realm.insert(clienteGuardar);
                    realm.commitTransaction();
                    Toast.makeText(getApplicationContext(), "Cuenta Creada  creada ",
                            Toast.LENGTH_SHORT)
                            .show();
                    Pago pago = new Pago();
                        pago.setCedula(cedulaCliente.getText().toString());
                        pago.setTotal(total);
                    Toast.makeText(getApplicationContext(), "Pago Con exito total es  " +total,
                            Toast.LENGTH_SHORT)
                            .show();
                    goToCompras();
                }else{
                    Pago pago = new Pago();
                    pago.setCedula(cedulaCliente.getText().toString());
                    pago.setTotal(total);
                    Toast.makeText(getApplicationContext(), "Pago Con exito total es  " +total,
                            Toast.LENGTH_SHORT)
                            .show();
                    goToCompras();
                }
                break;
            case R.id.etBuscar:
                rellenar();
                break;
                default:

        }

    }

    private void goToCompras() {
        Intent myIntent = new Intent(this, MainActivity.class);
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
            Toast.makeText(this,"El cliente no exite llene los datos",Toast.LENGTH_LONG).show();
        }
    }

    private boolean buscarCliente(String cedula) {
        RealmResults<Cliente> result2 = realm.where(Cliente.class).equalTo("cedula",cedula).findAll();


        return result2.size() == 0;
    }
}
