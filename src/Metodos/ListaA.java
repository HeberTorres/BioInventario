/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Heber
 */
public class ListaA {
     Pool metodospool = new Pool();
    DefaultTableModel ModeloTabla;

    public void Buscar(String valor, String filtro, JTable tablacontactos) {
        //Asignacion de los nombres de las columnas
        String[] columnas = {"ID", "Nombres", "Apellidos", "Nombre Tutor", "Apellido Tutor", "Direccion", "Colonia", "Telefono", "Celular",
        "Correo", "Escuela", "Grado", "Turno", "Materia", "Comentarios", "Fecha"};
        String[] registro = new String[16]; //arreglo de c/u de las columnas
        ModeloTabla = new DefaultTableModel(null, columnas);
        String SSQL;
        Connection conect = null;

        switch (filtro) {
            case "Todos":
                SSQL = "SELECT * FROM mydb.alumnos";
                break;
            case "Nombre":
                SSQL = "SELECT idalumnos, nombres, apellidos, nombretut, apellidostut, direccion, colonia, telefono, celular, correo, escuela, grado, turno, materia, comentarios, fecha "
                        + "FROM alumnos WHERE nombres LIKE '%" + valor + "%'";
                break;
            case "Apellido":
                SSQL = "SELECT idalumnos, nombres, apellidos, nombretut, apellidostut, direccion, colonia, telefono, celular, correo, escuela, grado, turno, materia, comentarios, fecha"
                        + "FROM alumnos WHERE apellidos LIKE '%" + valor + "%'";
                break;
            case "Materia":
                SSQL = "SELECT idalumnos, nombres, apellidos, nombretut, apellidostut, direccion, colonia, telefono, celular, correo, escuela, grado, turno, materia, comentarios, fecha "
                        + "FROM alumnos WHERE materia LIKE '%" + valor + "%'";
                break;
            default:
                SSQL = "SELECT idalumnos, nombres, apellidos, nombretut, apellidostut, direccion, colonia, telefono, celular, correo, escuela, grado, turno, materia, comentarios, fecha "
                        + "FROM alumnos WHERE fecha LIKE '%" + valor + "%'";
                break;
            case "Fecha":
                SSQL = "SELECT idalumnos, nombres, apellidos, nombretut, apellidostut, direccion, colonia, telefono, celular, correo, escuela, grado, turno, materia, comentarios, fecha"
                        + "FROM alumnos WHERE fecha LIKE '%" + valor + "%'";
                break;
                
        }

        try {

            conect = metodospool.dataSource.getConnection();
            PreparedStatement st = conect.prepareStatement(SSQL);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                registro[0] = rs.getString("idAlumnos");
                registro[1] = rs.getString("nombres");
                registro[2] = rs.getString("apellidos");
                registro[3] = rs.getString("nombretut");
                registro[4] = rs.getString("apellidostut");
                registro[5] = rs.getString("direccion");
                registro[6] = rs.getString("colonia");
                registro[7] = rs.getString("telefono");
                registro[8] = rs.getString("celular");
                registro[9] = rs.getString("correo");
                registro[10] = rs.getString("escuela");
                registro[11] = rs.getString("grado");
                registro[12] = rs.getString("turno");
                registro[13] = rs.getString("materia");
                registro[14] = rs.getString("comentarios");
                registro[15] = rs.getString("fecha");
                
                
                
                
               

                ModeloTabla.addRow(registro);

            }

            tablacontactos.setModel(ModeloTabla);

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e, "Error durante el procedimiento", JOptionPane.ERROR_MESSAGE);

        } finally {

            if (conect != null) {

                try {

                    conect.close();

                } catch (SQLException ex) {

                    JOptionPane.showMessageDialog(null, ex, "Error de desconexión", JOptionPane.ERROR_MESSAGE);

                }

            }

        }
    }
    
}
