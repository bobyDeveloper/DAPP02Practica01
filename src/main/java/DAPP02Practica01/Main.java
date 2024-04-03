package DAPP02Practica01;

import javax.swing.*;
import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        IDAO<Venta> dao = new DAOVenta();

        Object[] opciones = {"Registrar Venta", "Ver Ventas", "Editar Venta", "Eliminar Venta", "Salir"};
        String opcion;

        do {
            opcion = (String) JOptionPane.showInputDialog(null, "Seleccione una acción:",
                    "Menú Principal", JOptionPane.PLAIN_MESSAGE, null, opciones, "Registrar Venta");

            if ("Registrar Venta".equals(opcion)) {
                registrarVenta(dao);
            } else if ("Ver Ventas".equals(opcion)) {
                listarVentas(dao);
            } else if ("Editar Venta".equals(opcion)) {
                actualizarVenta(dao);
            } else if ("Eliminar Venta".equals(opcion)) {
                eliminarVenta(dao);
            } else if ("Salir".equals(opcion) || opcion == null) {
                JOptionPane.showMessageDialog(null, "Finalizando...");
                break;
            }
        } while (true);
    }

    private static void registrarVenta(IDAO<Venta> dao) {
        String cliente = JOptionPane.showInputDialog("Ingrese el nombre del Cliente:");
        String totalStr = JOptionPane.showInputDialog("Ingrese el Total de la venta:");
        double total = Double.parseDouble(totalStr);
        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.setFechaventa(new Date(new java.util.Date().getTime()));
        venta.setTotal(total);

        int ndetalles = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de productos de la venta:"));
        for (int i = 0; i < ndetalles; i++) {
            String nombreProducto = JOptionPane.showInputDialog("Ingrese el nombre del Producto " + (i + 1) + ":");
            double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del Producto:"));
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad del Producto:"));

            DetalleVenta dtv = new DetalleVenta();
            dtv.setProducto(nombreProducto);
            dtv.setPrecio(precio);
            dtv.setCantidad(cantidad);
            dtv.setVenta(venta);
            venta.getDetalleVenta().add(dtv);
        }

        boolean bandera = dao.guardar(venta);
        if (bandera) {
            JOptionPane.showMessageDialog(null, "Venta registrada con éxito.");
        } else {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al registrar la venta.");
        }
    }

   private static void listarVentas(IDAO<Venta> dao) {
    List<Venta> ventas = dao.buscarAll();
    StringBuilder ventasStr = new StringBuilder("Ventas registradas:\n");
    for (Venta venta : ventas) {
        ventasStr.append("\nID Venta: ").append(venta.getId())
                .append("\nCliente: ").append(venta.getCliente())
                .append("\nFecha: ").append(venta.getFechaventa().toString())
                .append("\nTotal: $").append(venta.getTotal()).append("\n");

        // Agregar detalles de cada venta
        List<DetalleVenta> detalles = venta.getDetalleVenta();
        if (detalles != null && !detalles.isEmpty()) {
            ventasStr.append("Detalles:\n");
            for (DetalleVenta detalle : detalles) {
                ventasStr.append("  Producto: ").append(detalle.getProducto())
                        .append(", Cantidad: ").append(detalle.getCantidad())
                        .append(", Precio: $").append(detalle.getPrecio()).append("\n");
            }
        } else {
            ventasStr.append("  No hay detalles para mostrar.\n");
        }
    }
    JOptionPane.showMessageDialog(null, ventasStr.toString());
}


    private static void actualizarVenta(IDAO<Venta> dao) {
        long idVenta = Long.parseLong(JOptionPane.showInputDialog("Ingrese el ID de la venta a actualizar:"));
        Venta venta = dao.buscarById(idVenta);
        if (venta != null) {
            String nuevoCliente = JOptionPane.showInputDialog("Ingrese el nuevo nombre del Cliente:", venta.getCliente());
            String nuevoTotalStr = JOptionPane.showInputDialog("Ingrese el nuevo Total de la venta:", String.valueOf(venta.getTotal()));
            double nuevoTotal = Double.parseDouble(nuevoTotalStr);
            venta.setCliente(nuevoCliente);
            venta.setTotal(nuevoTotal);

            boolean resultado = dao.modificar(venta);
            if (resultado) {
                JOptionPane.showMessageDialog(null, "Venta actualizada con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo actualizar la venta.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró la venta con ID: " + idVenta);
        }
    }

    private static void eliminarVenta(IDAO<Venta> dao) {
        long idVenta = Long.parseLong(JOptionPane.showInputDialog("Ingrese el ID de la venta a eliminar:"));
        Venta venta = dao.buscarById(idVenta);
        if (venta != null) {
            boolean resultado = dao.eliminar(venta);
            if (resultado) {
                JOptionPane.showMessageDialog(null, "Venta eliminada con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar la venta.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró la venta con ID: " + idVenta);
        }
    }
}