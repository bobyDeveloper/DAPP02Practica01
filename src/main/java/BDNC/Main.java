package BDNC;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        EmpleadoDAO empleadoDAO = new Empleado();
        //empleadoDAO.agregarEmpleado(1, "bobyDeveloper", "Orizaba", "2721276929");
        empleadoDAO.listarEmpleados();
        //empleadoDAO.actualizarEmpleado(3, "Jazael Omar", "Huatusco", "2721276929");
        //empleadoDAO.eliminarEmpleado(1);
    }
}
