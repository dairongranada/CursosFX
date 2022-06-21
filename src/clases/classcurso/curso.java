package clases.classcurso;

public class curso extends horario{
    private String nombre;
    private String instructor;
    
    public curso(){

    }

    public curso(String fecha, String hora, String nombre, String instructor) {
        super(fecha, hora);
        this.nombre = nombre;
        this.instructor = instructor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    
}
