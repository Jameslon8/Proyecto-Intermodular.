package service;

import HuntPMODAO.AnomaliaDAO;
import HuntPMODAO.RolAsignacionDAO;
import HuntPMOVO.AnomaliaVO;
import HuntPMOVO.RolAsignacionVO;
import config.Conexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnomaliaService {
    private AnomaliaDAO daoAnomalia = new AnomaliaDAO();
    private RolAsignacionDAO daoRolAsignacion = new RolAsignacionDAO();

    public void listarAnomalias() {
        List<AnomaliaVO> anomalias = new ArrayList<>();

        try (Connection con = Conexion.getConexion()) {
            anomalias = daoAnomalia.obtenerAnomalias(con);

            if (anomalias.isEmpty()) {
                System.out.println("Lista vacia");
            } else {
                for (AnomaliaVO anomalia : anomalias) {
                    System.out.println(anomalia);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listarAnomaliasPorInstalacion(int instalacionId) {
        List<AnomaliaVO> anomalias = new ArrayList<>();

        try (Connection con = Conexion.getConexion()) {
            anomalias = daoAnomalia.obtenerAnomaliasPorInstalacion(con, instalacionId);

            if (anomalias.isEmpty()) {
                System.out.println("Lista vacia");
            } else {
                for (AnomaliaVO anomalia : anomalias) {
                    System.out.println(anomalia);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listarRolesAsignacion() {
        List<RolAsignacionVO> rolesAsignacion = new ArrayList<>();

        try (Connection con = Conexion.getConexion()) {
            rolesAsignacion = daoRolAsignacion.obtenerRolesAsignacion(con);

            if (rolesAsignacion.isEmpty()) {
                System.out.println("Lista vacia");
            } else {
                for (RolAsignacionVO rolAsignacion : rolesAsignacion) {
                    System.out.println(rolAsignacion);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean crearRolAsignacion(RolAsignacionVO rolAsignacion) {
        try (Connection con = Conexion.getConexion()) {
            return daoRolAsignacion.crearRolAsignacion(con, rolAsignacion);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarDescripcionAnomalia(RolAsignacionVO rolAsignacion) {
        try (Connection con = Conexion.getConexion()) {
            return daoRolAsignacion.actualizarDescripcionAnomaliaDesdeRolAsignacion(con, rolAsignacion);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
