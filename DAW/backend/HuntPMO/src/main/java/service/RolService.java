package service;

import HuntPMODAO.RolDAO;
import HuntPMOVO.RolVO;
import config.Conexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RolService {

        private RolDAO daoRol = new RolDAO();

        public void listarRoles() {
            List<RolVO> roles = new ArrayList<>();

            try (Connection con = Conexion.getConexion()) {
                roles = daoRol.obtenerRoles(con);

                if (roles.isEmpty()) {
                    System.out.println("Lista vacia");
                } else {
                    for (RolVO rol : roles) {
                        System.out.println(rol);
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public boolean crearRol(RolVO rol) {
            try (Connection con = Conexion.getConexion()) {
                RolVO rolCreado = daoRol.crearRol(con, rol);
                return rolCreado != null;

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

    public RolVO crearRolRevision(String detalles, int agenteId) {
        RolVO rol = new RolVO(
                "R",
                detalles,
                java.time.LocalDateTime.now(),
                java.time.LocalDateTime.now(),
                agenteId
        );

        try (Connection con = Conexion.getConexion()) {
            return daoRol.crearRol(con, rol);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
