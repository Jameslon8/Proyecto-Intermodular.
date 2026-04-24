package HuntPMOVO;

public class AgenteVO extends PersonaVO {
    private static int agenteID;
    private String mote;
    private int rango;
    private String especialidad;
    private AgenteVO agente;


    public AgenteVO(String dni, String nombre, String prApellido, String sgApellido, String telefono,
                    String email, String NSS, String mote, int rango, String especialidad, AgenteVO agente) {
        super(dni, nombre, prApellido, sgApellido, telefono, email, NSS);
        this.mote = mote;
        this.rango = rango;
        this.especialidad = especialidad;
        this.agente = agente;
    }

    public AgenteVO(String dni, String nombre, String prApellido, String telefono, String email, String NSS, String mote, int rango, String especialidad, AgenteVO agente) {
        super(dni, nombre, prApellido, telefono, email, NSS);
        this.mote = mote;
        this.rango = rango;
        this.especialidad = especialidad;
        this.agente = agente;
    }

    public static int getAgenteID() {
        return agenteID;
    }

    public static void setAgenteID(int agenteID) {
        AgenteVO.agenteID = agenteID;
    }

    public int getRango() {
        return rango;
    }

    public void setRango(int rango) {
        this.rango = rango;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "AgenteVO{" +
                "mote='" + mote + '\'' +
                ", rango=" + rango +
                ", especialidad='" + especialidad + '\'' +
                '}';
    }
}
