package com.example.crudpersona;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ActivityEdit extends AppCompatActivity {

    EditText aetxtnombres,aetxtapellidos,aetxtedad,aetxtcorreo,aetxtdireccion;
    Button aebtnactualizar,aebtneliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        aetxtnombres = (EditText) findViewById(R.id.aetxtNombres);
        aetxtapellidos = (EditText) findViewById(R.id.aetxtApellidos);
        aetxtedad = (EditText) findViewById(R.id.aetxtEdad);
        aetxtcorreo = (EditText) findViewById(R.id.aetxtCorreo);
        aetxtdireccion = (EditText) findViewById(R.id.aetxtDireccion);
        aebtnactualizar = (Button) findViewById(R.id.aebtnActualizar);
        aebtneliminar = (Button) findViewById(R.id.aebtnEliminar);

        String codigos = getIntent().getStringExtra("codigo");
        String aenombres = getIntent().getStringExtra("nombres");
        String aeapellidos = getIntent().getStringExtra("apellidos");
        String aeedad = getIntent().getStringExtra("edad");
        String aecorreo = getIntent().getStringExtra("correo");
        String aedireccion = getIntent().getStringExtra("direccion");

        aetxtnombres.setText(aenombres);
        aetxtapellidos.setText(aeapellidos);
        aetxtedad.setText(aeedad);
        aetxtcorreo.setText(aecorreo);
        aetxtdireccion.setText(aedireccion);

        System.out.println(codigos);
    }
}