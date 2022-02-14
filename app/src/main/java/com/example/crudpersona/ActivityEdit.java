package com.example.crudpersona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crudpersona.Configuracion.SQLiteConexion;
import com.example.crudpersona.Configuracion.Transacciones;

public class ActivityEdit extends AppCompatActivity {

    EditText aetxtnombres,aetxtapellidos,aetxtedad,aetxtcorreo,aetxtdireccion,aetxtcodigo;
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
        aetxtcodigo = (EditText) findViewById(R.id.aetxtCodigo);

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
        aetxtcodigo.setText(codigos);

        System.out.println(codigos);


        aebtnactualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActualizarPersona();
            }
        });

        aebtneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                EliminarPersona();
            }
        });
    }

    private void EliminarPersona() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase,null,1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        String ObtenerCodigo = aetxtcodigo.getText().toString();
        try {
            db.delete(Transacciones.tblPersonas,Transacciones.id +" = "+ ObtenerCodigo, null);
            db.close();
            Toast.makeText(getApplicationContext(),"Se elimino correctamente", Toast.LENGTH_SHORT).show();

        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"No se elimino", Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(getApplicationContext(),ActivityListar.class);
        startActivity(intent);
    }

    private void ActualizarPersona() {

        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase,null,1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        String ObtenerCodigo = aetxtcodigo.getText().toString();

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombres,aetxtnombres.getText().toString());
        valores.put(Transacciones.apellidos,aetxtapellidos.getText().toString());
        valores.put(Transacciones.edad,aetxtedad.getText().toString());
        valores.put(Transacciones.correo,aetxtcorreo.getText().toString());
        valores.put(Transacciones.direccion,aetxtdireccion.getText().toString());

        try {
            db.update(Transacciones.tblPersonas,valores, Transacciones.id +" = "+ ObtenerCodigo, null);
            db.close();
            Toast.makeText(getApplicationContext(),"Se actualizo correctamente", Toast.LENGTH_SHORT).show();

        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"No se actualizo", Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(getApplicationContext(),ActivityListar.class);
        startActivity(intent);

    }
}