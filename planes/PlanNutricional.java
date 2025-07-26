package planes;

import java.util.ArrayList;
import java.util.List;

public class PlanNutricional {
    private int idPlan;
    private String nombre;
    private String objetivo;
    private String recomendaciones;
    private String descripcion;
    private char estatus;
    private List<PlanComidas> comidas;

    public PlanNutricional(int idPlan, String nombre, String objetivo, String recomendaciones, String descripcion) {
        this.idPlan = idPlan;
        this.nombre = nombre;
        this.objetivo = objetivo;
        this.recomendaciones = recomendaciones;
        this.descripcion = descripcion;
        this.comidas = new ArrayList<>();
    }

    // Constructor alternativo para crear sin id (id=0 por defecto)
    public PlanNutricional(String nombre, String objetivo, String recomendaciones, String descripcion) {
        this.idPlan = 0;
        this.nombre = nombre;
        this.objetivo = objetivo;
        this.recomendaciones = recomendaciones;
        this.descripcion = descripcion;
        this.comidas = new ArrayList<>();
    }



      public PlanNutricional(){
    this.comidas = new ArrayList<>();
    }

    // Getters y setters
    public int getIdPlan() { return idPlan; }
    public void setIdPlan(int idPlan) { this.idPlan = idPlan; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getObjetivo() { return objetivo; }
    public void setObjetivo(String objetivo) { this.objetivo = objetivo; }
    public char getEstatus() { return estatus; }
    public String getRecomendaciones() { return recomendaciones; }
    public void setRecomendaciones(String recomendaciones) { this.recomendaciones = recomendaciones; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public List<PlanComidas> getComidas() { return comidas; }
    public void setComidas(List<PlanComidas> comidas) { this.comidas = comidas; }
     public void setEstatus(char estatus) { this.estatus = estatus; }
    
    public void agregarComida(PlanComidas comida) {
        this.comidas.add(comida);
    }

    @Override
    public String toString() {
        return "PlanNutricional [idPlan=" + idPlan + ", nombre=" + nombre + ", objetivo=" + objetivo
                + ", recomendaciones=" + recomendaciones + ", descripcion=" + descripcion + ", comidas=" + comidas
                + ", getEstatus()=" + getEstatus() + "]";
    }

    
    
}
