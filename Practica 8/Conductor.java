
/**
 *
 * @author ivan0
 */
public class Conductor {

    private String dni, nombre, apellidos;
    private int puntos;

    public Conductor(String dni, String nombre, String apellidos, int puntos) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.puntos = puntos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return dni + ", " + nombre + " " + apellidos + ", " + puntos + " puntos.";
    }

    public void sumarPunto(int puntos) {
        this.puntos += puntos;
    }

    void quitarPuntos(int puntos) {
        this.puntos -= puntos;
    }
}
