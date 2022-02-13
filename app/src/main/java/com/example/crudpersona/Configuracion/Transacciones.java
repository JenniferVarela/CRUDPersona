package com.example.crudpersona.Configuracion;

public class Transacciones {

    public static final String NameDatabase = "PM01DB";

    public static String tblPersonas = "tblpersonas";
    public static final String id="id";
    public static final String nombres="nombres";
    public static final String apellidos="apellidos";
    public static final String edad= "edad";
    public static final String correo="correo";
    public static final String direccion = "direccion";

    public static final String CreateTablePersonas = "CREATE TABLE " + tblPersonas + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "nombres TEXT,apellidos TEXT,edad INTEGER,correo TEXT, direccion TEXT)";

    public static final String DropTablePersonas = "DROP TABLE IF EXISTS "+tblPersonas;
}
