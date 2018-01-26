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
//Filtro y busqueda
/**
 *
 * @author Heber
 */
public class Filtro {
     Pool metodospool = new Pool();
    DefaultTableModel ModeloTabla;

    public void Buscar(String valor, String filtro, JTable tablacontactos) {
        //Asignacion de los nombres de las columnas
        String[] columnas = {"ID", "Nombres", "Apellidos", "Materia", "Horas", "Costo", "Pago", "Adeudo", "Fecha"};
        String[] registro = new String[9]; //arreglo de c/u de las columnas
        ModeloTabla = new DefaultTableModel(null, columnas);
        String SSQL;
        Connection conect = null;

        switch (filtro) {
            case "Todos":
                SSQL = "SELECT * FROM mydb.cuenta";
                break;
            case "Nombres":
                SSQL = "SELECT idCuentas, nombres, apellidos, materia, horas, costo, pago, adeudo, fecha "
                        + "FROM cuenta WHERE nombres LIKE '%" + valor + "%'";
                break;
            case "Apellidos":
                SSQL = "SELECT idCuentas, nombres, apellidos, materia, horas, costo, pago, adeudo, fecha "
                        + "FROM cuenta WHERE apellidos LIKE '%" + valor + "%'";
                break;
            case "Materia":
                SSQL = "SELECT idCuentas, nombres, apellidos, materia, horas, costo, pago, adeudo, fecha "
                        + "FROM cuenta WHERE materia LIKE '%" + valor + "%'";
                break;
            default:
                SSQL = "SELECT idCuentas, nombres, apellidos, materia, horas, costo, pago, adeudo, fecha "
                        + "FROM cuenta WHERE fecha LIKE '%" + valor + "%'";
                break;
            case "Fecha":
                SSQL = "SELECT idCuentas, nombres, apellidos, materia, horas, costo, pago, adeudo, fecha "
                        + "FROM cuenta WHERE fecha LIKE '%" + valor + "%'";
                break;
                
        }

        try {

            conect = metodospool.dataSource.getConnection();
            PreparedStatement st = conect.prepareStatement(SSQL);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                registro[0] = rs.getString("idCuentas");
                registro[1] = rs.getString("nombres");
                registro[2] = rs.getString("apellidos");
                registro[3] = rs.getString("materia");
                registro[4] = rs.getString("horas");
                registro[5] = rs.getString("costo");
                registro[6] = rs.getString("pago");
                registro[7] = rs.getString("adeudo");
                registro[8] = rs.getString("fecha");

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
