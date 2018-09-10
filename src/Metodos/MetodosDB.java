
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
public class MetodosDB {

    //REGISTRO DE DATOS
    Pool metodospool = new Pool();

    public int Guardar(
            String nombres,
            String apellidos,
            String materia,
            double horas,
            double costo,
            double pago,
            double adeudo,
            String fecha) {
        int resultado = 0;
        Connection con = null;
        String SSQL = "INSERT INTO cuenta (nombres, apellidos, materia, horas, costo, pago, adeudo, fecha) " //Aqui se agingan los comandos SQL para la tabla
                //La tabla tiene que existir en la base de datos, y cada uno de sus valores, (filas y columnas)
                + "VALUES (?, ?, ?, ?, ?, ? ,?, ?)";  //Arreglo de SQL

        try {
            //este metodo es que va almacenar los registros de la frame de cuentas, se debe de asignar cada una de las variables, tanto aqui.
            //como en la base de datos.
            con = metodospool.dataSource.getConnection();

            try (PreparedStatement psql = con.prepareStatement(SSQL) //Agregar elementos tanto aqui como en la base de datos
                    ) {
                psql.setString(1, nombres);
                psql.setString(2, apellidos);
                psql.setString(3, materia);
                psql.setDouble(4, horas);
                psql.setDouble(5, costo);
                psql.setDouble(6, pago);
                psql.setDouble(7, adeudo);
                psql.setString(8, fecha);

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
