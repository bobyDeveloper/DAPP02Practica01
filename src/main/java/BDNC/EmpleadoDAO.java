package BDNC;

import java.sql.SQLException;

public interface EmpleadoDAO {
    void agregarEmpleado(int clave, String nombre, String direccion, String telefono) throws SQLException;
    void listarEmpleados() throws SQLException;
    void actualizarEmpleado(int clave, String nombre, String direccion, String telefono) throws SQLException;
    void eliminarEmpleado(int clave) throws SQLException;
}