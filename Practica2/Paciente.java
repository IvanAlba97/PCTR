
public class Paciente {

    int telefono, edad;
    String nombre, dni, direccion, compania, sexo;

    Paciente(int telefono, int edad, String nombre, String dni, String direccion, String compania, String sexo) {
        this.telefono = telefono;
        this.edad = edad;
        this.nombre = nombre;
        this.dni = dni;
        this.direccion = direccion;
        this.compania = compania;
        this.sexo = sexo;
    }

    Paciente() {
    }

    public int getTelefono() {
        return telefono;
    }

    public int getEdad() {
        return edad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCompania() {
        return compania;
    }

    public String getSexo() {
        return sexo;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

}
