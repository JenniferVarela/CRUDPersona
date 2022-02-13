package com.example.crudpersona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.crudpersona.Clases.Personas;
import com.example.crudpersona.Configuracion.SQLiteConexion;
import com.example.crudpersona.Configuracion.Transacciones;

import java.util.ArrayList;

public class ActivityListar extends AppCompatActivity {

    SQLiteConexion conexion;
    ListView lista;
    ArrayList<Personas> listaPersonas;
    ArrayList<String> ArregloPersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        conexion = new SQLiteConexion(this, Transacciones.NameDatabase,null,1);
        lista=(ListView) findViewById(R.id.listar);

        ObtenerListaPersonas();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,ArregloPersonas);
        lista.setAdapter(adp);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ObtenerPersona(i);
            }
        });

    }
    private void ObtenerListaPersonas() {
        SQLiteDatabase db = conexion.getReadableDatabase();

        Personas list_per = null;
        listaPersonas = new ArrayList<Personas>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ Transacciones.tblPersonas,null);

        while(cursor.moveToNext())
        {
            list_per = new Personas();
            list_per.setId(cursor.getInt(0));
            list_per.setNombres(cursor.getString(1));
            list_per.setApellidos(cursor.getString(2));
            list_per.setEdad(cursor.getInt(3));
            list_per.setCorreo(cursor.getString(4));
            list_per.setDireccion(cursor.getString(5));

            listaPersonas.add(list_per);
        }
        cursor.close();
        llenarlista();

    }

    private void llenarlista() {
        ArregloPersonas = new ArrayList<String>();

        for (int i=0; i<listaPersonas.size();i++)
        {
            ArregloPersonas.add(listaPersonas.get(i).getId()+" | "+
            listaPersonas.get(i).getNombres() + " | "+
                    listaPersonas.get(i).getApellidos() +" | "+ listaPersonas.get(i).getEdad()+ " | "+
                    listaPersonas.get(i).getCorreo()+" | "+ listaPersonas.get(i).getDireccion());
        }
    }

    private void ObtenerPersona( int id) {
        Personas personas = listaPersonas.get(id);

        Intent intent = new Intent(getApplicationContext(),ActivityEdit.class);

        intent.putExtra("codigo", personas.getId()+"");
        intent.putExtra("nombres",personas.getNombres());
        intent.putExtra("apellidos",personas.getApellidos());
        intent.putExtra("edad",personas.getEdad()+"");
        intent.putExtra("correo", personas.getCorreo());
        intent.putExtra("direccion",personas.getDireccion());

        startActivity(intent);
    }

}