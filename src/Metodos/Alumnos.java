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
            String apellidos,
            String nombretut,
            String apellidostut,
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
        String SSQL = "INSERT INTO alumnos (nombres, apellidos, nombretut, apellidostut, direccion, colonia, telefono, celular, correo, escuela, grado, turno, materia, comentarios, fecha) " //Aqui se agingan los comandos SQL para la tabla
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 

        try {
            //este metodo es que va almacenar los registros de la frame de cuentas, se debe de asignar cada una de las variables, tanto aqui.
            //como en la base de datos.

            con = metodospool.dataSource.getConnection();

            try (PreparedStatement psql = con.prepareStatement(SSQL) //Agregar elementos tanto aqui como en la base de datos
                    ) {
                psql.setString(1, nombres);
                psql.setString(2, apellidos);
                psql.setString(3, nombretut);
                psql.setString(4, apellidostut);
                psql.setString(5, direccion);
                psql.setString(6, colonia);
                psql.setString(7, telefono);
                psql.setString(8, celular);
                psql.setString(9, correo);
                psql.setString(10, escuela);
                psql.setString(11, grado);
                psql.setString(12, turno);
                psql.setString(13, materia);
                psql.setString(14, comentarios);
                psql.setString(15, fecha);

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
