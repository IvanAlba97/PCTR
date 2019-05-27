
/**
 *
 * @author ivan0
 */
import java.io.Serializable;

public class Libro implements Serializable {

    private String titulo, autor;
    private int anno;

    public Libro(String titulo, String autor, int anno) {
        this.titulo = titulo;
        this.autor = autor;
        this.anno = anno;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnno() {
        return anno;
    }

    @Override
    public String toString() {
        return "Libro{" + "titulo=" + titulo + ", autor=" + autor + ", anno=" + anno + '}';
    }

}
