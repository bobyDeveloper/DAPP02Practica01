package BDNC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Empleado implements EmpleadoDAO {
    private final Connection connection;

    public Empleado() throws SQLException {
        this.connection = ConexionBD.getInstance().getConnection();
    }

    @Override
    public void agregarEmpleado(int clave, String nombre, String direccion, String telefono) throws SQLException {
        String SQL = "INSERT INTO Empleados (Clave, Nombre, Direccion, Telefono) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(SQL);
        pstmt.setInt(1, clave);
        pstmt.setString(2, nombre);
        pstmt.setString(3, direccion);
        pstmt.setString(4, telefono);
        pstmt.executeUpdate();
    }

    @Override
    public void listarEmpleados() throws SQLException {
        String SQL = "SELECT * FROM Empleados";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        while (rs.next()) {
            System.out.println(rs.getInt("Clave") + "\t" +
                               rs.getString("Nombre") + "\t" +
                               rs.getString("Direccion") + "\t" +
                               rs.getString("Telefono"));
        }
    }

    @Override
    public void actualizarEmpleado(int clave, String nombre, String direccion, String telefono) throws SQLException {
        String SQL = "UPDATE Empleados SET Nombre = ?, Direccion = ?, Telefono = ? WHERE Clave = ?";
        PreparedStatement pstmt = connection.prepareStatement(SQL);
        pstmt.setString(1, nombre);
        pstmt.setString(2, direccion);
        pstmt.setString(3, telefono);
        pstmt.setInt(4, clave);
        pstmt.executeUpdate();
    }

    @Override
    public void eliminarEmpleado(int clave) throws SQLException {
        String SQL = "DELETE FROM Empleados WHERE Clave = ?";
        PreparedStatement pstmt = connection.prepareStatement(SQL);
        pstmt.setInt(1, clave);
        pstmt.executeUpdate();
    }
}