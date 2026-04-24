package dao;

import HuntPMOVO.AgenteVO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class AgenteDAO {

    public List<AgenteVO>  obtenerAgente(Connection conexion){
        List<AgenteVO> agente=new ArrayList<>();

        String consulta = "Select * from Agente";
    }
}
