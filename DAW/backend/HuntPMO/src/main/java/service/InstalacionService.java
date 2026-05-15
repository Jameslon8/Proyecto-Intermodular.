package service;

import HuntPMODAO.InstalacionDAO;
import HuntPMOVO.InstalacionVO;
import config.Conexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstalacionService {
    private InstalacionDAO daoInstalacion = new InstalacionDAO();

    public void listarInstalaciones() {
        List<InstalacionVO> instalaciones = new ArrayList<>();

        try (Connection con = Conexion.getConexion()) {
            instalaciones = daoInstalacion.obtenerInstalaciones(con);

            if (instalaciones.isEmpty()) {
                System.out.println("Lista vacia");
            } else {
                for (InstalacionVO instalacion : instalaciones) {
                    System.out.println(instalacion);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean crearInstalacion(InstalacionVO instalacion) {
        try (Connection con = Conexion.getConexion()) {
            InstalacionVO creada = daoInstalacion.crearInstalacion(con, instalacion);
            return creada != null;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
