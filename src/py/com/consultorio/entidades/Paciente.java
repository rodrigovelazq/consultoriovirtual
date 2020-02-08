/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.consultorio.entidades;

import java.util.Date;

/**
 *
 * @author usuario
 */
public class Paciente {
 
    private int id;
    private String nombre;
    private String apellido; 
    private String cedula;
    private Date fechaNacimiento;
 
    public Paciente(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellido = apellidos;
    }

    public Paciente(String nombre, String apellido, String cedula, Date fechaNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.fechaNacimiento = fechaNacimiento;
    }

    
    
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getNombre() {
        return nombre;
    }
 
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
 
    public String getApellido() {
        return apellido;
    }
 
    public void setApellido(String apellidos) {
        this.apellido = apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return this.cedula;
    }
 
}
