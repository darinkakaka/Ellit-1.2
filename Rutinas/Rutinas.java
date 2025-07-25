package Rutinas;
// ===============================
// Clase Rutinas.java
// ===============================
// Contiene los atributos de las rutinas, constructores, getters/setters y toString

public class Rutinas {
    private int idRutina;
    private String nombreRutina;
    private String descripcionRutina;
    private String objetivoRutina;
    private String dificultadRutina;
    private int diasPorSemana;
    private double horasEstimadas;

    // Constructor vacío
    public Rutinas() {}

    // Constructor con parámetros
    public Rutinas(int idRutina, String nombreRutina, String descripcionRutina, 
                  String objetivoRutina, String dificultadRutina, 
                  int diasPorSemana, double horasEstimadas) {
        this.idRutina = idRutina;
        this.nombreRutina = nombreRutina;
        this.descripcionRutina = descripcionRutina;
        this.objetivoRutina = objetivoRutina;
        this.dificultadRutina = dificultadRutina;
        this.diasPorSemana = diasPorSemana;
        this.horasEstimadas = horasEstimadas;
    }

    // Getters y Setters
    public int getIdRutina() {
        return idRutina;
    }

    public void setIdRutina(int idRutina) {
        this.idRutina = idRutina;
    }

    public String getNombreRutina() {
        return nombreRutina;
    }

    public void setNombreRutina(String nombreRutina) {
        this.nombreRutina = nombreRutina;
    }

    public String getDescripcionRutina() {
        return descripcionRutina;
    }

    public void setDescripcionRutina(String descripcionRutina) {
        this.descripcionRutina = descripcionRutina;
    }

    public String getObjetivoRutina() {
        return objetivoRutina;
    }

    public void setObjetivoRutina(String objetivoRutina) {
        this.objetivoRutina = objetivoRutina;
    }

    public String getDificultadRutina() {
        return dificultadRutina;
    }

    public void setDificultadRutina(String dificultadRutina) {
        this.dificultadRutina = dificultadRutina;
    }

    public int getDiasPorSemana() {
        return diasPorSemana;
    }

    public void setDiasPorSemana(int diasPorSemana) {
        this.diasPorSemana = diasPorSemana;
    }

    public double getHorasEstimadas() {
        return horasEstimadas;
    }

    public void setHorasEstimadas(double horasEstimadas) {
        this.horasEstimadas = horasEstimadas;
    }

    // Método toString
    @Override
    public String toString() {
        return String.format("ID: %d | Nombre: %s | Objetivo: %s | Dificultad: %s | Días/Semana: %d | Duración: %.1f horas",
                idRutina, nombreRutina, objetivoRutina, dificultadRutina, diasPorSemana, horasEstimadas);
    }
}