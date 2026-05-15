package HuntPMOVO;

public class AgenteVO{
    private int agenteID;
    private String mote;
    private int rango;
    private String especialidad;
    private String contrasenya;
    private PersonaVO persona;

    public AgenteVO(int agenteID, String mote, int rango, String especialidad, String contrasenya, PersonaVO persona) {
        this.agenteID = agenteID;
        this.mote = mote;
        this.rango = rango;
        this.especialidad = especialidad;
        this.contrasenya = contrasenya;
        this.persona = persona;
    }

    public AgenteVO(int agenteID, String mote, int rango, String especialidad, String contrasenya) {
        this.agenteID = agenteID;
        this.mote = mote;
        this.rango = rango;
        this.especialidad = especialidad;
        this.contrasenya = contrasenya;
    }

    public AgenteVO(String mote, int rango, String especialidad, String contrasenya, PersonaVO persona) {
        this.mote = mote;
        this.rango = rango;
        this.especialidad = especialidad;
        this.contrasenya = contrasenya;
        this.persona = persona;
    }

    public int getAgenteID() {
        return agenteID;
    }

    public void setAgenteID(int agenteID) {
        this.agenteID = agenteID;
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

    public String getMote() {
        return mote;
    }

    public void setMote(String mote) {
        this.mote = mote;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public PersonaVO getPersona() {
        return persona;
    }

    public void setPersona(PersonaVO persona) {
        this.persona = persona;
    }

    @Override
    public String toString() {
        return "AgenteVO{" +
                "mote='" + mote + '\'' +
                ", rango=" + rango +
                ", especialidad='" + especialidad + '\'' +
                ", persona=" + persona +
                '}';
    }
}
