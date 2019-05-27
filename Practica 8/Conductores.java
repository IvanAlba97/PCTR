
/**
 *
 * @author ivan0
 */
import java.util.ArrayList;

public class Conductores {

    ArrayList<Conductor> conductores;

    public synchronized Conductor obtenerConductor(int i) {
        return conductores.get(i);
    }

    public synchronized void agregarConductor(Conductor conductor) {
        conductores.add(conductor);
    }

    public synchronized void eliminarConductor(Conductor conductor) {
        conductores.remove(conductor);
    }

    public synchronized void sumarPuntoConductor(Conductor conductor, int puntos) {
        conductores.get(conductores.indexOf(conductor)).sumarPunto(puntos);
    }

    public synchronized void quitarPuntosConductor(Conductor conductor, int puntos) {
        conductores.get(conductores.indexOf(conductor)).quitarPuntos(puntos);
    }
}
