package service;

import HuntPMODAO.AnomaliaDAO;
import HuntPMODAO.RolAsignacionDAO;
import HuntPMODAO.RolDAO;
import HuntPMOVO.AnomaliaVO;
import HuntPMOVO.RolAsignacionVO;
import HuntPMOVO.RolVO;
import config.Conexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AnomaliaService {
    private AnomaliaDAO daoAnomalia = new AnomaliaDAO();
    private RolAsignacionDAO daoRolAsignacion = new RolAsignacionDAO();
    private RolDAO daoRol = new RolDAO();

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

    public boolean crearAnomalia(AnomaliaVO anomalia) {
        try (Connection con = Conexion.getConexion()) {
            AnomaliaVO creada = daoAnomalia.crearAnomalia(con, anomalia);
            return creada != null;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public void listarAnomaliasPorPeligrosidad(int peligrosidad) {
        List<AnomaliaVO> anomalias = new ArrayList<>();

        try (Connection con = Conexion.getConexion()) {
            anomalias = daoAnomalia.obtenerAnomaliasPorPeligrosidad(con, peligrosidad);

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

    public void listarAnomaliasPorTipo(String tipo) {
        List<AnomaliaVO> anomalias = new ArrayList<>();

        try (Connection con = Conexion.getConexion()) {
            anomalias = daoAnomalia.obtenerAnomaliasPorTipo(con, tipo);

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

    public RolVO iniciarAsignacionAnomalia(int anomaliaId, String detalles, LocalDateTime fechaFinalizacion, int agenteId) {
        try (Connection con = Conexion.getConexion()) {
            con.setAutoCommit(false);

            RolVO rol = new RolVO(
                    "A",
                    detalles,
                    LocalDateTime.now(),
                    fechaFinalizacion,
                    agenteId
            );

            RolVO rolCreado = daoRol.crearRol(con, rol);

            if (rolCreado == null) {
                con.rollback();
                return null;
            }

            String descripcionActual = daoAnomalia.obtenerDescripcionAnomalia(con, anomaliaId);

            if (descripcionActual == null) {
                con.rollback();
                return null;
            }

            RolAsignacionVO rolAsignacion = new RolAsignacionVO(
                    descripcionActual,
                    anomaliaId,
                    rolCreado.getRolId()
            );

            daoRolAsignacion.crearRolAsignacion(con, rolAsignacion);

            con.commit();
            return rolCreado;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean finalizarAsignacionAnomalia(int anomaliaId, int rolId, String textoAnyadido) {
        try (Connection con = Conexion.getConexion()) {
            con.setAutoCommit(false);

            String descripcionActual = daoAnomalia.obtenerDescripcionAnomalia(con, anomaliaId);

            if (descripcionActual == null) {
                con.rollback();
                return false;
            }

            String nuevaDescripcion = descripcionActual + "\n" + textoAnyadido;

            RolAsignacionVO rolAsignacion = new RolAsignacionVO(
                    nuevaDescripcion,
                    anomaliaId,
                    rolId
            );

            daoRolAsignacion.actualizarNuevaDescripcion(con, rolAsignacion);
            daoRolAsignacion.actualizarDescripcionAnomaliaDesdeRolAsignacion(con, rolAsignacion);
            daoRol.finalizarRol(con, rolId, LocalDateTime.now());

            con.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void listarMisAnomaliasPendientes(int agenteId) {
        List<AnomaliaVO> anomalias = new ArrayList<>();

        try (Connection con = Conexion.getConexion()) {
            anomalias = daoAnomalia.obtenerAnomaliasPendientesPorAgente(con, agenteId);

            if (anomalias.isEmpty()) {
                System.out.println("No tienes anomalias pendientes.");
            } else {
                for (AnomaliaVO anomalia : anomalias) {
                    System.out.println(anomalia);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
