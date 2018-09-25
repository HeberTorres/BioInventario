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

    public void Buscar(String valor, String filtro, JTable tblAlumnos) {

        //Asignacion de los nombres de las columnas
        String[] columnas = {
            "ID",
            "Nombres",
            "ApellidoP",
            "ApellidoM",
            "NombreTut",
            "ApellidoPTut",
            "ApellidoMTut",
            "Nombretut2",
            "ApellidoPTut2",
            "ApellidoMTut2",
            "Direccion",
            "Colonia",
            "Telefono",
            "Celular",
            "Correo",
            "Escuela",
            "Grado",
            "Turno",
            "Materia",
            "Comentarios",
            "Fecha"};

        String[] registro = new String[21]; //arreglo de c/u de las columnas
        ModeloTabla = new DefaultTableModel(null, columnas);

        {

            String SSQL;
            Connection conect = null;

            switch (filtro) {
                case "Todos":
                    SSQL = "SELECT * FROM mydb.alumnos";
                    break;
                case "Nombre":
                    SSQL = "SELECT idalumnos, nombres, apellidop, apellidom, nombretut, apellidoPtut, apellidoMtut, nombretut2, apellidoPtut2, apellidoMtut2, direccion, colonia, telefono, celular, correo, escuela, grado, turno, materia, comentarios, fecha "
                            + "FROM alumnos WHERE nombres LIKE '%" + valor + "%'";
                    break;
                case "Apellido Paterno":
                    SSQL = "SELECT idalumnos, nombres, apellidop, apellidom, nombretut, apellidoPtut, apellidoMtut, nombretut2, apellidoPtut2, apellidoMtut2, direccion, colonia, telefono, celular, correo, escuela, grado, turno, materia, comentarios, fecha "
                            + "FROM alumnos WHERE apellidoP LIKE '%" + valor + "%'";
                    break;
                case "Apellido Materno":
                    SSQL = "SELECT idalumnos, nombres, apellidop, apellidom, nombretut, apellidoPtut, apellidoMtut, nombretut2, apellidoPtut2, apellidoMtut2, direccion, colonia, telefono, celular, correo, escuela, grado, turno, materia, comentarios, fecha "
                            + "FROM alumnos WHERE apellidom LIKE '%" + valor + "%'";
                    break;
                case "Materia":
                    SSQL = "SELECT idalumnos, nombres, apellidop, apellidom, nombretut, apellidoPtut, apellidoMtut, nombretut2, apellidoPtut2, apellidoMtut2, direccion, colonia, telefono, celular, correo, escuela, grado, turno, materia, comentarios, fecha "
                            + "FROM alumnos WHERE materia LIKE '%" + valor + "%'";
                    break;
                default:
                    SSQL = "SELECT idalumnos, nombres, apellidop, apellidom, nombretut, apellidoPtut, apellidoMtut, nombretut2, apellidoPtut2, apellidoMtut2, direccion, colonia, telefono, celular, correo, escuela, grado, turno, materia, comentarios, fecha "
                            + "FROM alumnos WHERE fecha LIKE '%" + valor + "%'";
                    break;
                case "Fecha":
                    SSQL = "SELECT idalumnos, nombres, apellidop, apellidom, nombretut, apellidoPtut, apellidoMtut, nombretut2, apellidoPtut2, apellidoMtut2, direccion, colonia, telefono, celular, correo, escuela, grado, turno, materia, comentarios, fecha "
                            + "FROM alumnos WHERE fecha LIKE '%" + valor + "%'";
                    break;
                case "Turno":
                    SSQL = "SELECT idalumnos, nombres, apellidop, apellidom, nombretut, apellidoPtut, apellidoMtut, nombretut2, apellidoPtut2, apellidoMtut2, direccion, colonia, telefono, celular, correo, escuela, grado, turno, materia, comentarios, fecha "
                            + "FROM alumnos WHERE turno LIKE '%" + valor + "%'";
                    break;

            }

            try {

                conect = metodospool.dataSource.getConnection();
                PreparedStatement st = conect.prepareStatement(SSQL);
                ResultSet rs = st.executeQuery();

                while (rs.next()) {

                    registro[0] = rs.getString("idAlumnos");
                    registro[1] = rs.getString("nombres");
                    registro[2] = rs.getString("apellidop");
                    registro[3] = rs.getString("apellidom");
                    registro[4] = rs.getString("nombretut");
                    registro[5] = rs.getString("apellidoPtut");
                    registro[6] = rs.getString("apellidoMtut");
                    registro[7] = rs.getString("nombretut2");
                    registro[8] = rs.getString("apellidoPtut2");
                    registro[9] = rs.getString("apellidoMtut2");
                    registro[10] = rs.getString("direccion");
                    registro[11] = rs.getString("colonia");
                    registro[12] = rs.getString("telefono");
                    registro[13] = rs.getString("celular");
                    registro[14] = rs.getString("correo");
                    registro[15] = rs.getString("escuela");
                    registro[16] = rs.getString("grado");
                    registro[17] = rs.getString("turno");
                    registro[18] = rs.getString("materia");
                    registro[19] = rs.getString("comentarios");
                    registro[20] = rs.getString("fecha");

                    ModeloTabla.addRow(registro);
                }

                tblAlumnos.setModel(ModeloTabla);

               

                tblAlumnos.setColumnSelectionAllowed(false);
                tblAlumnos.setRowSelectionAllowed(false);

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
}
