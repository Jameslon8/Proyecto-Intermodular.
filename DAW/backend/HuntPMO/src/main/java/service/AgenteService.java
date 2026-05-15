package service;


import HuntPMODAO.AgenteDAO;
import HuntPMODAO.PersonaDAO;
import HuntPMOVO.AgenteVO;
import HuntPMOVO.PersonaVO;
import config.Conexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AgenteService {
    private AgenteDAO daoAgente = new AgenteDAO();
    private PersonaDAO daoPer = new PersonaDAO();

    public boolean registrarAgente(PersonaVO persona, AgenteVO agente) {
        try (Connection con = Conexion.getConexion()) {
            con.setAutoCommit(false);

            daoPer.insertarPersona(con, persona);
            daoAgente.crearAgente(con, agente);

            con.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void listarAgentes() {
        List<AgenteVO> agentes = new ArrayList<>();

        try (Connection con = Conexion.getConexion()) {
            agentes = daoAgente.obtenerAgentes(con);

            if (agentes.isEmpty()) {
                System.out.println("Lista vacia");
            } else {
                for (AgenteVO agente : agentes) {
                    System.out.println(agente);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean eliminarAgente(String mote) {
        try (Connection con = Conexion.getConexion()) {
            return daoAgente.eliminarAgente(con, mote);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
