/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.consultorio.controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import py.com.consultorio.entidades.Paciente;

/**
 *
 * @author usuario
 */
public class Conector {

    Connection c;

    public Conector() {
    }

    /*public static void main(String args[]){
        connect();
    }*/
    public void connect() {
        try {
            //Class.forName("org.sqlite.JDBC");
            Class.forName("org.h2.Driver");
            c = DriverManager.getConnection("jdbc:h2:" + System.getProperty("user.dir") + "/consultorio.db", "consultorio", "consultorio");
            //c = DriverManager.getConnection("jdbc:sqlite:consultorio.db");
            if (c != null) {
                System.out.println("Conectado");
            }
        } catch (Exception ex) {
            System.err.println("No se ha podido conectar a la base de datos\n" + ex.getMessage());
        }
    }

    public void close() {
        try {
            c.close();
        } catch (Exception ex) {
            System.err.println("No se ha podido cerrar la base de datos\n" + ex.getMessage());
        }
    }

    public void savePaciente(Paciente paciente) {
        try {
            PreparedStatement st = c.prepareStatement("insert into paciente (cedula, nombre, apellido, fecha_nacimiento, fecha_alta) values (?,?,?,?,?)");
            st.setString(1, paciente.getCedula());
            st.setString(2, paciente.getNombre());
            st.setString(3, paciente.getApellido());
            st.setString(4, paciente.getFechaNacimiento().toString());
            st.setString(5, "date('now')");
            st.execute();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public ResultSet getListPaciente(Map<String, String> filtro) {
        try {
            StringBuilder sb = new StringBuilder("select * from paciente where 1 = 1");
            if (filtro != null) {
                Iterator it = filtro.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    sb.append(" and " + pair.getKey() + " = " + pair.getValue());
                    it.remove();
                }
            }
            PreparedStatement st = c.prepareStatement(sb.toString());
            return st.executeQuery();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public ResultSet getListHistorial(Map<String, String> filtro) {
        try {
            StringBuilder sb = new StringBuilder("select * from historial where 1 = 1");
            if (filtro != null) {
                Iterator it = filtro.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    sb.append(" and " + pair.getKey() + " = " + pair.getValue());
                    it.remove();
                }
            }
            PreparedStatement st = c.prepareStatement(sb.toString());
            return st.executeQuery();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }
}
