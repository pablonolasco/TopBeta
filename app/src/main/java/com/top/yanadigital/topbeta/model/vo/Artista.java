package com.top.yanadigital.topbeta.model.vo;

import android.os.Build;

import java.util.Objects;

public class Artista {
    public static final String ORDES = "orden";
    private long id;
    private String nombre;
    private String apellidos;
    private long fechaNacimiento;
    private String lugarNacimiento;
    private short estatura;
    private String notas;
    private int orden;
    private String fotoURL;

    public Artista() {
    }

    public Artista(long id, String nombre, String apellidos, long fechaNacimiento,
                   String lugarNacimiento, short estatura, String notas, int orden, String fotoURL) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.lugarNacimiento = lugarNacimiento;
        this.estatura = estatura;
        this.notas = notas;
        this.orden = orden;
        this.fotoURL = fotoURL;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public long getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(long fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public short getEstatura() {
        return estatura;
    }

    public void setEstatura(short estatura) {
        this.estatura = estatura;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getFotoURL() {
        return fotoURL;
    }

    public void setFotoURL(String fotoURL) {
        this.fotoURL = fotoURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artista artista = (Artista) o;
        return id == artista.id;
    }

    @Override
    public int hashCode() {

        int result= 0;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
               return Objects.hash(id);

            }
           if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN){
                result=31*result+(int)id;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return  result;

    }

    public String getNombreCompleto() {
        return this.nombre+" "+this.apellidos;
    }
}
