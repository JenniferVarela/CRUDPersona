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

public class MainActivity extends AppCompatActivity {
    EditText txtnombres,txtapellidos,txtedad,txtcorreo,txtdireccion;
    Button btnguardar,btnListar;

    /*Bloqueo el boton retroceder del telefono*/
    @Override public void onBackPressed(){};
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtnombres = (EditText) findViewById(R.id.txtNombres);
        txtapellidos = (EditText) findViewById(R.id.txtApellidos);
        txtedad = (EditText) findViewById(R.id.txtEdad);
        txtcorreo = (EditText) findViewById(R.id.txtCorreo);
        txtdireccion = (EditText) findViewById(R.id.txtDireccion);
        btnguardar = (Button) findViewById(R.id.btnguardar);

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgregarPersona();
            }
        });

        btnListar = (Button) findViewById(R.id.btnListar);

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ActivityListar.class);
                startActivity(intent);
            }
        });
    }

    private void AgregarPersona() {

        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase,null,1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombres,txtnombres.getText().toString());
        valores.put(Transacciones.apellidos,txtapellidos.getText().toString());
        valores.put(Transacciones.edad,txtedad.getText().toString());
        valores.put(Transacciones.correo,txtcorreo.getText().toString());
        valores.put(Transacciones.direccion,txtdireccion.getText().toString());

        Long resultado = db.insert(Transacciones.tblPersonas,Transacciones.id,valores);

        Toast.makeText(getApplicationContext(),"Registro exitoso..!!"+resultado,Toast.LENGTH_LONG).show();

        db.close();

        limpiarPantalla();


    }

    private void limpiarPantalla() {
        txtnombres.setText("");
        txtapellidos.setText("");
        txtedad.setText("");
        txtcorreo.setText("");
        txtdireccion.setText("");
    }
}