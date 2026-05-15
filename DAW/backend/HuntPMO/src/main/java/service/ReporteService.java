package service;


import HuntPMODAO.ReportaAnomaliaDAO;
import HuntPMODAO.ReporteDAO;
import HuntPMODAO.RolRevisionDAO;
import HuntPMOVO.ReportaAnomaliaVO;
import HuntPMOVO.ReporteVO;
import HuntPMOVO.RolRevisionVO;
import config.Conexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReporteService {
    private ReporteDAO daoReporte = new ReporteDAO();
    private RolRevisionDAO daoRolRevision = new RolRevisionDAO();
    private ReportaAnomaliaDAO daoReportaAnomalia = new ReportaAnomaliaDAO();

    public void listarReportes() {
        List<ReporteVO> reportes = new ArrayList<>();

        try (Connection con = Conexion.getConexion()) {
            reportes = daoReporte.obtenerReportes(con);

            if (reportes.isEmpty()) {
                System.out.println("Lista vacia");
            } else {
                for (ReporteVO reporte : reportes) {
                    System.out.println(reporte);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listarReportesPorUsuario(int usuarioId) {
        try (Connection con = Conexion.getConexion()) {
            List<ReporteVO> reportes = daoReporte.obtenerReportesPorUsuario(con, usuarioId);

            if (reportes.isEmpty()) {
                System.out.println("Lista vacia");
            } else {
                for (ReporteVO reporte : reportes) {
                    System.out.println(reporte);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listarReportesConAnomalias() {
        List<ReporteVO> reportes = new ArrayList<>();

        try (Connection con = Conexion.getConexion()) {

            reportes = daoReporte.obtenerReportesConAnomalias(con);

            if (reportes.isEmpty()) {
                System.out.println("Lista vacia");
            } else {
                for (ReporteVO reporte : reportes) {
                    System.out.println(reporte);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void listarReportesSinAnomalias() {
        List<ReporteVO> reportes = new ArrayList<>();

        try (Connection con = Conexion.getConexion()) {

            reportes = daoReporte.obtenerReportesSinAnomalias(con);

            if (reportes.isEmpty()) {
                System.out.println("Lista vacia");
            } else {
                for (ReporteVO reporte : reportes) {
                    System.out.println(reporte);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean crearReporte(ReporteVO reporte) {
        try (Connection con = Conexion.getConexion()) {
            ReporteVO creado = daoReporte.crearReporte(con, reporte);
            return creado != null;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void listarRolesRevision() {
        List<RolRevisionVO> rolesRevision = new ArrayList<>();

        try (Connection con = Conexion.getConexion()) {
            rolesRevision = daoRolRevision.obtenerRolesRevision(con);

            if (rolesRevision.isEmpty()) {
                System.out.println("Lista vacia");
            } else {
                for (RolRevisionVO rolRevision : rolesRevision) {
                    System.out.println(rolRevision);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean crearRolRevision(RolRevisionVO rolRevision) {
        try (Connection con = Conexion.getConexion()) {
            con.setAutoCommit(false);

            daoRolRevision.crearRolRevision(con, rolRevision);
            daoReporte.actualizarEstadoReporteDesdeRolRevision(con, rolRevision);

            con.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void listarReportaAnomalias() {
        List<ReportaAnomaliaVO> reportaAnomalias = new ArrayList<>();

        try (Connection con = Conexion.getConexion()) {
            reportaAnomalias = daoReportaAnomalia.obtenerReportaAnomalias(con);

            if (reportaAnomalias.isEmpty()) {
                System.out.println("Lista vacia");
            } else {
                for (ReportaAnomaliaVO reportaAnomalia : reportaAnomalias) {
                    System.out.println(reportaAnomalia);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean crearReportaAnomalia(ReportaAnomaliaVO reportaAnomalia) {
        try (Connection con = Conexion.getConexion()) {
            return daoReportaAnomalia.crearReportaAnomalia(con, reportaAnomalia);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
