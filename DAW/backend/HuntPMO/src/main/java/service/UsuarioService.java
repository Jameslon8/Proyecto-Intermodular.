package service;

import HuntPMODAO.PersonaDAO;
import HuntPMODAO.UsuarioDAO;
import HuntPMOVO.PersonaVO;
import HuntPMOVO.UsuarioVO;
import config.Conexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService{
    private UsuarioDAO daoUser = new UsuarioDAO();
    private PersonaDAO daoPer = new PersonaDAO();

    List<UsuarioVO> usuarios = new ArrayList<>();

    public boolean registrarUsuario(PersonaVO persona, UsuarioVO usuario) {
        try (Connection con = Conexion.getConexion()) {

            con.setAutoCommit(false);

            daoPer.insertarPersona(con, persona);
            daoUser.crearUsuario(con, usuario);

            con.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public void listarUsuarios() {

        List<UsuarioVO> usuarios = new ArrayList<>();

        try (Connection con = Conexion.getConexion()) {

            usuarios = daoUser.obtenerUsuarios(con);

            if (usuarios.isEmpty()) {
                System.out.println("Lista vacia");
            } else {
                for (UsuarioVO usuario : usuarios) {
                    System.out.println(usuario);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean eliminarUsuario(String nombreUser) {

        try (Connection con = Conexion.getConexion()) {

            return daoUser.eliminarUsuario(con, nombreUser);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public UsuarioVO verificarUsuario(String nombreUser, String contrasenya) {

        try (Connection con = Conexion.getConexion()) {

            return daoUser.verificarUsuario(con, nombreUser, contrasenya);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
