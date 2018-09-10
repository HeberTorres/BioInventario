/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Heber
 */
public class Alumnos {

    Pool metodospool = new Pool();

    public int Registro(
            String nombres,
            String apellidoP,
            String apellidoM,
            String nombretut,
            String apellidoPtut,
            String apellioMtut,
            String nombretut2,
            String apellidoPtut2,
            String apellidoMtut2,
            String direccion,
            String colonia,
            String telefono,
            String celular,
            String correo,
            String escuela,
            String grado,
            String turno,
            String materia,
            String comentarios,
            String fecha) {
        int resultado = 0;
        Connection con = null;
        String SSQL = "INSERT INTO alumnos ("
                + "nombres,"
                + "apellidop,"
                + "apellidom,"
                + "nombretut,"
                + "apellidoPtut,"
                + "apellidoMtut,"
                + "nombretut2,"
                + "apellidoPtut2,"
                + "apellidoMtut2,"
                + "direccion,"
                + "colonia,"
                + "telefono,"
                + "celular,"
                + "correo,"
                + "escuela,"
                + "grado,"
                + "turno,"
                + "materia,"
                + "comentarios,"
                + "fecha) " //Aqui se agingan los comandos SQL para la tabla
                + "VALUES ("
                + "?,"
                + " ?,"
                + " ?,"
                + " ?,"
                + " ?,"
                + " ?,"
                + " ?,"
                + " ?,"
                + " ?,"
                + " ?,"
                + " ?,"
                + " ?,"
                + " ?,"
                + " ?,"
                + " ?,"
                + " ?,"
                + " ?,"
                + " ?,"
                + " ?,"
                + " ?)";

        try {
            //este metodo es que va almacenar los registros de la frame de cuentas, se debe de asignar cada una de las variables, tanto aqui.
            //como en la base de datos.

            con = metodospool.dataSource.getConnection();

            try (PreparedStatement psql = con.prepareStatement(SSQL) //Agregar elementos tanto aqui como en la base de datos
                    ) {
                psql.setString(1, nombres);
                psql.setString(2, apellidoP);
                psql.setString(3, apellidoM);
                psql.setString(4, nombretut);
                psql.setString(5, apellidoPtut);
                psql.setString(6, apellioMtut);
                psql.setString(7, nombretut2);
                psql.setString(8, apellidoPtut2);
                psql.setString(9, apellidoMtut2);
                psql.setString(10, direccion);
                psql.setString(11, colonia);
                psql.setString(12, telefono);
                psql.setString(13, celular);
                psql.setString(14, correo);
                psql.setString(15, escuela);
                psql.setString(16, grado);
                psql.setString(17, turno);
                psql.setString(18, materia);
                psql.setString(19, comentarios);
                psql.setString(20, fecha);
                
                resultado = psql.executeUpdate();
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error al intentar almacenar la información:\n"
                    + e, "Error en la operación", JOptionPane.ERROR_MESSAGE);

        } finally {

            try {

                if (con != null) {

                    con.close();

                }

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, "Error al intentar cerrar la conexión:\n"
                        + ex, "Error en la operación", JOptionPane.ERROR_MESSAGE);

            }

        }

        return resultado;
    }
}
