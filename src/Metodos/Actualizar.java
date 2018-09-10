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
public class Actualizar {

    Pool metodospool = new Pool();

    public void Modificar(String nombres,
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
            String fecha,
            String idalumnos) {

        int confirmar = JOptionPane.showConfirmDialog(null, "¿Desea modificar los datos actuales?");

        if (confirmar == JOptionPane.YES_OPTION) {

            Connection conexion = null;

            try {

                conexion = metodospool.dataSource.getConnection();

                String Ssql = "UPDATE alumnos SET "
                        + "nombres=?, "
                        + "apellidop=?, "
                        + "apellidom=?,"
                        + "nombretut=?, "
                        + "apellidoPtut=?, "
                        + "apellidoMtut=?, "
                        + "nombretut2=?, "
                        + "apellidoPtut2=?,"
                        + "apellidoMtut2=?,"
                        + "direccion=?,"
                        + "colonia=?,"
                        + "telefono=?,"
                        + "celular=?,"
                        + "correo=?,"
                        + "escuela=?,"
                        + " grado=?,"
                        + " turno=?,"
                        + " materia=?,"
                        + " comentarios=?,"
                        + " fecha=? "
                        + "WHERE idalumnos=?";

                PreparedStatement prest = conexion.prepareStatement(Ssql);

                prest.setString(1, nombres);
                prest.setString(2, apellidoP);
                prest.setString(3, apellidoM);
                prest.setString(4, nombretut);
                prest.setString(5, apellidoPtut);
                prest.setString(6, apellioMtut);
                prest.setString(7, nombretut2);
                prest.setString(8, apellidoPtut2);
                prest.setString(9, apellidoMtut2);
                prest.setString(10, direccion);
                prest.setString(11, colonia);
                prest.setString(12, telefono);
                prest.setString(13, celular);
                prest.setString(14, correo);
                prest.setString(15, escuela);
                prest.setString(16, grado);
                prest.setString(17, turno);
                prest.setString(18, materia);
                prest.setString(19, comentarios);
                prest.setString(20, fecha);
                prest.setString(21, idalumnos);

                if (prest.executeUpdate() > 0) {

                    JOptionPane.showMessageDialog(null, "Los datos han sido modificados con éxito", "Operación Exitosa",
                            JOptionPane.INFORMATION_MESSAGE);

                } else {

                    JOptionPane.showMessageDialog(null, "No se ha podido realizar la actualización de los datos\n"
                            + "Inténtelo nuevamente.", "Error en la operación",
                            JOptionPane.ERROR_MESSAGE);

                }

            } catch (SQLException e) {

                JOptionPane.showMessageDialog(null, "No se ha podido realizar la actualización de los datos\n"
                        + "Inténtelo nuevamente.\n"
                        + "Error: " + e, "Error en la operación",
                        JOptionPane.ERROR_MESSAGE);

            } finally {

                if (conexion != null) {

                    try {

                        conexion.close();

                    } catch (SQLException e) {

                        JOptionPane.showMessageDialog(null, "Error al intentar cerrar la conexión."
                                + "Error: " + e, "Error en la operación",
                                JOptionPane.ERROR_MESSAGE);

                    }

                }

            }

        }

    }

}
