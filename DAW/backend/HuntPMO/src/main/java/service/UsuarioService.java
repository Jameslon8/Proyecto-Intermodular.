package service;

import HuntPMODAO.UsuarioDAO;
import HuntPMOVO.UsuarioVO;
import config.Conexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService{
    private UsuarioDAO daoUser = new UsuarioDAO();

    List<UsuarioVO> usuarios = new ArrayList<>();

    public void mostrarUsuarios() {
        try {
            Connection con = Conexion.getConexion();

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

    public void registrarUsuario() {


    }
}
